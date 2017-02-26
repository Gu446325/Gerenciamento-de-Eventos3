package br.com.trisoft.eventos.dao;

import br.com.trisoft.eventos.model.Grupo;

public interface GrupoDAO extends GenericDAO<Grupo, Long> {

	public Grupo nivelDeAcesso(String nivel);
}
