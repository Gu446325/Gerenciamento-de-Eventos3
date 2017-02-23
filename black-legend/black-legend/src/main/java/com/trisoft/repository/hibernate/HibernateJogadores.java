package com.trisoft.repository.hibernate;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.trisoft.model.Jogador;
import com.trisoft.repository.Jogadores;
import com.trisoft.service.NegocioException;

import teste.util.print.Print;

public class HibernateJogadores implements Jogadores {

	@Inject
	private EntityManager manager;

	@SuppressWarnings("unchecked")
	public List<Jogador> filtrarJogador(Jogador jogador) {
		Print.value("filtrarJogador", jogador.getNome());

		Session session = manager.unwrap(Session.class);
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

	public void salvar(Jogador jogador) {
		Print.value("salvar", jogador.getNome());
		manager.merge(jogador);
	}

	@SuppressWarnings("unchecked")
	public List<Jogador> buscarTodos() {
		Print.value("buscarTodos", "Lista");
		Session session = manager.unwrap(Session.class);

		Criteria criteria = session.createCriteria(Jogador.class);
		return criteria.list();
	}
	
	public void remover(Long id) throws NegocioException {
		try {
			Jogador jogador = buscarPeloCodigo(id);

			manager.remove(jogador);
			manager.flush();

		} catch (PersistenceException e) {
			throw new NegocioException("Falha na exclusão");
		}

	}
	
	public Jogador buscarPeloCodigo(Long id) {
		return manager.find(Jogador.class, id);
	}

}
