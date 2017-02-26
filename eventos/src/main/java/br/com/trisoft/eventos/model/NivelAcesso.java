package br.com.trisoft.eventos.model;

public enum NivelAcesso {

	PARTICIPANTES("PARTICIPANTES"), EMPRESAS("EMPRESAS"), ADMINISTRADORES("ADMINISTRADORES");
	
	private String descricao;

	NivelAcesso(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return this.descricao;
	}

}
