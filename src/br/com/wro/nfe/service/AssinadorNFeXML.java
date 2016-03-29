package br.com.wro.nfe.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.Security;
import java.security.cert.CertificateException;
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
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import br.com.wro.nfe.enums.TipoCertificado;
import br.com.wro.nfe.utils.ConfigUtils;
import br.com.wro.nfe.utils.SecurityUtils;

/**
 * <code>
 * <h1>AssinadorNFeXML</h1>
 * 
 * Pacote: 		br.com.wro.nfe.service<br>
 * Descrição: 	Classe responsável pela assinatura digital do XML de documentos 
 * 				eletrônicos relativos ao processo da NF-e.<br>
 * Projeto: 	consultaNFeDist<br>
 * @author  	Felipe Caparelli<br>
 * @version 	1.0<br>
 * @since   	13/05/2015<br>
 * </code>
 */
public class AssinadorNFeXML {
	
    private static final String INF_EVEN = "infEvento";
    
    private PrivateKey privateKey = null;  
    private KeyInfo keyInfo = null; 
    private KeyStore keyStore = null;
    
    public AssinadorNFeXML() {}
    
    /**
     * Obtem o keyStore ja carregado e inicializado no processo anterior.
     * @param keyStore
     */
    public AssinadorNFeXML(KeyStore keyStoreA3) {
    	this.keyStore = keyStoreA3;
    }
    
    /**
	 * <code>
	 * Descrição: 	Método responsável por assinar digitalmente os documentos que 
	 * 				serão enviados como evento para a Secretaria da Fazenda.<br>
	 * @since 		13/05/2015<br>
	 * @author 		Felipe Caparelli<br>
	 * @param 		xml<br>
	 * @return		String<br>
	 * @throws 		Exception
	 *             </code>
	 */
    public String assinaEvento(String xml) throws Exception {
    	return assinarXML(xml, INF_EVEN);
    }
    
    /**
     * Assina algum dos eventos de NFe (Ciencia da Operacao, Cancelamento, Inutilizacao).
     * 
     * @param xml - documento a ser assinado, ainda como String
     * @param tag - tag que receberá a assinatura
     * 
     * @return String - o xml assinado como String
     * 
     * @throws Exception
     */
    private String assinarXML(String xml, String tag) throws Exception {
        
    	Document document = documentFactory(xml);
  
        XMLSignatureFactory signatureFactory = XMLSignatureFactory.getInstance("DOM");  
        ArrayList<Transform> transformList = signatureFactory(signatureFactory);
        
        //carrega os certificados
        loadCertificates(signatureFactory);
        
        //carrega a assinatura (verificar se o nó que esta sendo passado é o correto evento, ou se devo passar infEvento)
        XMLSignature signature = loadXMLSignature(document, tag, 0, signatureFactory, transformList);
        
        //assina o XML
        signature.sign(new DOMSignContext(privateKey, document.getFirstChild()));
        
        //retorna o XML
        return outputXML(document);  
    }
    
    /**
     * Retorna um objeto para assinatura de XML.
     * Obs.: Funcionando com java a partir do 7u25
     * 
     * @param document - documento XML a ser assinado
     * @param tag - elemento que receberá a assinatura
     * @param tagIndex - index da tag que deve ser assinada; geralmente 0
     * @param signatureFactory - factory de assinaturas
     * @param transformList - lista de elementos para gerar a criptografia
     * 
     * @return XMLSignature
     * 
     * @throws NoSuchAlgorithmException
     * @throws InvalidAlgorithmParameterException
     */
    private XMLSignature loadXMLSignature(Document document, String tag, int tagIndex, XMLSignatureFactory signatureFactory, ArrayList<Transform> transformList) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException {
    	
    	//le os elementos do XML 
        NodeList elements = document.getElementsByTagName(tag);  
        Element el = (Element) elements.item(tagIndex);  
        String id = el.getAttribute("Id");
        //vide: http://stackoverflow.com/questions/17331187/xml-dig-sig-error-after-upgrade-to-java7u25
        el.setIdAttribute("Id", true);  
        
        //detalhes de criptografia da assinatura
        Reference ref = signatureFactory.newReference("#" + id, signatureFactory.newDigestMethod(DigestMethod.SHA1, null), transformList, null, null);  
        CanonicalizationMethod canonicMethod = signatureFactory.newCanonicalizationMethod(CanonicalizationMethod.INCLUSIVE, (C14NMethodParameterSpec) null);
        SignatureMethod signMethod = signatureFactory.newSignatureMethod(SignatureMethod.RSA_SHA1, null);
        
        //informacoes da assinatura
        SignedInfo si = signatureFactory.newSignedInfo(canonicMethod, signMethod, Collections.singletonList(ref));
    	
        //retorna um novo elemento com as informacoes para a assinatura
    	return signatureFactory.newXMLSignature(si, keyInfo);
    }
    
