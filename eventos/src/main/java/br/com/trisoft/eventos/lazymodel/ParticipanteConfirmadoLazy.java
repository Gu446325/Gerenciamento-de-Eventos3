package br.com.trisoft.eventos.lazymodel;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.trisoft.eventos.dao.ParticipanteAtividadeDAO;
import br.com.trisoft.eventos.model.Evento;
import br.com.trisoft.eventos.model.relationship.ParticipanteAtividade;

/**
 * 
 * @author luiz
 *
 *         Esta classe busca participantes que pagaram ou que estão presentes em
 *         uma atividade do evento informado
 */
public class ParticipanteConfirmadoLazy extends LazyDataModel<ParticipanteAtividade>implements Serializable {

	private static final long serialVersionUID = 1L;

	private ParticipanteAtividadeDAO dao;
	private Evento evento;

	public ParticipanteConfirmadoLazy(ParticipanteAtividadeDAO dao, Evento evento) {
		this.dao = dao;
		this.evento = evento;
	}

	@Override
	public List<ParticipanteAtividade> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {

		List<ParticipanteAtividade> participantes = this.dao.buscarParticipanteConfirmado(first, pageSize, evento);

		this.setRowCount(this.dao.contarParticipanteConfirmado(evento).intValue());

		return participantes;
	}

}
