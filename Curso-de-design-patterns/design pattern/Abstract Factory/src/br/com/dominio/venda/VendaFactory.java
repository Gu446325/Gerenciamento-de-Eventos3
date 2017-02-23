package br.com.dominio.venda;

import br.com.dominio.boleto.Boleto;
import br.com.dominio.nfe.NFE;

public interface VendaFactory {
	
	public NFE criarNFE();
	
	public Boleto criarBoleto();

}
