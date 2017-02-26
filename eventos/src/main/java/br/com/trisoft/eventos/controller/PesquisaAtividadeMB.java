package br.com.trisoft.eventos.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.trisoft.eventos.dao.AtividadeDAO;
import br.com.trisoft.eventos.lazymodel.AtividadeLazy;
import br.com.trisoft.eventos.model.Evento;
import br.com.trisoft.eventos.service.validationobject.EventoServiceVO;

@Named
@ViewScoped
public class PesquisaAtividadeMB implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Evento evento;

	@Inject
	private AtividadeDAO atividadeDAO;

	private AtividadeLazy atividadeLazy;

	@Inject
	private EventoServiceVO eventoServiceVO;

	public void iniciar() {

		Evento evtTemp = eventoServiceVO.getEvento(evento.getId());

		if (evtTemp.getId() == evento.getId()) {
			atividadeLazy = new AtividadeLazy(evento, atividadeDAO);
		}
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public AtividadeLazy getAtividadeLazy() {
		return atividadeLazy;
	}

	public void setAtividadeLazy(AtividadeLazy atividadeLazy) {
		this.atividadeLazy = atividadeLazy;
	}

}
