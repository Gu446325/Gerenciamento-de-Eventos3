package br.com.trisoft.eventos.lazymodel;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.trisoft.eventos.dao.ParticipanteDAO;
import br.com.trisoft.eventos.model.Participante;

public class ParticipanteLazy extends LazyDataModel<Participante>implements Serializable {

	private static final long serialVersionUID = 1L;

	private ParticipanteDAO dao;

	private Participante participante;

	public ParticipanteLazy(ParticipanteDAO participanteDAO, Participante participante) {

		if (participante.getNome() == null) {
			participante.setNome("");
		}

		if (participante.getEmail() == null) {
			participante.setEmail("");
		}

		this.dao = participanteDAO;
		this.participante = participante;
	}

	public List<Participante> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {

		List<Participante> participantes = this.dao.buscarParticipanteComPaginacao(first, pageSize, participante);

		this.setRowCount(this.dao.encontrarParticipanteQuantidade(participante).intValue());

		return participantes;
	}

}
