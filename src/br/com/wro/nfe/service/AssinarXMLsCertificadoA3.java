package br.com.wro.nfe.service;

import java.io.BufferedReader;  
import java.io.ByteArrayInputStream;  
import java.io.ByteArrayOutputStream;  
import java.io.FileInputStream;  
import java.io.IOException;  
import java.io.InputStreamReader;  
import java.security.InvalidAlgorithmParameterException;  
import java.security.KeyStore;  
import java.security.NoSuchAlgorithmException;  
import java.security.PrivateKey;  
import java.security.Provider;  
import java.security.Security;  
import java.security.cert.X509Certificate;  
import java.util.ArrayList;  
import java.util.Collections;  
import java.util.Enumeration;  
import java.util.List;  
  
import javax.xml.crypto.dsig.CanonicalizationMethod;  
import javax.xml.crypto.dsig.DigestMethod;  
import javax.xml.crypto.dsig.Reference;  
import javax.xml.crypto.dsig.SignatureMethod;  
import javax.xml.crypto.dsig.SignedInfo;  
import javax.xml.crypto.dsig.Transform;  
import javax.xml.crypto.dsig.XMLSignature;  
import javax.xml.crypto.dsig.XMLSignatureFactory;  
import javax.xml.crypto.dsig.dom.DOMSignContext;  
import javax.xml.crypto.dsig.keyinfo.KeyInfo;  
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;  
import javax.xml.crypto.dsig.keyinfo.X509Data;  
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;  
import javax.xml.crypto.dsig.spec.TransformParameterSpec;  
import javax.xml.parsers.DocumentBuilderFactory;  
import javax.xml.parsers.ParserConfigurationException;  
import javax.xml.transform.Transformer;  
import javax.xml.transform.TransformerException;  
import javax.xml.transform.TransformerFactory;  
import javax.xml.transform.dom.DOMSource;  
import javax.xml.transform.stream.StreamResult;  
  
import org.w3c.dom.Document;  
import org.w3c.dom.NodeList;  
import org.xml.sax.SAXException;  

public class AssinarXMLsCertificadoA3 {
	
	private static final String INFINUT = "infInut";  
    private static final String INFCANC = "infCanc";  
    private static final String NFE = "NFe";  
  
    private PrivateKey privateKey;  
    private KeyInfo keyInfo;  
    
    private static String senhaDoCertificadoDoCliente = "0123";  
  
    public static void main(String[] args) {  
        try {  
            
            AssinarXMLsCertificadoA3 assinarXMLsCertificadoA3 = new AssinarXMLsCertificadoA3();  
  
            /** 
             * Assinando o XML de Lote da NF-e 
             * fileEnviNFe = Caminho do Arquivo XML (EnviNFe) gerado; 
             */  
            info("");  
            String fileEnviNFe = "C:/JavaC/NF-e/xmlEnviNFe.xml";  
            String xmlEnviNFe = lerXML(fileEnviNFe);  
            String xmlEnviNFeAssinado = assinarXMLsCertificadoA3.assinaEnviNFe(  
                    xmlEnviNFe, senhaDoCertificadoDoCliente);  
            info("XML EnviNFe Assinado: " + xmlEnviNFeAssinado);  
  
            /** 
             * Assinando o XML de Cancelamento da NF-e 
             * fileCancNFe = Caminho do Arquivo XML (CancNFe) gerado; 
             */  
            info("");  
            String fileCancNFe = "C:/JavaC/NF-e/xmlCancNFe.xml";  
            String xmlCancNFe = lerXML(fileCancNFe);  
            String xmlCancNFeAssinado = assinarXMLsCertificadoA3.assinaCancNFe(  
                    xmlCancNFe, senhaDoCertificadoDoCliente);  
            info("XML CancNFe Assinado: " + xmlCancNFeAssinado);  
  
            /** 
             * Assinando o XML de Inutilizacao da NF-e 
             * fileInutNFe = Caminho do Arquivo XML (InutNFe) gerado; 
             */  
            info("");  
            String fileInutNFe = "C:/JavaC/NF-e/xmlInutNFe.xml";  
            String xmlInutNFe = lerXML(fileInutNFe);  
            String xmlInutNFeAssinado = assinarXMLsCertificadoA3.assinaInutNFe(  
                    xmlInutNFe, senhaDoCertificadoDoCliente);  
            info("XML InutNFe Assinado: " + xmlInutNFeAssinado);  
  
        } catch (Exception e) {  
            e.printStackTrace();  
            error("| " + e.toString());  
        }  
    }  
  
