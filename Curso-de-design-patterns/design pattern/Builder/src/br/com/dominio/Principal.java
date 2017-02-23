package br.com.dominio;

import java.math.BigDecimal;
import java.util.ArrayList;

import br.com.dominio.modelo.Produto;
import br.com.dominio.modelo.Cliente;
import br.com.dominio.modelo.Pedido;
import br.com.dominio.modelo.Vendedor;

public class Principal {

	public static void main(String[] args) {

		Cliente cliente = new Cliente();

		cliente.setCodigo(100);
		cliente.setNome("Carlos");
		cliente.setTelefone("9254-6733");

		Vendedor vendedor = new Vendedor();

		vendedor.setCodigo(1);
		vendedor.setNome("João");

		Produto produto1 = new Produto();

		produto1.setNome("Caderno");
		produto1.setQuantidade(1);
		produto1.setValor(new BigDecimal(10.00));

		Produto produto2 = new Produto();

		produto2.setNome("Caneta");
		produto2.setQuantidade(2);
		produto2.setValor(new BigDecimal(1.50));

		Pedido pedido = new Pedido();

		pedido.setNumeroPedido("00001");
		pedido.setCliente(cliente);
		pedido.setVendedor(vendedor);
		pedido.setprodutos(new ArrayList<>());
		pedido.getprodutos().add(produto1);
		pedido.getprodutos().add(produto2);

		pedido.informacoes();

	}

}
