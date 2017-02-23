package br.com.dominio.service;

import java.math.BigDecimal;

import br.com.dominio.model.Produto;

public class CalculoPrecoComum implements CalculoPreco {

	@Override
	public BigDecimal valorFinal(Produto produto) {

		BigDecimal imposto = new BigDecimal("0.10");
		BigDecimal valorFinal = produto.getValorUnitario().add(produto.getValorUnitario().multiply(imposto));

		return valorFinal;

	}

}
