package com.trisoft.dao;

import java.util.List;

import com.trisoft.model.Jogador;

public interface JogadorDAO extends GenericDAO<Jogador, Long> {

	/*
	 * A classe de serviço acessa esta interface. A implementação do método fica
	 * em HibernateJogadorDAO.
	 */
	public List<Jogador> filtrarJogador(Jogador jogador);

}
