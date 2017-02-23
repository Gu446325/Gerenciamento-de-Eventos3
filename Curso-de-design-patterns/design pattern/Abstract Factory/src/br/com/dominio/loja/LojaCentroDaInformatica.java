package br.com.dominio.loja;

import br.com.dominio.boleto.Boleto;
import br.com.dominio.boleto.BoletoBancoBrasil;
import br.com.dominio.nfe.NFE;
import br.com.dominio.nfe.NFESaoPaulo;
import br.com.dominio.venda.VendaFactory;

public class LojaCentroDaInformatica implements VendaFactory{

	@Override
	public NFE criarNFE() {
		
		NFE notaFiscal = new NFESaoPaulo();
		
		return notaFiscal;
	}

	@Override
	public Boleto criarBoleto() {
		
		Boleto boleto = new BoletoBancoBrasil();		
		
		return boleto;
	}

}
