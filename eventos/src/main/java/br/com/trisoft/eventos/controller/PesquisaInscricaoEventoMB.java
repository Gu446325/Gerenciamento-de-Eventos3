package br.com.trisoft.eventos.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.trisoft.eventos.dao.ParticipanteEventoDAO;
import br.com.trisoft.eventos.lazymodel.InscricaoEventoLazy;
import br.com.trisoft.eventos.model.Evento;
import br.com.trisoft.eventos.service.validationobject.EventoServiceVO;

@Named
@ViewScoped
public class PesquisaInscricaoEventoMB implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Evento evento;

	@Inject
	private ParticipanteEventoDAO participanteDAO;

	private InscricaoEventoLazy inscricaoLazy;

	@Inject
	private EventoServiceVO eventoServiceVO;

	public void iniciar() {

		Evento evtTemp = eventoServiceVO.getEvento(evento.getId());

		if (evtTemp.getId() == evento.getId()) {
			inscricaoLazy = new InscricaoEventoLazy(participanteDAO, evento);
		}
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public Evento getEvento() {
		return evento;
	}

	public InscricaoEventoLazy getInscricaoLazy() {
		return inscricaoLazy;
	}

}
