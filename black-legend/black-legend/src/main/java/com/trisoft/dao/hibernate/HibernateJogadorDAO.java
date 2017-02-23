package com.trisoft.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.trisoft.dao.JogadorDAO;
import com.trisoft.model.Jogador;

public class HibernateJogadorDAO extends HibernateGenericDAO<Jogador, Long>implements JogadorDAO, Serializable {

	private static final long serialVersionUID = 1L;

	/*
	 * Criamos aqui a implementação do método que não é genérico, ou seja, não
	 * pode pertencer a todas as classes como salvar, remover, buscar,etc. A
	 * classe de serviço NÃO acessa este método, ela irá acessá-lo através da
	 * interface JogadorDAO
	 */
	@SuppressWarnings("unchecked")
	public List<Jogador> filtrarJogador(Jogador jogador) {
		Session session = getEntityManager().unwrap(Session.class);
		Criteria criteria = session.createCriteria(Jogador.class);

		if (jogador.getNome() != null && !jogador.getNome().trim().equals("")) {
			criteria.add(Restrictions.ilike("nome", jogador.getNome(), MatchMode.ANYWHERE));
		}

		if (jogador.getSalario() != null) {
			criteria.add(Restrictions.ge("salario", jogador.getSalario()));
		}

		criteria.add(Restrictions.eq("integrado", jogador.isIntegrado()));

		return criteria.addOrder(Order.asc("nome")).list();
	}



	/**
	 * Filtro com JPQL
	 */
	// public List<Jogador> buscarComFiltro(String nome) {
	// String jpql = "from Jogador where upper(nome) like :nome";
	// List<Jogador> lista = getEntityManager().createQuery(jpql, Jogador.class)
	// .setParameter("nome", "%" + nome.toUpperCase() + "%").getResultList();
	// return lista;
	// }
}
