package br.com.trisoft.eventos.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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

import br.com.trisoft.eventos.model.relationship.ParticipanteAtividade;

@Entity
@Table(name = "atividade")
@NamedQueries({
		@NamedQuery(name = "Atividade.buscarTodosPorEvento", query = "select a from Atividade a join fetch a.evento where a.evento  = :Evento order by a.nome"),
		@NamedQuery(name = "Atividade.contarPorEvento", query = "select count(a) from Atividade a join a.evento where a.evento  = :Evento")})
public class Atividade implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private TipoAtividade tipoAtividade;
	private String localDeRealizacao;
	private Long cargaHoraria = new Long(0);
	private Date dataDeInicio;
	private Date dataDeTermino;
	private BigDecimal valorDaAtividade = BigDecimal.ZERO;
	private Long numeroDeVagas = new Long(1);
	private String descricao;
	private Evento evento;

	private List<ParticipanteAtividade> participanteAtividadeList;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gera_seq")
	@SequenceGenerator(name = "gera_seq", sequenceName = "atividade_seq", allocationSize = 1)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "nome", nullable = false, length = 80)
	@Size(max = 80, message = "O nome não pode conter mais que 80 caracteres")
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@NotNull
	@Column(name = "tipo_atividade")
	@Enumerated(EnumType.STRING)
	public TipoAtividade getTipoAtividade() {
		return tipoAtividade;
	}

	public void setTipoAtividade(TipoAtividade tipoAtividade) {
		this.tipoAtividade = tipoAtividade;
	}

	@NotNull
	@Column(name = "local_realizacao")
	@Size(max = 50, message = "O local de realização não pode ter mais que 50 caracteres")
	public String getLocalDeRealizacao() {
		return localDeRealizacao;
	}

	public void setLocalDeRealizacao(String localDeRealizacao) {
		this.localDeRealizacao = localDeRealizacao;
	}

	@Column(name = "carga_horaria")
	@DecimalMin(value = "0", message = "O número mínimo de horas é 0")
	@NotNull(message = "O campo de carga horária deve ser preenchido")
	public Long getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(Long cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_inicio")
	public Date getDataDeInicio() {
		return dataDeInicio;
	}

	public void setDataDeInicio(Date dataDeInicio) {
		this.dataDeInicio = dataDeInicio;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_termino")
	public Date getDataDeTermino() {
		return dataDeTermino;
	}

	public void setDataDeTermino(Date dataDeTermino) {
		this.dataDeTermino = dataDeTermino;
	}

	@NotNull
	@Column(name = "valor_atividade", nullable = false)
	@DecimalMin(value = "0.00", message = "O valor mínimo da atividade é R$ 0,00")
	public BigDecimal getValorDaAtividade() {
		return valorDaAtividade;
	}

	public void setValorDaAtividade(BigDecimal valorDaAtividade) {
		this.valorDaAtividade = valorDaAtividade;
	}

	@NotNull
	@Column(name = "numero_vagas", nullable = false)
	@DecimalMin(value = "1", message = "O número mínimo de vagas é 1")
	public Long getNumeroDeVagas() {
		return numeroDeVagas;
	}

	public void setNumeroDeVagas(Long numeroDeVagas) {
		this.numeroDeVagas = numeroDeVagas;
	}

	@Column(name = "descricao")
	@Size(max = 250, message = "A descrição não pode ter mais que 250 caracteres")
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_evento")
	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	@OneToMany(mappedBy = "id.participante")
	public List<ParticipanteAtividade> getParticipanteAtividadeList() {
		return participanteAtividadeList;
	}

	public void setParticipanteAtividadeList(List<ParticipanteAtividade> participanteAtividadeList) {
		this.participanteAtividadeList = participanteAtividadeList;
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
		Atividade other = (Atividade) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
