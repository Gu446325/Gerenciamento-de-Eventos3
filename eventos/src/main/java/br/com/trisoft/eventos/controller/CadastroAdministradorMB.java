package br.com.trisoft.eventos.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.trisoft.eventos.model.Grupo;
import br.com.trisoft.eventos.model.Usuario;
import br.com.trisoft.eventos.service.GrupoService;
import br.com.trisoft.eventos.service.NegocioException;
import br.com.trisoft.eventos.service.UsuarioService;
import br.com.trisoft.eventos.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroAdministradorMB implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Usuario usuario;
	
	@Inject
	private GrupoService grupoService;

	@Inject
	private UsuarioService usuarioService;

	
	public void iniciar() {
		if(usuario == null){
			limpar();
		}
	}

	public void salvar() {
		try {

			prepararUsuario();
			usuarioService.salvar(usuario);

			FacesUtil.addInfoMessage("Usuário adicionado com sucesso");
			limpar();

		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	private void prepararUsuario() throws NegocioException {

		List<Grupo> grupos = new ArrayList<>();
		Grupo grupo = grupoService.buscarPorAdministradores();

		grupos.add(grupo);
		usuario.setGrupos(grupos);
	}

	private void limpar() {
		usuario = new Usuario();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	

}
