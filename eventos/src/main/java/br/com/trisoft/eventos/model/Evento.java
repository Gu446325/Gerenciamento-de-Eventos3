package br.com.trisoft.eventos.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import br.com.trisoft.eventos.model.relationship.ParticipanteAtividade;
import br.com.trisoft.eventos.model.relationship.ParticipanteEvento;

@Entity
@Table(name = "evento")
@NamedQueries({
		@NamedQuery(name = "Evento.buscarTodos", query = "select e from Evento e where upper(e.nome) like :nome and instituicao_id = :codeinstituicao order by e.nome"),
		@NamedQuery(name = "Evento.contar", query = "select count(e) from Evento e where upper(e.nome) like :nome and instituicao_id = :codeinstituicao"),
		@NamedQuery(name = "Evento.buscarTodosAbertos", query = "select e from Evento e where e.aberto = true"),
		@NamedQuery(name = "Evento.contarAbertos", query = "select count(e) from Evento e where e.aberto = true"),
		@NamedQuery(name = "Evento.buscarPorInstituicao", query = "select e from Evento e where instituicao_id = :codigoInstituicao order by e.nome"),
		@NamedQuery(name = "Evento.porCodigoDoUsuario", query = "select e from Evento e where id = :codEvento and instituicao_id = (select ui.id.instituicao.id from UsuarioInstituicao ui  where usuario_id = :codUsuario)") })
public class Evento implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private BigDecimal custo = BigDecimal.ZERO;
	private String local;
	private String cidade;
	private Estado estado;
	private Boolean aberto = false;
	private Date dataDeInicio;
	private Date dataDeTermino;
	private List<Atividade> atividades;

	private Status status = Status.PENDENTE;

	private List<ParticipanteEvento> participanteEventoList;
	private List<ParticipanteAtividade> participanteAtividadeList;

	private Instituicao instituicao;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gera_seq")
	@SequenceGenerator(name = "gera_seq", sequenceName = "evento_seq", allocationSize = 1)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotNull(message = "O nome do evento deve ser preenchido")
	@Column(name = "nome", nullable = false, length = 100)
	@Size(max = 100, message = "O nome não pode conter mais que 100 caracteres")
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "custo", nullable = false)
	@DecimalMin(value = "0.00", message = "O valor mínimo do custo é R$ 0,00")
	@NotNull(message = "O custo não pode ser nulo, defina o valor mínimo de 0,00")
	public BigDecimal getCusto() {
		return custo;
	}

	public void setCusto(BigDecimal custo) {
		this.custo = custo;
	}

	@NotNull
	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@NotBlank(message = "O local do evento deve ser preenchido")
	@Column(name = "local", nullable = false)
	@Size(max = 80, message = "O local não pode conter mais que 80 caracteres")
	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	@NotBlank(message = "O nome da cidade deve ser preenchido")
	@Column(name = "cidade", nullable = false)
	@Size(max = 80, message = "A cidade não pode conter mais que 80 caracteres")
	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "estado", nullable = false)
	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	@NotNull
	@Column(name = "aberto")
	public Boolean getAberto() {
		return aberto;
	}

	public void setAberto(Boolean aberto) {
		this.aberto = aberto;
	}

	@NotNull(message = "Defina uma data de ínicio para o evento")
	@Temporal(TemporalType.DATE)
	@Column(name = "data_inicio")
	public Date getDataDeInicio() {
		return dataDeInicio;
	}

	public void setDataDeInicio(Date dataDeInicio) {
		this.dataDeInicio = dataDeInicio;
	}

	@NotNull(message = "Defina uma data de término para o evento")
	@Temporal(TemporalType.DATE)
	@Column(name = "data_termino")
	public Date getDataDeTermino() {
		return dataDeTermino;
	}

	public void setDataDeTermino(Date dataDeTermino) {
		this.dataDeTermino = dataDeTermino;
	}

	@OneToMany(mappedBy = "evento")
	public List<Atividade> getAtividades() {
		return atividades;
	}

	public void setAtividades(List<Atividade> atividades) {
		this.atividades = atividades;
	}

	@OneToMany(mappedBy = "id.evento")
	public List<ParticipanteEvento> getParticipanteEventoList() {
		return participanteEventoList;
	}

	public void setParticipanteEventoList(List<ParticipanteEvento> participanteEventoList) {
		this.participanteEventoList = participanteEventoList;
	}

	@OneToMany(mappedBy = "id.atividade")
	public List<ParticipanteAtividade> getParticipanteAtividadeList() {
		return participanteAtividadeList;
	}

	public void setParticipanteAtividadeList(List<ParticipanteAtividade> participanteAtividadeList) {
		this.participanteAtividadeList = participanteAtividadeList;
	}

	@ManyToOne
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
		Evento other = (Evento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
