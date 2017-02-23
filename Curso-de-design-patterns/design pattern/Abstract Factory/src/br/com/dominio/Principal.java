package br.com.dominio;

import java.math.BigDecimal;

import br.com.dominio.loja.LojaCentroDaInformatica;
import br.com.dominio.modelo.Produto;
import br.com.dominio.venda.Venda;
import br.com.dominio.venda.VendaFactory;

public class Principal {

	public static void main(String[] args) {
		
		Produto produto = new Produto();
		
		produto.setNome("Smartphone Moto X 2ª geração");
		produto.setQuantidade(1);
		produto.setValorUnitario(new BigDecimal(1000));
		
		VendaFactory factory = new LojaCentroDaInformatica();
		
		Venda venda = new Venda(factory);
		
		venda.realizarVenda(produto);
		
	}
	
}
