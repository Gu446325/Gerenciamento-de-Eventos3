package br.com.trisoft.eventos.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.trisoft.eventos.dao.UsuarioDAO;
import br.com.trisoft.eventos.model.Grupo;
import br.com.trisoft.eventos.model.Usuario;
import br.com.trisoft.eventos.util.cdi.CDIServiceLocator;

/**
 * 
 * @author luiz
 * 
 * 
 * 
 * Classe usa interface UserDetailsService do spring que fornece os detalhes de usuarios. 
 *
 */

public class AppUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UsuarioDAO usuarios = CDIServiceLocator.getBean(UsuarioDAO.class);
		Usuario usuario = usuarios.porEmail(email);

		UsuarioSistema user = null;

		if (usuario != null) {
			user = new UsuarioSistema(usuario, getGrupos(usuario));
		}

		return user;
	}

	private Collection<? extends GrantedAuthority> getGrupos(Usuario usuario) {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();

		for (Grupo grupo : usuario.getGrupos()) {
			authorities.add(new SimpleGrantedAuthority(grupo.getNome().toUpperCase()));
		}

		return authorities;
	}

}
