package br.com.trisoft.eventos.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.trisoft.eventos.model.Grupo;
import br.com.trisoft.eventos.model.Instituicao;
import br.com.trisoft.eventos.model.Usuario;
import br.com.trisoft.eventos.model.relationship.UsuarioInstituicao;
import br.com.trisoft.eventos.service.GrupoService;
import br.com.trisoft.eventos.service.NegocioException;
import br.com.trisoft.eventos.service.UsuarioInstituicaoService;
import br.com.trisoft.eventos.service.UsuarioService;
import br.com.trisoft.eventos.service.validationobject.InstituicaoServiceVO;
import br.com.trisoft.eventos.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaUsuarioMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Usuario> usuarios;

	@Inject
	private Usuario usuario;

	@Inject
	private UsuarioService usuarioService;

	public void iniciar() {

		if (usuario == null) {
			limpar();
		}

		consultar();
	}

	// @Inject
	// private InstituicaoServiceVO serviceVO;

	@Inject
	private UsuarioInstituicaoService usuarioInstituicaoService;
	@Inject
	private GrupoService grupoService;
	private Grupo grupo;
	private List<Grupo> grupos;

	public void adicionar() throws NegocioException {

		try {

			prepararUsuario();
			prepararRelacionamento();

			FacesUtil.addInfoMessage("Usuário adicionado com sucesso");

			limpar();
			consultar();

		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	private void prepararUsuario() throws NegocioException {

		grupos = new ArrayList<>();
		grupo = grupoService.buscarPorEmpresa();
		grupos.add(grupo);
		usuario.setGrupos(grupos);

		usuario = usuarioService.salvarComRetorno(usuario);
	}

	private void prepararRelacionamento() throws NegocioException {
		Instituicao instituicao = instituicaoVO.getInstituicao();

		UsuarioInstituicao ui = new UsuarioInstituicao();
		ui.adicionarRelacionamento(instituicao, usuario);
		usuarioInstituicaoService.salvar(ui);
	}

	public void remover() throws NegocioException {

		try {

			if (usuarios.size() > 1) {

				usuarioService.remover(usuario.getId());
				
				FacesUtil.addInfoMessage("O usuário foi removido");
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

	@Inject
	private InstituicaoServiceVO instituicaoVO;

	private void consultar() {
		Long codigoInstituicao = instituicaoVO.getInstituicao().getId();
		usuarios = usuarioService.buscarUsuarios(codigoInstituicao);
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
