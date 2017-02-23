package br.com.dominio.modelo;

import java.math.BigDecimal;
import java.util.List;

import javax.swing.JOptionPane;

public class Pedido {

	private String numeroPedido;
	private Cliente cliente;
	private Vendedor vendedor;
	private List<Produto> produtos;

	public BigDecimal getValorTotal() {

		BigDecimal total = BigDecimal.ZERO;

		for (Produto produto : produtos) {

			total = total.add(produto.getValor().multiply(new BigDecimal(produto.getQuantidade())));

		}

		return total;

	}

	public int getTotalProdutos() {

		int total = 0;

		for (Produto produto : produtos) {

			total += produto.getQuantidade();

		}

		return total;

	}

	// -----------------------------------------------

	public String getNumeroPedido() {
		return numeroPedido;
	}

	public void setNumeroPedido(String numeroPedido) {
		this.numeroPedido = numeroPedido;
	}

	public List<Produto> getprodutos() {
		return produtos;
	}

	public void setprodutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	public void informacoes() {
		JOptionPane.showMessageDialog(null,
				"<html><h1>Nº Pedido: " + numeroPedido + "\n<html><h2>Cliente: " + cliente.getNome()
						+ "\n<html><h2>Vendedor: " + vendedor.getNome() + "\n<html><h2>Produto(s): "
						+ getTotalProdutos() + "\n<html><h2>Valor total: R$ " + getValorTotal() + " \n\n");
	}

}
