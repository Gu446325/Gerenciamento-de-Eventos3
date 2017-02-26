package br.com.trisoft.eventos.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import br.com.trisoft.eventos.model.Usuario;

/**
 * 
 * @author luiz
 *
 *         Repositório de usuários
 *
 *         Classe pra representar um usuario herdando de
 *         User(org.springframework.security.core.userdetails.User). Quando
 *         logado vai armazenar na sessao e na memoria.
 */

public class UsuarioSistema extends User {

	private static final long serialVersionUID = 1L;

	private Usuario usuario;

	public UsuarioSistema(Usuario usuario, Collection<? extends GrantedAuthority> authorities) {
		super(usuario.getEmail(), usuario.getSenha(), authorities);
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

}