package br.com.trisoft.eventos.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "grupo")
@NamedQueries({ @NamedQuery(name = "Grupo.nivelAcesso", query = "from Grupo g where g.nome = :nivel") })
public class Grupo implements Serializable {

	private static final long serialVersionUID = 1L;

	//@GeneratedValue

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gera_seq")
	@SequenceGenerator(name = "gera_seq", sequenceName = "grupo_seq", allocationSize = 1)
	private Long id;

	@Column(nullable = false, length = 20, unique = true)
	private String nome;

	@Column(nullable = true, length = 80)
	private String descricao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	// public List<Usuario> getUsuarios() {
	// return usuarios;
	// }
	//
	// public void setUsuarios(List<Usuario> usuarios) {
	// this.usuarios = usuarios;
	// }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		Grupo other = (Grupo) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
