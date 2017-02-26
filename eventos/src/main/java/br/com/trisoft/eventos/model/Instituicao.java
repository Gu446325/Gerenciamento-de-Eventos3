package br.com.trisoft.eventos.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.br.CNPJ;

@Entity
@Table(name = "instituicao")
@NamedQueries({ @NamedQuery(name = "Instituicao.buscarCNPJ", query = "from Instituicao i where i.cnpj = :cnpj") })
public class Instituicao implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private String email;
	private String cnpj;
	private String endereco;
	private String cidade;
	private Estado estado = Estado.AC;

	private byte[] logotipo;
	private byte[] assinatura;
	private Boolean ativo = true;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gera_seq")
	@SequenceGenerator(name = "gera_seq", sequenceName = "instituicao_seq2", allocationSize = 1)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotNull
	@Column(name = "nome", nullable = false, length = 80)
	@Size(max = 80, message = "O nome não pode conter mais que 80 caracteres")
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Size(max = 50, message = "O email não pode conter mais que 50 caracteres")
	@Email(regexp = ".+@.+\\.[A-Za-z]+", message = "Este e-mail é inválido")
	@Column(name = "email", nullable = false)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@CNPJ
	@Column(name = "cnpj", nullable = false, unique = true)
	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	@Column(name = "endereco", nullable = false)
	@Size(max = 50, message = "O endereço não pode conter mais que 50 caracteres")
	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	@Column(name = "cidade", nullable = false)
	@Size(max = 50, message = "A cidade não pode conter mais que 50 caracteres")
	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	@Column(name = "uf")
	@Enumerated(EnumType.STRING)
	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	@Column(name = "logotipo")
	@NotNull(message = "Selecione um logotipo em formato de imagem (jpg, png)")
	public byte[] getLogotipo() {
		return logotipo;
	}

	public void setLogotipo(byte[] logotipo) {
		this.logotipo = logotipo;
	}

	@Column(name = "assinatura")
	@NotNull(message = "Selecione uma assinatura em formato de imagem (jpg, png)")
	public byte[] getAssinatura() {
		return assinatura;
	}

	public void setAssinatura(byte[] assinatura) {
		this.assinatura = assinatura;
	}

	public boolean hasAssinatura() {
		return this.assinatura != null && this.assinatura.length > 0;
	}

	// TODO se for falso não permite a emissão de certificado
	@Column(name = "ativo", nullable = false)
	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
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
		Instituicao other = (Instituicao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
