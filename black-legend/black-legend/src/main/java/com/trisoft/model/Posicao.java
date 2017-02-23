package com.trisoft.model;

public enum Posicao {

	GK("Goleiro"), LT("Lateral"), ZG("Zagueiro"), MC("Meio campo"), AT("Atacante");

	private String descricao;

	Posicao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return this.descricao;
	}
}
