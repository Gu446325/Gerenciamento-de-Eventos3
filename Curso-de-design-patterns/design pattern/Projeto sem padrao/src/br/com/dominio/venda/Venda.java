package br.com.dominio.venda;

import br.com.dominio.modelo.Produto;

public class Venda {

	private Produto produto;
	private Boleto boleto;
	private NFE notaFiscal;

	public Venda(Produto produto, Boleto boleto, NFE notaFiscal) {

		this.produto = produto;
		
		this.boleto = boleto;

		this.notaFiscal = notaFiscal;

	}
	
	public void realizarVenda(){
		
		double imposto = notaFiscal.calcularImposto();
	
		boleto.emitir(produto, imposto);;
		
	}

}
