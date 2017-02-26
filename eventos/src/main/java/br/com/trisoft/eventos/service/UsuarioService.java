package br.com.trisoft.eventos.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.trisoft.eventos.dao.UsuarioDAO;
import br.com.trisoft.eventos.model.Usuario;
import br.com.trisoft.eventos.util.jpa.Transactional;

public class UsuarioService implements Serializable, Service<Usuario> {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioDAO usuarioDAO;

	@Override
	@Transactional
	public void salvar(Usuario usuario) throws NegocioException {

		if (usuarioDAO.isExisteEmail(usuario.getEmail()) == true) {
			throw new NegocioException("Este e-mail já está cadastrado");
		}

		usuarioDAO.salvar(usuario);
	}

	@Transactional
	public Usuario salvarComRetorno(Usuario usuario) throws NegocioException {

		if (usuarioDAO.isExisteEmail(usuario.getEmail().toLowerCase())) {
			throw new NegocioException("Já existe usuário com este e-mail");
		}

		return usuarioDAO.salvarComRetorno(usuario);

	}

	@Override
	@Transactional
	public void remover(Long id) throws NegocioException {
		usuarioDAO.remover(id);
	}

	@Override
	public List<Usuario> buscarTodos() {
		return usuarioDAO.buscarTodos();
	}

	@Override
	public Usuario buscarPeloCodigo(Long id) {
		return usuarioDAO.buscarPeloCodigo(id);
	}

	public List<Usuario> buscarUsuarios(Long codigo) {
		return usuarioDAO.buscarUsuariosPorEmpresa(codigo);
	}

	public Usuario buscarPorEmail(String email) {
		return usuarioDAO.porEmail(email);
	}

	public List<Usuario> buscarAdministradores() {
		return usuarioDAO.buscarAdministradores();
	}

}
