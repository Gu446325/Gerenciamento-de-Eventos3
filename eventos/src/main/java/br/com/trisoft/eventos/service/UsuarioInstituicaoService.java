package br.com.trisoft.eventos.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.trisoft.eventos.dao.UsuarioInstituicaoDAO;
import br.com.trisoft.eventos.model.Instituicao;
import br.com.trisoft.eventos.model.Usuario;
import br.com.trisoft.eventos.model.key.UsuarioInstituicaoId;
import br.com.trisoft.eventos.model.relationship.UsuarioInstituicao;
import br.com.trisoft.eventos.util.jpa.Transactional;

public class UsuarioInstituicaoService implements Serializable, Service<UsuarioInstituicao> {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioInstituicaoDAO instituicaoDAO;

	@Override
	@Transactional
	public void salvar(UsuarioInstituicao instituicao) throws NegocioException {
		instituicaoDAO.salvar(instituicao);
	}

	@Override
	@Transactional
	public void remover(Long id) throws NegocioException {
		instituicaoDAO.remover(id);
	}

	@Override
	public List<UsuarioInstituicao> buscarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UsuarioInstituicao buscarPeloCodigo(Long id) {
		return instituicaoDAO.buscarPeloCodigo(id);
	}

	@Transactional
	public UsuarioInstituicao buscarPeloCodigo(UsuarioInstituicaoId id) {
		return instituicaoDAO.buscarPeloCodigo(id);
	}

	public Long buscarCodigoDaInstituicao(Long idUsuario) {
		return instituicaoDAO.buscarCodigoDaInstituicao(idUsuario);
	}

}
