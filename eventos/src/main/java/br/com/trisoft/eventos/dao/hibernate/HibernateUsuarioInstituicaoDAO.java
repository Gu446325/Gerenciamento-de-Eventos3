package br.com.trisoft.eventos.dao.hibernate;

import java.io.Serializable;

import br.com.trisoft.eventos.dao.UsuarioInstituicaoDAO;
import br.com.trisoft.eventos.model.key.UsuarioInstituicaoId;
import br.com.trisoft.eventos.model.relationship.UsuarioInstituicao;

public class HibernateUsuarioInstituicaoDAO extends HibernateGenericDAO<UsuarioInstituicao, Long>
		implements UsuarioInstituicaoDAO, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public UsuarioInstituicao buscarPeloCodigo(UsuarioInstituicaoId id) {
		return getEntityManager().find(UsuarioInstituicao.class, id);
	}

	@Override
	public Long buscarCodigoDaInstituicao(Long idUsuario) {
		return getEntityManager().createNamedQuery("UsuarioInstituicao.instituicaoPorCodigoDoUsuario", Long.class)
				.setParameter("codigo", idUsuario).getSingleResult();
	}

}
