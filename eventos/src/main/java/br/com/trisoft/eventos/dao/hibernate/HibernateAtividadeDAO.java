package br.com.trisoft.eventos.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import br.com.trisoft.eventos.dao.AtividadeDAO;
import br.com.trisoft.eventos.model.Atividade;
import br.com.trisoft.eventos.model.Evento;

public class HibernateAtividadeDAO extends HibernateGenericDAO<Atividade, Long>implements AtividadeDAO, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public List<Atividade> buscarComPaginacaoPorEvento(Evento evento, int first, int pageSize) {

		List<Atividade> atividades = getEntityManager()
				.createNamedQuery("Atividade.buscarTodosPorEvento", Atividade.class).setParameter("Evento", evento)
				.setFirstResult(first).setMaxResults(pageSize).getResultList();
		return atividades;
	}

	@Override
	public Long encontrarQuantidadePorEvento(Evento evento) {
		Long count = getEntityManager().createNamedQuery("Atividade.contarPorEvento", Long.class)
				.setParameter("Evento", evento).getSingleResult();
		return count;
	}

	
}
