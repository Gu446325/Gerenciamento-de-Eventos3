package br.com.trisoft.eventos.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import br.com.trisoft.eventos.dao.ParticipanteDAO;
import br.com.trisoft.eventos.model.Participante;

public class HibernateParticipanteDAO extends HibernateGenericDAO<Participante, Long>
		implements ParticipanteDAO, Serializable {

	private static final long serialVersionUID = 1L;

	public boolean isExisteEmail(String email) {

		try {
			Participante participante = getEntityManager()
					.createNamedQuery("Participante.existenciaEmail", Participante.class).setParameter("email", email)
					.getSingleResult();

			if (participante == null) {
				return false;
			}

			return true;

		} catch (Exception e) {
		}

		return false;
	}

	@Override
	public List<Participante> buscarParticipanteComPaginacao(int first, int pageSize, Participante participante) {

		String nome = likeBothUpperCase(participante.getNome());
		String email = likeBothUpperCase(participante.getEmail());

		List<Participante> lista = getEntityManager().createNamedQuery("Participante.buscarTodos", Participante.class)
				.setParameter("nome", nome).setParameter("email", email).setFirstResult(first).setMaxResults(pageSize)
				.getResultList();

		return lista;
	}

	@Override
	public Long encontrarParticipanteQuantidade(Participante participante) {

		String nome = likeBothUpperCase(participante.getNome());
		String email = likeBothUpperCase(participante.getEmail());

		Long count = getEntityManager().createNamedQuery("Participante.contar", Long.class).setParameter("nome", nome)
				.setParameter("email", email).getSingleResult();

		return count;
	}

	@Override
	public Participante porEmail(String email) {
		Participante participante = getEntityManager().createNamedQuery("Participante.porEmail", Participante.class)
				.setParameter("email", email.toLowerCase()).getSingleResult();
		return participante;
	}

}
