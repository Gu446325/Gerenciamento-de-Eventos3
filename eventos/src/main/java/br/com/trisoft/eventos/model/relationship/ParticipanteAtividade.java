package br.com.trisoft.eventos.model.relationship;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.com.trisoft.eventos.model.Atividade;
import br.com.trisoft.eventos.model.Participante;
import br.com.trisoft.eventos.model.key.ParticipanteAtividadeId;

@Entity
@Table(name = "participante_atividade")
@NamedQueries({
		@NamedQuery(name = "ParticipanteAtividade.buscarTodos", query = "select p from ParticipanteAtividade p where p.id.atividade = :Atividade order by p.id.participante.nome"),
		@NamedQuery(name = "ParticipanteAtividade.contar", query = "select count(p) from ParticipanteAtividade p where p.id.atividade = :Atividade"),
		@NamedQuery(name = "ParticipanteAtividade.buscarTodosPorParticipante", query = "select p from ParticipanteAtividade p where p.id.participante = :Participante order by p.id.participante.nome"),
		@NamedQuery(name = "ParticipanteAtividade.contarPorParticipante", query = "select count(p) from ParticipanteAtividade p where p.id.participante = :Participante"),
		@NamedQuery(name = "ParticipanteAtividade.buscarParticipanteConfirmado", query = "select p from ParticipanteAtividade p where  p.id.atividade.evento = :Evento and p.presenteNaAtividade = true or p.id.atividade.evento = :Evento and p.atividadePaga = true order by p.id.participante.nome"),
		@NamedQuery(name = "ParticipanteAtividade.contarParticipanteConfirmado", query = "select count(p) from ParticipanteAtividade p where p.id.atividade.evento = :Evento  and p.presenteNaAtividade = true or p.id.atividade.evento = :Evento and p.atividadePaga = true"),
		@NamedQuery(name = "ParticipanteAtividade.porValidacao", query = "select p from ParticipanteAtividade p where p.codigo = :codigo") })
public class ParticipanteAtividade implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long codigo;
	private ParticipanteAtividadeId id;
	private Date dataInscricao;
	private Boolean atividadePaga = false;
	private Boolean presenteNaAtividade = false;
	private BigDecimal valorInscricao = BigDecimal.ZERO;
	private Long percentual = 100L;

	@EmbeddedId
	public ParticipanteAtividadeId getId() {
		return id;
	}

	public void setId(ParticipanteAtividadeId id) {
		this.id = id;
	}

	@Column(name = "codigo_validacao")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gera_seq")
	@SequenceGenerator(name = "gera_seq", sequenceName = "participante_atividade_seq", allocationSize = 1)
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_inscricao")
	public Date getDataInscricao() {
		return dataInscricao;
	}

	public void setDataInscricao(Date dataInscricao) {
		this.dataInscricao = dataInscricao;
	}

	@Column(name = "pagamento_atividade", nullable = false)
	public Boolean getAtividadePaga() {
		return atividadePaga;
	}

	public void setAtividadePaga(Boolean atividadePaga) {
		this.atividadePaga = atividadePaga;
	}

	@Column(name = "presenca_atividade", nullable = false)
	public Boolean getPresenteNaAtividade() {
		return presenteNaAtividade;
	}

	public void setPresenteNaAtividade(Boolean presenteNaAtividade) {
		this.presenteNaAtividade = presenteNaAtividade;
	}

	@Column(name = "valor_inscricao", nullable = false)
	public BigDecimal getValorInscricao() {
		return valorInscricao;
	}

	public void setValorInscricao(BigDecimal valorInscricao) {
		this.valorInscricao = valorInscricao;
	}

	@Column(name = "percentual", nullable = false)
	public Long getPercentual() {
		return percentual;
	}

	public void setPercentual(Long percentual) {
		this.percentual = percentual;
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
		ParticipanteAtividade other = (ParticipanteAtividade) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public void adicionarParticipante(Participante participante, Atividade atividade) {

		ParticipanteAtividadeId id = new ParticipanteAtividadeId();
		id.setAtividade(atividade);
		id.setParticipante(participante);

		this.setId(id);
	}

	@Transient
	public BigDecimal getTarifa() {

		final BigDecimal VALOR_MINIMO = BigDecimal.valueOf(20);
		final BigDecimal PERCENTUAL_TARIFA = BigDecimal.valueOf(10);

		BigDecimal tarifa = BigDecimal.ZERO;

		if (this.valorInscricao.compareTo(VALOR_MINIMO) == -1) {
			// tarifa = tarifa.add(BigDecimal.valueOf(2));
			tarifa = tarifa.add(new BigDecimal("2.00"));

		} else {
			tarifa = tarifa.add(this.valorInscricao.multiply(PERCENTUAL_TARIFA)).divide(BigDecimal.valueOf(100));
		}

		return tarifa;
	}

}
