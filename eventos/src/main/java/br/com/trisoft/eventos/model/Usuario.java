package br.com.trisoft.eventos.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "usuario")
@NamedQueries({ @NamedQuery(name = "Usuario.porNome", query = "from Usuario where upper(nome) = :nome"),
		@NamedQuery(name = "Usuario.porEmail", query = "from Usuario where lower(email) = :email"),
		@NamedQuery(name = "Usuario.existenciaEmail", query = "select u from Usuario u where u.email = :email"),
		@NamedQuery(name = "Usuario.porAdmin", query = "select u from Usuario u ") })
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private String email;
	private String senha;
	private List<Grupo> grupos = new ArrayList<>();

	private List<Instituicao> instituicao;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gera_seq")
	@SequenceGenerator(name = "gera_seq", sequenceName = "usuario_seq", allocationSize = 1)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotBlank
	@Size(min = 3, max = 80, message = "Nome deve conter entre 3 e 80 caracteres")
	@Column(nullable = false, length = 20)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Email
	@NotBlank
	@Column(nullable = false, length = 70, unique = true)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@NotBlank
	@Size(min = 6, message = "Senha deve conter 6 digitos no mínimo")
	@Column(nullable = false, length = 30)
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@NotNull
	@ManyToMany
	// (cascade = CascadeType.ALL)
	@JoinTable(name = "usuario_grupo", joinColumns = @JoinColumn(name = "usuario_id") , inverseJoinColumns = @JoinColumn(name = "grupo_id") )
	public List<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}

	@ManyToMany
	@JoinTable(name = "usuario_instituicao", joinColumns = @JoinColumn(name = "usuario_id") , inverseJoinColumns = @JoinColumn(name = "instituicao_id") )
	public List<Instituicao> getUsuarioInstituicao() {
		return instituicao;
	}

	public void setUsuarioInstituicao(List<Instituicao> instituicao) {
		this.instituicao = instituicao;
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
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
