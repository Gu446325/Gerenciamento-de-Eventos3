package br.com.trisoft.eventos.controller;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.trisoft.eventos.model.Usuario;
import br.com.trisoft.eventos.service.NegocioException;
import br.com.trisoft.eventos.service.UsuarioService;
import br.com.trisoft.eventos.util.jsf.FacesUtil;

@Named
@SessionScoped
public class PesquisaAdministradorMB implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Usuario usuario;

	@Inject
	private UsuarioService usuarioService;

	private List<Usuario> usuarios;

	public void iniciar() {

		if (usuario == null) {
			limpar();
		}

		consultar();
	}

	public void remover() throws NegocioException {
		try {

			if (usuarios.size() > 1) {

				usuarioService.remover(usuario.getId());
				FacesUtil.addInfoMessage("Usuário removido com sucesso");
				consultar();

			} else {
				FacesUtil.addWarnMessage("Você não pode ficar sem usuários no sistema!");
			}

		} catch (Exception e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	private void limpar() {
		usuario = new Usuario();
	}

	private void consultar() {
		// usuarios = usuarioGrupoService.buscarAdministradores();
		usuarios = usuarioService.buscarTodos();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

}
