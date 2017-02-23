package br.com.dominio.venda;

import br.com.dominio.modelo.Produto;

public class Boleto {

	private String banco;

	public Boleto(String banco) {

		this.banco = banco;

	}

	public void emitir(Produto produto, double imposto) {

		if (banco.equalsIgnoreCase("Brasil")) {

			brasil(produto, imposto);

		}

		else if (banco.equalsIgnoreCase("Bradesco")) {

			bradesco(produto, imposto);

		}
		
		else if (banco.equalsIgnoreCase("bmg")) {

			bmg(produto, imposto);

		}

		else {

			caixa(produto, imposto);

		}

	}

	private void bradesco(Produto produto, double imposto) {
		System.out.println("BRADESCO");

		System.out.println("*************************************************************************");

		System.out.println("Descrição: " + produto.getNome());
		System.out.println("Quantidade: " + produto.getQuantidade());
		System.out.println("Valor: R$ " + produto.getValorUnitario());
		System.out.println("Imposto: R$ " + imposto);
		System.out.println("Valor total: R$ " + produto.getValorTotal(imposto));

	}

	private void caixa(Produto produto, double imposto) {

		System.out.println("CAIXA ECONOMICA FEDERAL");

		System.out.println("------------------------------------------------------------------------");

		System.out.println("Descrição: " + produto.getNome());
		System.out.println("Quantidade: " + produto.getQuantidade());
		System.out.println("Valor: R$ " + produto.getValorUnitario());
		System.out.println("Imposto: R$ " + imposto);
		System.out.println("Valor total: R$ " + produto.getValorTotal(imposto));

	}

	private void brasil(Produto produto, double imposto) {

		System.out.println("BANCO DO BRASIL");

		System.out.println("==========================================================================");

		System.out.println("Descrição: " + produto.getNome());
		System.out.println("Quantidade: " + produto.getQuantidade());
		System.out.println("Valor: R$ " + produto.getValorUnitario());
		System.out.println("Imposto: R$ " + imposto);
		System.out.println("Valor total: R$ " + produto.getValorTotal(imposto));

	}
	
	private void bmg(Produto produto, double imposto) {

		System.out.println("BMG");

		System.out.println("==========================================================================");

		System.out.println("Descrição: " + produto.getNome());
		System.out.println("Quantidade: " + produto.getQuantidade());
		System.out.println("Valor: R$ " + produto.getValorUnitario());
		System.out.println("Imposto: R$ " + imposto);
		System.out.println("Valor total: R$ " + produto.getValorTotal(imposto));

	}


}
