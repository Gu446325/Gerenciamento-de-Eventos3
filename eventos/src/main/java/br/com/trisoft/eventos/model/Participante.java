package br.com.trisoft.eventos.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import br.com.trisoft.eventos.model.relationship.ParticipanteEvento;

@Entity
@Table(name = "participante")
@NamedQueries({
		@NamedQuery(name = "Participante.existenciaEmail", query = "select p from Participante p where p.email = :email"),
		@NamedQuery(name = "Participante.buscarTodos", query = "select p from Participante p where upper(p.nome) like :nome and upper(p.email) like :email order by p.nome"),
		@NamedQuery(name = "Participante.contar", query = "select count(nome) from Participante p where upper(p.nome) like :nome and upper(p.email) like :email"),
		@NamedQuery(name = "Participante.porNome", query = "from Participante where upper(nome) like :nome"),
		@NamedQuery(name="Participante.porEmail", query="select p from Participante p where p.email = :email")})
public class Participante implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private String email;

	private List<ParticipanteEvento> participanteEventoList;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gera_seq")
	@SequenceGenerator(name = "gera_seq", sequenceName = "participante_seq", allocationSize = 1)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotNull
	@Column(name = "nome", nullable = false, length = 50)
	@Size(max = 50, message = "O nome não pode conter mais que 50 caracteres")
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@NotNull
	@Email//(regexp = ".+@.+\\.[A-Za-z]+", message = "Este e-mail é inválido")
	@Column(name = "email", nullable = false, length = 50, unique = true)
	@Size(max = 50, message = "O email não pode conter mais que 50 caracteres")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@OneToMany(mappedBy = "id.participante")
	public List<ParticipanteEvento> getParticipanteEventoList() {
		return participanteEventoList;
	}

	public void setParticipanteEventoList(List<ParticipanteEvento> participanteEventoList) {
		this.participanteEventoList = participanteEventoList;
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
		Participante other = (Participante) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
