package br.com.trisoft.eventos.model.relationship;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.trisoft.eventos.model.Instituicao;
import br.com.trisoft.eventos.model.Usuario;
import br.com.trisoft.eventos.model.key.UsuarioInstituicaoId;

@Entity
@Table(name = "usuario_instituicao")
@NamedQueries({
		@NamedQuery(name = "UsuarioInstituicao.instituicaoPorCodigoDoUsuario", query = "select u.id.instituicao.id from UsuarioInstituicao u where usuario_id = :codigo"),
		@NamedQuery(name="UsuarioInstituicao.usuariosPorCodigoDaInstituicao",query="select u.id.usuario from UsuarioInstituicao u where instituicao_id = :codigo order by u.id.usuario.nome")})
public class UsuarioInstituicao implements Serializable {

	private static final long serialVersionUID = 1L;

	private UsuarioInstituicaoId id;

	@EmbeddedId
	public UsuarioInstituicaoId getId() {
		return id;
	}

	public void setId(UsuarioInstituicaoId id) {
		this.id = id;
	}

	public void adicionarRelacionamento(Instituicao instituicao, Usuario usuario) {

		UsuarioInstituicaoId id = new UsuarioInstituicaoId();
		id.setInstituicao(instituicao);
		id.setUsuario(usuario);

		this.setId(id);

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		UsuarioInstituicao other = (UsuarioInstituicao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
