package br.com.trisoft.eventos.dao;

import java.util.List;

import br.com.trisoft.eventos.model.Usuario;

public interface UsuarioDAO extends GenericDAO<Usuario, Long> {

	public Usuario porNome(String nome);

	public Usuario porEmail(String email);

	public List<Usuario> buscarUsuariosPorEmpresa(Long codigo);

	public boolean isExisteEmail(String email);

	public List<Usuario> buscarAdministradores();

}
