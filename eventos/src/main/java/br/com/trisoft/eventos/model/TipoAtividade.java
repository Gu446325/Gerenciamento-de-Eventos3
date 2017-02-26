package br.com.trisoft.eventos.model;

public enum TipoAtividade {

	APRESENTACAO("Apresentação"), CURSO("Curso"), MINICURSO("Minicurso"), PALESTRA("Palestra"), SEMINARIO(
			"Seminário"), SIMPOSIO("Simpósio"), OUTRA("Outras");

	private String descricao;

	TipoAtividade(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return this.descricao;
	}

}
