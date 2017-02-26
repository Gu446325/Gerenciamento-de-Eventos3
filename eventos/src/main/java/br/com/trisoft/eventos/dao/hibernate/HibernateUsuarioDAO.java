package br.com.trisoft.eventos.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import javax.persistence.NoResultException;

import br.com.trisoft.eventos.dao.UsuarioDAO;
import br.com.trisoft.eventos.model.Usuario;

public class HibernateUsuarioDAO extends HibernateGenericDAO<Usuario, Long>implements UsuarioDAO, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public Usuario porNome(String nome) {
		try {

			return getEntityManager().createNamedQuery("Usuario.porNome", Usuario.class).setParameter("nome", nome)
					.getSingleResult();

		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public Usuario porEmail(String email) {

		Usuario usuario = null;

		try {

			usuario = this.getEntityManager().createNamedQuery("Usuario.porEmail", Usuario.class)
					.setParameter("email", email.toLowerCase()).getSingleResult();

		} catch (NoResultException e) {
			// nenhum usuário encontrado com o e-mail informado
		}

		return usuario;
	}

	@Override
	public List<Usuario> buscarUsuariosPorEmpresa(Long codigo) {
		List<Usuario> usuarios = getEntityManager()
				.createNamedQuery("UsuarioInstituicao.usuariosPorCodigoDaInstituicao", Usuario.class)
				.setParameter("codigo", codigo).getResultList();

		return usuarios;
	}

	public boolean isExisteEmail(String email) {

		try {
			Usuario usuario = getEntityManager().createNamedQuery("Usuario.existenciaEmail", Usuario.class)
					.setParameter("email", email).getSingleResult();

			if (usuario == null) {
				return false;
			}

			return true;

		} catch (Exception e) {
		}

		return false;
	}

	@Override
	public List<Usuario> buscarAdministradores() {
		List<Usuario> usuarios = getEntityManager().createNamedQuery("Usuario.porAdmin", Usuario.class).getResultList();
		return usuarios;
	}

}
