package br.com.wro.nfe.service;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;
import org.w3c.dom.Element;

import procNFe_v110.TEnderEmi;
import procNFe_v110.TEndereco;
import procNFe_v110.TNFe.InfNFe;
import procNFe_v110.TNFe.InfNFe.Cobr;
import procNFe_v110.TNFe.InfNFe.Cobr.Dup;
import procNFe_v110.TNFe.InfNFe.Cobr.Fat;
import procNFe_v110.TNFe.InfNFe.Dest;
import procNFe_v110.TNFe.InfNFe.Det;
import procNFe_v110.TNFe.InfNFe.Det.Prod;
import procNFe_v110.TNFe.InfNFe.Emit;
import procNFe_v110.TNFe.InfNFe.Ide;
import procNFe_v110.TNFe.InfNFe.Ide.NFref;
import procNFe_v110.TNFe.InfNFe.Ide.NFref.RefNF;
import procNFe_v110.TNFe.InfNFe.InfAdic;
import procNFe_v110.TNFe.InfNFe.InfAdic.ObsCont;
import procNFe_v110.TNFe.InfNFe.Total;
import procNFe_v110.TNFe.InfNFe.Total.ICMSTot;
import procNFe_v110.TNFe.InfNFe.Transp;
import procNFe_v110.TNFe.InfNFe.Transp.Transporta;
import procNFe_v110.TNFe.InfNFe.Transp.Vol;
import procNFe_v110.TNfeProc;
import procNFe_v110.TVeiculo;
import br.com.wro.nfe.model.NfeAssocDocRef;
import br.com.wro.nfe.model.NfeAssocDuplicata;
import br.com.wro.nfe.model.NfeAssocInfoContrib;
import br.com.wro.nfe.model.NfeAssocProdServ;
import br.com.wro.nfe.model.NfeAssocReboque;
import br.com.wro.nfe.model.NfeAssocVolume;
import br.com.wro.nfe.model.NfeVO;
import br.com.wro.nfe.utils.DataUtils;
import br.com.wro.nfe.utils.PlacaUtils;

/**
 * Classe responsavel por processar a NFe no formato (versao) 3.10 do schema da SeFaz.
 */
public class ProcessaNFe_v110Service extends ProcessaNFeService {
	
	private static final Logger logger = Logger.getLogger(ProcessaNFe_v110Service.class);
	
	public static boolean processaNFe(Element element, Long idLogProcessamento) throws JAXBException {
		
		//TNfeProc: (nfe + protocolo)
		TNfeProc nfeProc = unmarshallXml(TNfeProc.class, element);
		
		//persistir os dados da NFe
		return processaNFe(nfeProc, idLogProcessamento);
	}

	
	/**
	 * Processa a string do Resumo da NFe recebida.
	 * 
	 * @param xmlNfeProc
	 * @return
	 * @throws JAXBException
	 */
	public static boolean processaNfeProc(String xmlNfeProc, Long idLogProcessamento) throws JAXBException {
		
		//TNfeProc: (nfe + protocolo)
		TNfeProc nfeProc = unmarshallXml(TNfeProc.class, xmlNfeProc);
		
		//persistir os dados da NFe
		return processaNFe(nfeProc, idLogProcessamento);
	}
	
