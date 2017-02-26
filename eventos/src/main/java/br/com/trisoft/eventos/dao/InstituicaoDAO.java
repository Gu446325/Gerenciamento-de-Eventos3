package br.com.trisoft.eventos.dao;

import br.com.trisoft.eventos.model.Instituicao;

public interface InstituicaoDAO extends GenericDAO<Instituicao, Long> {

	public boolean isExisteCNPJ(String cnpj);

}
