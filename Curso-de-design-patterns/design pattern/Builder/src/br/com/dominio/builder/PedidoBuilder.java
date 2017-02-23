package br.com.dominio.builder;

import java.math.BigDecimal;
import java.util.ArrayList;

import br.com.dominio.modelo.Cliente;
import br.com.dominio.modelo.Pedido;
import br.com.dominio.modelo.Produto;
import br.com.dominio.modelo.Vendedor;

public class PedidoBuilder {

	private Pedido instancia;

	public PedidoBuilder() {
		this.instancia = new Pedido();
	}

	public PedidoBuilder setPedido(String numeroPedido) {

		instancia.setNumeroPedido(numeroPedido);

		return this;

	}

	public PedidoBuilder setCliente(int codigo, String nome, String telefone) {

		Cliente cliente = new Cliente();
		cliente.setCodigo(codigo);
		cliente.setNome(nome);
		cliente.setTelefone(telefone);

		instancia.setCliente(cliente);

		return this;

	}

	public PedidoBuilder setVendedor(int codigo, String nome) {

		Vendedor vendedor = new Vendedor();
		vendedor.setCodigo(codigo);
		vendedor.setNome(nome);

		instancia.setVendedor(vendedor);

		return this;

	}

	public PedidoBuilder setProduto(String nome, int quantidade, BigDecimal valor) {

		Produto produto = new Produto();
		produto.setNome(nome);
		produto.setQuantidade(quantidade);
		produto.setValor(valor);

		if (instancia.getprodutos() == null) {
			instancia.setprodutos(new ArrayList<>());
		}

		instancia.getprodutos().add(produto);

		return this;

	}

	public Pedido builder() {

		return instancia;

	}

}