    /** 
     * Assinatura do XML de Envio de Lote da NF-e utilizando Certificado 
     * Digital A3. 
     * @param xml 
     * @param certificado 
     * @param senha 
     * @return 
     * @throws Exception 
     */  
    public String assinaEnviNFe(String xml, String senha)  
            throws Exception {  
        Document document = documentFactory(xml);  
        XMLSignatureFactory signatureFactory = XMLSignatureFactory.getInstance("DOM");  
        ArrayList<Transform> transformList = signatureFactory(signatureFactory);  
        loadCertificates(senha, signatureFactory);  
  
        for (int i = 0; i < document.getDocumentElement().getElementsByTagName(NFE).getLength(); i++) {  
            assinarNFe(signatureFactory, transformList, privateKey, keyInfo, document, i);  
        }  
  
        return outputXML(document);  
    }  
  
    /** 
     * Assintaruda do XML de Cancelamento da NF-e utilizando Certificado 
     * Digital A3. 
     * @param xml 
     * @param certificado 
     * @param senha 
     * @return 
     * @throws Exception 
     */  
    public String assinaCancNFe(String xml, String senha) throws Exception {  
        return assinaCancelametoInutilizacao(xml, senha, INFCANC);  
    }  
  
    /** 
     * Assinatura do XML de Inutilizacao de sequenciais da NF-e utilizando 
     * Certificado Digital A3. 
     * @param xml 
     * @param certificado 
     * @param senha 
     * @return 
     * @throws Exception 
     */  
    public String assinaInutNFe(String xml, String senha) throws Exception {  
        return assinaCancelametoInutilizacao(xml, senha, INFINUT);  
    }  
  
    private void assinarNFe(XMLSignatureFactory fac,  
            ArrayList<Transform> transformList, PrivateKey privateKey,  
            KeyInfo ki, Document document, int indexNFe) throws Exception {  
  
        NodeList elements = document.getElementsByTagName("infNFe");  
        org.w3c.dom.Element el = (org.w3c.dom.Element) elements.item(indexNFe);  
        String id = el.getAttribute("Id");  
  
        Reference ref = fac.newReference("#" + id,  
                fac.newDigestMethod(DigestMethod.SHA1, null), transformList,  
                null, null);  
  
        SignedInfo si = fac.newSignedInfo(fac.newCanonicalizationMethod(  
                CanonicalizationMethod.INCLUSIVE,  
                (C14NMethodParameterSpec) null), fac  
                .newSignatureMethod(SignatureMethod.RSA_SHA1, null),  
                Collections.singletonList(ref));  
  
        XMLSignature signature = fac.newXMLSignature(si, ki);  
  
        DOMSignContext dsc = new DOMSignContext(privateKey,   
                document.getDocumentElement().getElementsByTagName(NFE).item(indexNFe));  
        signature.sign(dsc);  
    }  
  
    private String assinaCancelametoInutilizacao(String xml,  
            String senha, String tagCancInut) throws Exception {  
        Document document = documentFactory(xml);  
  
        XMLSignatureFactory signatureFactory = XMLSignatureFactory  
                .getInstance("DOM");  
        ArrayList<Transform> transformList = signatureFactory(signatureFactory);  
        loadCertificates(senha, signatureFactory);  
  
        NodeList elements = document.getElementsByTagName(tagCancInut);  
        org.w3c.dom.Element el = (org.w3c.dom.Element) elements.item(0);  
        String id = el.getAttribute("Id");  
  
        Reference ref = signatureFactory.newReference("#" + id,  
                signatureFactory.newDigestMethod(DigestMethod.SHA1, null),  
                transformList, null, null);  
  
        SignedInfo si = signatureFactory.newSignedInfo(signatureFactory  
                .newCanonicalizationMethod(CanonicalizationMethod.INCLUSIVE,  
                        (C14NMethodParameterSpec) null), signatureFactory  
                .newSignatureMethod(SignatureMethod.RSA_SHA1, null),  
                Collections.singletonList(ref));  
  
        XMLSignature signature = signatureFactory.newXMLSignature(si, keyInfo);  
  
        DOMSignContext dsc = new DOMSignContext(privateKey, document.getFirstChild());  
        signature.sign(dsc);  
  
        return outputXML(document);  
    }  
  
