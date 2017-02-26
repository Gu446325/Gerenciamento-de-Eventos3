package br.com.trisoft.eventos.dao.hibernate;

import java.io.Serializable;

import br.com.trisoft.eventos.dao.GrupoDAO;
import br.com.trisoft.eventos.model.Grupo;

public class HibernateGrupoDAO extends HibernateGenericDAO<Grupo, Long>implements GrupoDAO, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public Grupo nivelDeAcesso(String nivel) {
		Grupo grupo = getEntityManager().createNamedQuery("Grupo.nivelAcesso", Grupo.class)
				.setParameter("nivel", nivel.toUpperCase()).getSingleResult();
		return grupo;
	}

}