	/**
	 * Processa uma NF-e.
	 * 
	 * @param nfeProc
	 * @return
	 */
	public static boolean processaNFe(TNfeProc nfeProc, Long idLogProcessamento) {
		
		NfeVO nfe = new NfeVO();
		
		nfeProc.getProtNFe().getInfProt().getCStat();
		Integer statusNF = Integer.parseInt(nfeProc.getProtNFe().getInfProt().getCStat());
		nfe.setCodStatNF(statusNF);
		
		//nfe.setIdNFInte(Integer idNFInte);
		InfNFe infNfe = nfeProc.getNFe().getInfNFe();
		//Dados do emitente...
		Emit emitente = infNfe.getEmit();
		String numDocEmit = emitente.getCNPJ();
		if(numDocEmit == null || numDocEmit.isEmpty()){
			numDocEmit = emitente.getCPF();
		}
		nfe.setCpfCnpjEmitenteNF(numDocEmit);
		nfe.setRazalSocialEmitenteNF(emitente.getXNome());
		nfe.setNomeFantasiaEmitenteNF(emitente.getXFant());
		nfe.setInscricaoMunicipalEmitenteNF(emitente.getIM());
		nfe.setInscricaoEstadualEmitenteNF(emitente.getIE());
		//nfe.setCodRegimeTrubitarioEmitenteNF(emitente.getCRT() != null ? Integer.parseInt(emitente.getCRT()) : null); TODO verificar a documentacao cRT do emit
		
		//Dados de endereço do emitente...
		TEnderEmi endereco = emitente.getEnderEmit();
		if(endereco != null) {			
			nfe.setLogradouroEmitenteNF(endereco.getXLgr());
			nfe.setNumeroLogradouroEmitenteNF(endereco.getNro());
			nfe.setComplEnderecoEmitenteNF(endereco.getXCpl());
			nfe.setBairroEmitenteNF(endereco.getXBairro());
			nfe.setMunicipioEmitenteNF(endereco.getXMun());
			nfe.setSgUfEmitenteNF(endereco.getUF() != null ? endereco.getUF().value() : null);
			nfe.setCepEmitenteNF(endereco.getCEP());
			nfe.setPaisEmitenteNF(endereco.getXPais());
			nfe.setTelefoneEmitenteNF(endereco.getFone());
		}
		//nfe.setEmailEmitenteNF(??); nao existe nos beans (XML)
		Dest destinatario = infNfe.getDest();
		nfe.setCpfCnpjDestinatarioNF(destinatario.getCNPJ()); //CNPJ da sua empresa
		nfe.setRazaoSocialDestinatarioNF(destinatario.getXNome());
		nfe.setNomeFantasiaDestinatarioNF(destinatario.getXNome());
		//nfe.setInscricaoMunicipalDestinatarioNF(destinatario.getIM()); XXX Nao existe no layout v2.00
		nfe.setInscricaoEstadualDestinatarioNF(destinatario.getIE());
		//nfe.setCodRegimeTributarioDestinatarioNF(???); XXX Nao existe no layout v2.00
		//nfe.setEmailDestinatarioNF(destinatario.getEmail());
		//nfe.setTipoInscricaoDestinatarioNF(destinatario.getIE() != null ? Long.parseLong(destinatario.getIE()) : null);
		
		//Dados de endereço do destinatário...
		TEndereco enderecoDest = destinatario.getEnderDest();
		if(enderecoDest != null){			
			nfe.setLogradouroDestinatarioNF(enderecoDest.getXLgr());
			nfe.setNumeroLogradouroDestinatarioNF(enderecoDest.getNro());
			nfe.setComplEnderecoDestinatarioNF(enderecoDest.getXCpl());
			nfe.setBairroDestinatarioNF(enderecoDest.getXBairro());
			nfe.setMunicipioDestinatarioNF(enderecoDest.getCMun());
			nfe.setSgUfDestinatarioNF(enderecoDest.getUF() != null ? enderecoDest.getUF().value() : null);
			nfe.setCepDestinatarioNF(enderecoDest.getCEP());
			nfe.setPaisDestinatarioNF(enderecoDest.getXPais());
			nfe.setTelefoneDestinatarioNF(enderecoDest.getFone());
		}
		
		Ide ide = infNfe.getIde();
		if(ide != null) {			
			nfe.setCodNFe(infNfe.getId());
			nfe.setCodChaveAcesso(ide.getCNF());
			nfe.setCdUfOrigIBGE(ide.getCUF() != null ? Integer.parseInt(ide.getCUF()) : null);			
			nfe.setCodFormaPgto(ide.getIndPag() != null ? Integer.parseInt(ide.getIndPag()) : null);
			nfe.setCodModeloDocFiscal(ide.getMod() != null ? Integer.parseInt(ide.getMod()) : null);
			nfe.setNumSeriDocFiscal(ide.getSerie() != null ? Integer.parseInt(ide.getSerie()) : null);
			nfe.setNumDocFiscal(ide.getNNF() != null ? Integer.parseInt(ide.getNNF()) : null);			
			nfe.setDtEmissaoDocFiscal(DataUtils.getDateAAAA_MM_DD(ide.getDEmi()));
			nfe.setDtEntradaSaida(DataUtils.getDateAAAA_MM_DDThh_mm_ss(ide.getDSaiEnt()));
			nfe.setCodTipoOperacao(ide.getTpNF() != null ? Integer.parseInt(ide.getTpNF()) : null);
			//nfe.setCodTipoLocalDestinatarioOper(ide.getIdDest() != null ? Integer.parseInt(ide.getIdDest()) : null); XXX Nao existe no layout v2.00
			nfe.setCodMunicipioOcorFatoGeracao(ide.getCMunFG() != null ? Integer.parseInt(ide.getCMunFG()) : null);
			nfe.setCodFormImpressao(ide.getTpImp() != null ? Integer.parseInt(ide.getTpImp()) : null);
			nfe.setCodFormEmissao(ide.getTpEmis() != null ? Integer.parseInt(ide.getTpEmis()) : null);
			nfe.setDigitoVerificadorChaveAcesso(ide.getCDV() != null ? Integer.parseInt(ide.getCDV()) : null);
			nfe.setTipoAmbienteEmissao(ide.getTpAmb() != null ? Integer.parseInt(ide.getTpAmb()) : null);
			nfe.setCodFinalEmissao(ide.getFinNFe() != null ? Integer.parseInt(ide.getFinNFe()) : null);
			//nfe.setCodTipoOperConsFina(ide.getIndFinal() != null ? Integer.parseInt(ide.getIndFinal()) : null); XXX Nao existe no layout v2.00
			//nfe.setCodTipoPresCOnsFina(ide.getIndPres() != null ? Integer.parseInt(ide.getIndPres()) : null); XXX Nao existe no layout v2.00
			//nfe.setCodTipoProcEmissao(ide.getIndPres() != null ? Integer.parseInt(ide.getIndPres()) : null); XXX Nao existe no layout v2.00				
			nfe.setVersaoProcEmissao(ide.getVerProc());
			
			List<NFref> nFrefList = ide.getNFref();
			if(nFrefList != null && !nFrefList.isEmpty()){
				Long seqDocRef = 0L;
				List<NfeAssocDocRef> tbImpoNfeAssocDocRef = new ArrayList<NfeAssocDocRef>(nFrefList.size());
				for (NFref nFref : nFrefList) {
					NfeAssocDocRef nfeAssocDocRef = new NfeAssocDocRef();
					nfeAssocDocRef.setSeqDocumento(++seqDocRef);
					nfeAssocDocRef.setCodChaveAcesso(nFref.getRefNFe());
					RefNF refNFItem = nFref.getRefNF();
					nfeAssocDocRef.setCnpjEmitente(refNFItem.getCNPJ());
					nfeAssocDocRef.setCodUnidFederativa(refNFItem.getCUF() != null ? Long.parseLong(refNFItem.getCUF()) : null);
					nfeAssocDocRef.setDescAnoMesEmissao(refNFItem.getAAMM());
					nfeAssocDocRef.setNumSerie(refNFItem.getSerie() != null ? Long.parseLong(refNFItem.getSerie()) : null);
					tbImpoNfeAssocDocRef.add(nfeAssocDocRef);					
				}
				nfe.setTbImpoNfeAssocDocRef(tbImpoNfeAssocDocRef);				
			}
		}
		
		//Dados da Transportadora...
		Transp dadosTransp = infNfe.getTransp();
		if(dadosTransp != null) {			
			Transporta transportadora = dadosTransp.getTransporta();
			if(transportadora != null) {
				
				String numDocTransp = transportadora.getCNPJ();
				if(numDocTransp == null || numDocTransp.isEmpty()){
					numDocTransp = transportadora.getCPF();
				}
				nfe.setCpfCnpjTransportadora(numDocTransp);
				nfe.setRazaoSocialTransportadora(transportadora.getXNome());
				nfe.setInscricaoEstadualTransportadora(transportadora.getIE());
				nfe.setEnderecoTransportadora(transportadora.getXEnder());
				nfe.setMunicipioTransportadora(transportadora.getXMun());
				nfe.setSgUfTransportadora(transportadora.getUF() != null ? transportadora.getUF().value() : null);
			}
			nfe.setCodModalidadeFrete(dadosTransp.getModFrete() != null ? Integer.parseInt(dadosTransp.getModFrete()) : null);
			TVeiculo veiculoTransp = dadosTransp.getVeicTransp();
			if(veiculoTransp != null) {
				String placa = veiculoTransp.getPlaca();				
				nfe.setSiglaPlacaTransportadora(PlacaUtils.getSiglaPlaca(placa));
				Long numPlaca = PlacaUtils.getNumPlaca(placa);
				if(numPlaca != null) nfe.setNumPlacaTransportadora(numPlaca.toString());
			}
			
			//Dados de Reboque...
			List<TVeiculo> reboqueList = dadosTransp.getReboque();
			if(reboqueList != null && !reboqueList.isEmpty()){
				Long seqReb = 0L;
				List<NfeAssocReboque> tbImpoNfeAssocReboque = new ArrayList<NfeAssocReboque>(reboqueList.size());
				for (TVeiculo reboque : reboqueList) {
					NfeAssocReboque nfeAssocReboque = new NfeAssocReboque();
					nfeAssocReboque.setPlaca(reboque.getPlaca());
					nfeAssocReboque.setSiglaUnidFederativa(reboque.getUF() != null ? reboque.getUF().value() : null);
					nfeAssocReboque.setSeqReboqueTrans(++seqReb);
					tbImpoNfeAssocReboque.add(nfeAssocReboque);
				}
				nfe.setTbImpoNfeAssocReboque(tbImpoNfeAssocReboque);
			}
			
			//Informações de Volume...
			List<Vol> volumeList = dadosTransp.getVol();
			if(volumeList != null && !volumeList.isEmpty()){
				List<NfeAssocVolume> tbImpoNfeAssocVolume = new ArrayList<NfeAssocVolume>(volumeList.size());
				Long seqVol = 0L;
				for (Vol volume : volumeList) {
					NfeAssocVolume nfeAssocVolume = new NfeAssocVolume();
					nfeAssocVolume.setSeqVolume(++seqVol);
					nfeAssocVolume.setQuantidade(volume.getQVol() != null ? Double.parseDouble(volume.getQVol()) : null);
					nfeAssocVolume.setDescEspecie(volume.getEsp());
					nfeAssocVolume.setDescMarca(volume.getMarca());
					nfeAssocVolume.setCodigo(volume.getNVol());
					nfeAssocVolume.setValorPesoLiquido(volume.getPesoL() != null ? Double.parseDouble(volume.getPesoL()) : null);
					nfeAssocVolume.setValorPesoBruto(volume.getPesoB() != null ? Double.parseDouble(volume.getPesoB()) : null);
					tbImpoNfeAssocVolume.add(nfeAssocVolume);
				}
				nfe.setTbImpoNfeAssocVolume(tbImpoNfeAssocVolume);
			}
		}		
		
		//Arquivos Associados (Produtos)
		List<Det> detList = infNfe.getDet();
		if(detList != null && !detList.isEmpty()){
			Long seqProdServ = 0L;
			List<NfeAssocProdServ> tbNfeAssocProdServ = new ArrayList<NfeAssocProdServ>(detList.size());
			for (Det det : detList) {
				Prod prod = det.getProd();
				if(prod != null) {
					NfeAssocProdServ nfeAssocProdServ = new NfeAssocProdServ();
					nfeAssocProdServ.setSeqProdServ(++seqProdServ);
					nfeAssocProdServ.setCodProdServNfe(prod.getCProd());
					nfeAssocProdServ.setDescProdServNfe(prod.getXProd());
					nfeAssocProdServ.setDescInfoAdicionalProd(det.getInfAdProd());
					nfeAssocProdServ.setCodNCM(prod.getNCM());
					nfeAssocProdServ.setCodCFOPProdServ(prod.getCFOP());
					//nfeAssocProdServ.setCodItemPedidoCompra(prod.getNItemPed()); XXX Nao existe no layout v2.00
					nfeAssocProdServ.setDescUnidComercial(prod.getUCom());
					nfeAssocProdServ.setQtdProdServNfe(prod.getQCom() != null ? Double.parseDouble(prod.getQCom()) : null);
					nfeAssocProdServ.setValorUnitComercializacao(prod.getVUnCom() != null ? Double.parseDouble(prod.getVUnCom()) : null);
					nfeAssocProdServ.setValorTotalBrutoProdServ(prod.getVProd() != null ? Double.parseDouble(prod.getVProd()) : 0.0);
					nfeAssocProdServ.setDescUnidComercTributavel(prod.getUTrib());
					nfeAssocProdServ.setQtdTributavel(prod.getQTrib() != null ? Double.parseDouble(prod.getQTrib()) : null);
					nfeAssocProdServ.setValorUnitTributavel(prod.getVUnTrib() != null ? Double.parseDouble(prod.getVUnTrib()) : null);
					nfeAssocProdServ.setValorTotalFrete(prod.getVFrete() != null ? Double.parseDouble(prod.getVFrete()) : 0.0);
					nfeAssocProdServ.setValorTotalSeguro(prod.getVSeg() != null ? Double.parseDouble(prod.getVSeg()) : 0.0);
					nfeAssocProdServ.setValorDesconto(prod.getVDesc() != null ? Double.parseDouble(prod.getVDesc()) : 0.0);
					nfeAssocProdServ.setValorOutrasDespAcess(0.0); //XXX Nao existe no layout v2.00
					nfeAssocProdServ.setTipoValorItem(1L); //XXX Nao existe no layout v2.00
					//nfeAssocProdServ.setTipoItem(prod.getIndTot() != null ? Long.parseLong(prod.getIndTot()) : null); XXX Nao existe no layout v2.00
					nfeAssocProdServ.setCodPedidoCompra(prod.getXProd());
					tbNfeAssocProdServ.add(nfeAssocProdServ);
				}
			}
			nfe.setTbImpoNfeAssocProdServ(tbNfeAssocProdServ);
		}
		
		Cobr cobr = infNfe.getCobr();
		if(cobr != null) {
			List<Dup> duplicataList = cobr.getDup();
			if(duplicataList != null && !duplicataList.isEmpty()){
				long segDup = 0L;
				List<NfeAssocDuplicata> tbImpoNfeAssocDuplicata = new ArrayList<NfeAssocDuplicata>(duplicataList.size());
				for (Dup duplicata : duplicataList) {
					NfeAssocDuplicata nfeAssocDuplicata = new NfeAssocDuplicata();
					nfeAssocDuplicata.setSeqDuplicata(++segDup);
					nfeAssocDuplicata.setCodigo(duplicata.getNDup());
					nfeAssocDuplicata.setValor(duplicata.getVDup() != null ? Double.parseDouble(duplicata.getVDup()) : null);
					nfeAssocDuplicata.setDataVencimento(DataUtils.getDateAAAA_MM_DD(duplicata.getDVenc()));
					tbImpoNfeAssocDuplicata.add(nfeAssocDuplicata);
				}
				nfe.setTbImpoNfeAssocDuplicata(tbImpoNfeAssocDuplicata);
			}
			
			Fat faturamento = cobr.getFat();
			if(faturamento != null) {				
				nfe.setCodigoFaturamento(faturamento.getNFat());
				nfe.setValorOrigFaturamento(faturamento.getVOrig() != null ? Double.parseDouble(faturamento.getVOrig()) : null);
				nfe.setValorDescFaturamento(faturamento.getVDesc() != null ? Double.parseDouble(faturamento.getVDesc()) : null);
				nfe.setValorLiqFaturamento(faturamento.getVLiq() != null ? Double.parseDouble(faturamento.getVLiq()) : null);
			}
		}
		
		InfAdic infAdic = infNfe.getInfAdic();
		if(infAdic != null) {
			nfe.setInfoComplementar(infAdic.getInfCpl());
			
			List<ObsCont> obsContList = infAdic.getObsCont();
			
			if(obsContList != null && !obsContList.isEmpty()){
				List<NfeAssocInfoContrib> tbImpoNfeAssocInfoContrib = new ArrayList<NfeAssocInfoContrib>(obsContList.size());
				Long seqInfo = 0L;
				for (ObsCont obsCont : obsContList) {
					NfeAssocInfoContrib nfeAssocInfoContrib = new NfeAssocInfoContrib();
					nfeAssocInfoContrib.setNomeAtributoInfo(obsCont.getXCampo());
					nfeAssocInfoContrib.setDescInfo(obsCont.getXTexto());
					nfeAssocInfoContrib.setSeqInfoCont(++seqInfo);
					tbImpoNfeAssocInfoContrib.add(nfeAssocInfoContrib);
				}
				nfe.setTbImpoNfeAssocInfoContrib(tbImpoNfeAssocInfoContrib);	
			}
		}
		
		//Preenche o bloco de Totais...
		Total total = infNfe.getTotal();
		if(total != null) {
			ICMSTot icmsTot = total.getICMSTot();
			if(icmsTot != null){
				nfe.setValorBaseCalcICMS(icmsTot.getVBC() != null ? Double.parseDouble(icmsTot.getVBC()) : null);
				nfe.setValorTotalICMS(icmsTot.getVICMS() != null ? Double.parseDouble(icmsTot.getVICMS()) : null);
				nfe.setValorTotalICMSDes(0.0); //XXX Nao existe no layout v2.00
				nfe.setValorBaseCalcICMSSt(icmsTot.getVBCST() != null ? Double.parseDouble(icmsTot.getVBCST()) : null);
				nfe.setValorTotalICMSSt(icmsTot.getVST() != null ? Double.parseDouble(icmsTot.getVST()) : null);
				nfe.setValorTotalProdServ(icmsTot.getVProd() != null ? Double.parseDouble(icmsTot.getVProd()) : null);
				nfe.setValorTotalFrete(icmsTot.getVFrete() != null ? Double.parseDouble(icmsTot.getVFrete()) : null);
				nfe.setValorTotalSeguro(icmsTot.getVSeg() != null ? Double.parseDouble(icmsTot.getVSeg()) : null);
				nfe.setValorTotalDesc(icmsTot.getVDesc() != null ? Double.parseDouble(icmsTot.getVDesc()) : null);
				nfe.setValorTotalII(icmsTot.getVII() != null ? Double.parseDouble(icmsTot.getVII()) : null);
				nfe.setValorTotalIPI(icmsTot.getVIPI() != null ? Double.parseDouble(icmsTot.getVIPI()) : null);
				nfe.setValorTotalPIS(icmsTot.getVPIS() != null ? Double.parseDouble(icmsTot.getVPIS()) : null);
				nfe.setValorCofins(icmsTot.getVCOFINS() != null ? Double.parseDouble(icmsTot.getVCOFINS()) : null);
				nfe.setValorTotalOutrasDespAces(icmsTot.getVOutro() != null ? Double.parseDouble(icmsTot.getVOutro()) : null);
				nfe.setValorTotalNFe(icmsTot.getVNF() != null ? Double.parseDouble(icmsTot.getVNF()) : null);
				nfe.setValorTotalAproTrib(0.0); //XXX Nao existe no layout v2.00
			}
		}
		
		nfe.setIdLogProcessamento(idLogProcessamento);

		logger.info("NF-e Completa (versao 1.10): parse realizado --------------------------------------------------------");
		
		return registraNFe(nfe);
	}


}
