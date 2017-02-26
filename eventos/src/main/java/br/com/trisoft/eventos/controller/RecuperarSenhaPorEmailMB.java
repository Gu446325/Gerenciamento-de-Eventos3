package br.com.trisoft.eventos.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.outjected.email.api.MailMessage;

import br.com.trisoft.eventos.model.Usuario;
import br.com.trisoft.eventos.service.UsuarioService;
import br.com.trisoft.eventos.util.jsf.FacesUtil;
import br.com.trisoft.eventos.util.mail.Mailer;

@Named
@ViewScoped
public class RecuperarSenhaPorEmailMB implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Mailer mailer;

	@Inject
	private Usuario usuario;

	@Inject
	private UsuarioService service;

	public void iniciar() {
		if (usuario == null) {
			limpar();
		}
	}

	private void limpar() {
		usuario = new Usuario();
	}

	public void senha() {

		usuario = service.buscarPorEmail(usuario.getEmail().toLowerCase());

		if (usuario != null) {
			MailMessage message = mailer.novaMensagem();

			message.to(this.usuario.getEmail()).subject("TriSoft Eventos")
					.bodyHtml("<strong>senha: </strong> " + this.usuario.getSenha()).send();

			FacesUtil.addInfoMessage("Confira seu e-mail para ter acesso a sua senha");

		} else {

			FacesUtil.addErrorMessage("Este e-mail não foi encontrado no sistema");

		}

		limpar();

	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
