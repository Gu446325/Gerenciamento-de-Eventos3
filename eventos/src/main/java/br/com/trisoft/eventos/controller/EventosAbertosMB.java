package br.com.trisoft.eventos.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.trisoft.eventos.dao.EventoDAO;
import br.com.trisoft.eventos.lazymodel.EventosAbertosLazy;
import br.com.trisoft.eventos.model.Evento;

@Named
@ViewScoped
public class EventosAbertosMB implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Evento evento;

	@Inject
	private EventoDAO eventoDAO;

	private EventosAbertosLazy eventosAbertosLazy;

	public void iniciar() {

		if (evento == null) {
			limpar();
		}

		eventosAbertosLazy = new EventosAbertosLazy(evento, eventoDAO);

	}

	private void limpar() {
		evento = new Evento();
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public EventosAbertosLazy getEventosAbertosLazy() {
		return eventosAbertosLazy;
	}

}
