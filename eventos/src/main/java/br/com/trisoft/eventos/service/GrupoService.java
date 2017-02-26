package br.com.trisoft.eventos.service;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import br.com.trisoft.eventos.dao.GrupoDAO;
import br.com.trisoft.eventos.model.Grupo;
import br.com.trisoft.eventos.model.NivelAcesso;

public class GrupoService implements Serializable, Service<Grupo> {

	private static final long serialVersionUID = 1L;

	@Inject
	private GrupoDAO grupoDAO;

	@Override
	public void salvar(Grupo t) throws NegocioException, SQLException {

	}

	@Override
	public void remover(Long id) throws NegocioException {

	}

	@Override
	public List<Grupo> buscarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Grupo buscarPeloCodigo(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Grupo buscarPorEmpresa() throws NegocioException {
		return grupoDAO.nivelDeAcesso(NivelAcesso.EMPRESAS.getDescricao());
	}

	public Grupo buscarPorParticipante() {
		return grupoDAO.nivelDeAcesso(NivelAcesso.PARTICIPANTES.getDescricao());
	}

	public Grupo buscarPorAdministradores() {
		return grupoDAO.nivelDeAcesso(NivelAcesso.ADMINISTRADORES.getDescricao());
	}

}
