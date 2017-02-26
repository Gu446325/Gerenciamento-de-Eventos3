package br.com.trisoft.eventos.model.relationship;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.trisoft.eventos.model.Evento;
import br.com.trisoft.eventos.model.Participante;
import br.com.trisoft.eventos.model.key.ParticipanteEventoId;

@Entity
@Table(name = "participante_evento")
@NamedQueries({
		@NamedQuery(name = "ParticipanteEvento.buscarTodos", query = "select p from ParticipanteEvento p where p.id.evento = :Evento order by p.id.participante.nome"),
		@NamedQuery(name = "ParticipanteEvento.contar", query = "select count(p) from ParticipanteEvento p where p.id.evento = :Evento") })
public class ParticipanteEvento implements Serializable {

	private static final long serialVersionUID = 1L;

	private ParticipanteEventoId id;
	private Date dataDeInscricao;

	@EmbeddedId
	public ParticipanteEventoId getId() {
		return id;
	}

	public void setId(ParticipanteEventoId id) {
		this.id = id;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_inscricao")
	public Date getDataDeInscricao() {
		return dataDeInscricao;
	}

	public void setDataDeInscricao(Date dataDeInscricao) {
		this.dataDeInscricao = dataDeInscricao;
	}

	public void adicionarInscricao(Evento evento, Participante participante) {

		ParticipanteEventoId id = new ParticipanteEventoId();
		id.setEvento(evento);
		id.setParticipante(participante);

		this.setId(id);

	}

	// TODO dia 30 de novembro foi adicionado(observar comportamento)
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
		ParticipanteEvento other = (ParticipanteEvento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
