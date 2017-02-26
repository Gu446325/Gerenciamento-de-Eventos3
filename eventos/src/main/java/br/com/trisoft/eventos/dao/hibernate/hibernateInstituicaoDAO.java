package br.com.trisoft.eventos.dao.hibernate;

import java.io.Serializable;

import br.com.trisoft.eventos.dao.InstituicaoDAO;
import br.com.trisoft.eventos.model.Instituicao;

public class hibernateInstituicaoDAO extends HibernateGenericDAO<Instituicao, Long>
		implements InstituicaoDAO, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public boolean isExisteCNPJ(String cnpj) {

		try {

			Instituicao instituicao = getEntityManager().createNamedQuery("Instituicao.buscarCNPJ", Instituicao.class)
					.setParameter("cnpj", cnpj).getSingleResult();

			if (instituicao == null) {

				return false;

			} else {

				return true;
			}

		} catch (Exception e) {
			return false;
		}
	}

}
