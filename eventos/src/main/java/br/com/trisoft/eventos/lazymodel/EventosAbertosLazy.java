package br.com.trisoft.eventos.lazymodel;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.trisoft.eventos.dao.EventoDAO;
import br.com.trisoft.eventos.model.Evento;

public class EventosAbertosLazy extends LazyDataModel<Evento>implements Serializable {

	private static final long serialVersionUID = 1L;

	private EventoDAO eventoDAO;
	private Evento evento;

	public EventosAbertosLazy(Evento evento, EventoDAO eventoDAO) {

		if (evento.getNome() == null) {
			evento.setNome("");
		}

		this.evento = evento;
		this.eventoDAO = eventoDAO;
	}

	@Override
	public List<Evento> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {

		List<Evento> eventos = this.eventoDAO.buscarAbertosComPaginacao(evento, first, pageSize);
		this.setRowCount(this.eventoDAO.encontrarAbertosQuantidade(evento).intValue());

		return eventos;
	}

}
