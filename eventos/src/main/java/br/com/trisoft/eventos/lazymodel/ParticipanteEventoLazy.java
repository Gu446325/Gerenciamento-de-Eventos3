package br.com.trisoft.eventos.lazymodel;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.trisoft.eventos.dao.ParticipanteEventoDAO;
import br.com.trisoft.eventos.model.Evento;
import br.com.trisoft.eventos.model.relationship.ParticipanteEvento;

public class ParticipanteEventoLazy extends LazyDataModel<ParticipanteEvento>implements Serializable {

	private static final long serialVersionUID = 1L;

	private ParticipanteEventoDAO dao;
	private Evento evento;

	public ParticipanteEventoLazy(ParticipanteEventoDAO dao, Evento evento) {

		this.dao = dao;
		this.evento = evento;
	}

	@Override
	public List<ParticipanteEvento> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {

		List<ParticipanteEvento> participantes = this.dao.buscarInscricaoComPaginacao(first, pageSize, evento);

		this.setRowCount(this.dao.encontrarInscricaoQuantidade(evento).intValue());

		return participantes;
	}

}
