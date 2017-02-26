package br.com.trisoft.eventos.lazymodel;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.trisoft.eventos.dao.EventoDAO;
import br.com.trisoft.eventos.model.Evento;

public class EventoLazy extends LazyDataModel<Evento>implements Serializable {

	private static final long serialVersionUID = 1L;

	private Evento evento;
	private EventoDAO eventoDAO;

	public EventoLazy(Evento evento, EventoDAO eventoDAO) {

		if (evento.getNome() == null) {
			evento.setNome("");
		}

		this.evento = evento;
		this.eventoDAO = eventoDAO;
	}

	@Override
	public List<Evento> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {

		List<Evento> eventos = this.eventoDAO.buscarComPaginacao(evento, first, pageSize);
		this.setRowCount(this.eventoDAO.encontrarQuantidade(evento).intValue());

		return eventos;
	}

}