    private ArrayList<Transform> signatureFactory(  
            XMLSignatureFactory signatureFactory)  
            throws NoSuchAlgorithmException, InvalidAlgorithmParameterException {  
        ArrayList<Transform> transformList = new ArrayList<Transform>();  
        TransformParameterSpec tps = null;  
        Transform envelopedTransform = signatureFactory.newTransform(  
                Transform.ENVELOPED, tps);  
        Transform c14NTransform = signatureFactory.newTransform(  
                "http://www.w3.org/TR/2001/REC-xml-c14n-20010315", tps);  
  
        transformList.add(envelopedTransform);  
        transformList.add(c14NTransform);  
        return transformList;  
    }  
  
    private Document documentFactory(String xml) throws SAXException,  
            IOException, ParserConfigurationException {  
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
        factory.setNamespaceAware(true);  
        Document document = factory.newDocumentBuilder().parse(  
                new ByteArrayInputStream(xml.getBytes()));  
        return document;  
    }  
  
    private void loadCertificates(String senha,  
            XMLSignatureFactory signatureFactory) throws Exception {
  
        /** 
         * Para Certificados A3 Cartao usar: SmartCard.cfg; 
         * Para Certificados A3 Token usar: Token.cfg; 
         */  
        Provider provider = new sun.security.pkcs11.SunPKCS11("SmartCard.cfg");  
        Security.addProvider(provider);   
  
        KeyStore ks = KeyStore.getInstance("pkcs11", provider);  
        try {  
            ks.load(null, senha.toCharArray());  
        } catch (IOException e) {  
            throw new Exception("Senha do Certificado Digital incorreta ou Certificado inválido.");  
        }  
  
        KeyStore.PrivateKeyEntry pkEntry = null;  
        Enumeration<String> aliasesEnum = ks.aliases();  
        while (aliasesEnum.hasMoreElements()) {  
            String alias = (String) aliasesEnum.nextElement();  
            if (ks.isKeyEntry(alias)) {  
                pkEntry = (KeyStore.PrivateKeyEntry) ks.getEntry(alias,  
                        new KeyStore.PasswordProtection(senha.toCharArray()));  
                privateKey = pkEntry.getPrivateKey();  
                break;  
            }  
        }  
  
        X509Certificate cert = (X509Certificate) pkEntry.getCertificate();  
        info("SubjectDN: " + cert.getSubjectDN().toString());  
  
        KeyInfoFactory keyInfoFactory = signatureFactory.getKeyInfoFactory();  
        List<X509Certificate> x509Content = new ArrayList<X509Certificate>();  
  
        x509Content.add(cert);  
        X509Data x509Data = keyInfoFactory.newX509Data(x509Content);  
        keyInfo = keyInfoFactory.newKeyInfo(Collections.singletonList(x509Data));  
    }  
  
    private String outputXML(Document doc) throws TransformerException {  
		
    	ByteArrayOutputStream os = new ByteArrayOutputStream();
		
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer trans = tf.newTransformer();
		
		trans.transform(new DOMSource(doc), new StreamResult(os));
		
		String xml = os.toString();
		
		if ((xml != null) && (!"".equals(xml))) {
			xml = xml.replaceAll("\\r\\n", "");
			xml = xml.replaceAll(" standalone=\"no\"", "");
		}
		
		return xml;
    }  
  
    private static String lerXML(String fileXML) throws IOException {  
        String linha = "";  
        StringBuilder xml = new StringBuilder();  
  
        BufferedReader in = new BufferedReader(new InputStreamReader(  
                new FileInputStream(fileXML)));  
        while ((linha = in.readLine()) != null) {  
            xml.append(linha);  
        }  
        in.close();  
  
        return xml.toString();  
    }  
  
    /** 
     * Log ERROR. 
     *  
     * @param error 
     */  
    private static void error(String error) {  
        System.out.println("| ERROR: " + error);  
    }  
  
    /** 
     * Log INFO. 
     *  
     * @param info 
     */  
    private static void info(String info) {  
        System.out.println("| INFO: " + info);  
    }  

}
