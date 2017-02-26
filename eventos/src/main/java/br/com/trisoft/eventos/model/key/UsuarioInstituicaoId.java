package br.com.trisoft.eventos.model.key;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import br.com.trisoft.eventos.model.Instituicao;
import br.com.trisoft.eventos.model.Usuario;

@Embeddable
public class UsuarioInstituicaoId implements Serializable {

	private static final long serialVersionUID = 1L;

	private Usuario usuario;
	private Instituicao instituicao;

	// TODO um pra um, pois muitos pra um aqui permite que um usuario pertença a
	// mais de uma empresa
	// Ainda não é certo que funciona. Fase de testes
	@ManyToOne(fetch = FetchType.LAZY)
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	public Instituicao getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((instituicao == null) ? 0 : instituicao.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioInstituicaoId other = (UsuarioInstituicaoId) obj;
		if (instituicao == null) {
			if (other.instituicao != null)
				return false;
		} else if (!instituicao.equals(other.instituicao))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}

}