    /**
     * Retorna uma lista de criptografias
     * 
     * @param signatureFactory
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidAlgorithmParameterException
     */
    private ArrayList<Transform> signatureFactory(XMLSignatureFactory signatureFactory) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException {  
        
    	ArrayList<Transform> transformList = new ArrayList<Transform>();  
        TransformParameterSpec tps = null;  
        Transform envelopedTransform = signatureFactory.newTransform(Transform.ENVELOPED, tps);  
        Transform c14NTransform = signatureFactory.newTransform("http://www.w3.org/TR/2001/REC-xml-c14n-20010315", tps);  
  
        transformList.add(envelopedTransform);  
        transformList.add(c14NTransform);
        
        return transformList;  
    }
    
    /**
     * Le o conteudo do XML e constroi o objeto Document (DOM)
     * 
     * @param xml
     * @return
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     */
    private Document documentFactory(String xml) throws SAXException, IOException, ParserConfigurationException {
    	
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        
        factory.setNamespaceAware(true); 
        
        return factory.newDocumentBuilder().parse(new ByteArrayInputStream(xml.getBytes()));
    }
    
    /**
     * Obtem uma instancia do certificado A3. Caso exista nao recarrega.
     * 
     * @param tipoCertificado
     * @param pin
     * @return
     * @throws IOException
     * @throws KeyStoreException
     * @throws NoSuchAlgorithmException
     * @throws CertificateException
     */
    private KeyStore instanceOfA3(TipoCertificado tipoCertificado, String pin) throws IOException, KeyStoreException, NoSuchAlgorithmException, CertificateException {
    	
        if (keyStore == null) {
        	
        	Provider provider = new sun.security.pkcs11.SunPKCS11(ConfigUtils.smartCard());  
            Security.addProvider(provider);
            
            keyStore = KeyStore.getInstance("pkcs11", provider);  
      
            try {  
                keyStore.load(null, pin.toCharArray());  
            } catch (IOException e) {  
                throw new CertificateException("Senha do Certificado Digital esta incorreta ou Certificado inválido.");  
            }  
        }
        
        return keyStore;  
    } 
    
    /**
     * Le os certificados existentes no cartao inteligente (SmartCard - A3) e os carrega na memoria do sistema.
     * 
     * @param senha
     * @param signatureFactory
     * @throws Exception
     */
    private void loadCertificates(XMLSignatureFactory signatureFactory) throws Exception {  
    	
    	String senha = SecurityUtils.getCertPassword();
    	
        /** 
         * Para Certificados A3 Cartao Inteligente (SmartCard) usar: ConfigUtils.leitorGemPC_Perto() 
         * Para Certificados A3 Token usar: ConfigUtils.tokenAladdin()
         */
        KeyStore ks = instanceOfA3(TipoCertificado.A3_PKCS11, senha);
  
        KeyStore.PrivateKeyEntry pkEntry = null;  
        Enumeration<String> aliasesEnum = ks.aliases();
        
        while (aliasesEnum.hasMoreElements()) {  
            
        	String alias = (String) aliasesEnum.nextElement();  
            
        	if (ks.isKeyEntry(alias)) {  
                pkEntry = (KeyStore.PrivateKeyEntry) ks.getEntry(alias, new KeyStore.PasswordProtection(senha.toCharArray()));  
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
  
    /**
     * Metodo utilitario para imprimir o XML tratado.
     * 
     * @param doc
     * @return
     * @throws TransformerException
     */
	private String outputXML(Document doc) throws TransformerException {
		
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer trans = tf.newTransformer();
		
		trans.transform(new DOMSource(doc), new StreamResult(os));
		
		String xml = os.toString();
		
		if ((xml != null) && (!"".equals(xml))) {
			xml = xml.replaceAll("\\r\\n", ""); //remove espacos e quebras de linha
			xml = xml.replaceAll(" standalone=\"no\"", ""); //remove cabecalhos desnecessarios
		}
		
		return xml;
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
