package br.com.trisoft.eventos.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import br.com.trisoft.eventos.dao.ParticipanteEventoDAO;
import br.com.trisoft.eventos.model.Evento;
import br.com.trisoft.eventos.model.key.ParticipanteEventoId;
import br.com.trisoft.eventos.model.relationship.ParticipanteEvento;

public class HibernateParticipanteEventoDAO extends HibernateGenericDAO<ParticipanteEvento, Long>
		implements ParticipanteEventoDAO, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public ParticipanteEvento buscarInscricao(ParticipanteEventoId id) {
		return getEntityManager().find(ParticipanteEvento.class, id);
	}

	@Override
	public void removerParticipanteInscrito(ParticipanteEvento participanteEvento) {
		getEntityManager()
				.remove(getEntityManager().getReference(ParticipanteEvento.class, participanteEvento.getId()));
	}

	@Override
	public List<ParticipanteEvento> buscarInscricaoComPaginacao(int first, int pageSize, Evento evento) {
		List<ParticipanteEvento> lista = getEntityManager()
				.createNamedQuery("ParticipanteEvento.buscarTodos", ParticipanteEvento.class)
				.setParameter("Evento", evento).setFirstResult(first).setMaxResults(pageSize).getResultList();
		return lista;
	}

	@Override
	public Long encontrarInscricaoQuantidade(Evento evento) {
		Long count = getEntityManager().createNamedQuery("ParticipanteEvento.contar", Long.class)
				.setParameter("Evento", evento).getSingleResult();
		return count;
	}

}
