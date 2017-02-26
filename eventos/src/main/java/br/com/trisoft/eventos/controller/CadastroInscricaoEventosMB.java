package br.com.trisoft.eventos.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.trisoft.eventos.dao.ParticipanteDAO;
import br.com.trisoft.eventos.lazymodel.ParticipanteLazy;
import br.com.trisoft.eventos.model.Evento;
import br.com.trisoft.eventos.model.Participante;
import br.com.trisoft.eventos.model.relationship.ParticipanteEvento;
import br.com.trisoft.eventos.service.NegocioException;
import br.com.trisoft.eventos.service.ParticipanteEventoService;
import br.com.trisoft.eventos.service.validationobject.EventoServiceVO;
import br.com.trisoft.eventos.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroInscricaoEventosMB implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Participante participante;

	@Inject
	private ParticipanteDAO participanteDAO;

	@Inject
	private ParticipanteEventoService participanteEventoService;

	@Inject
	private Evento evento;

	private ParticipanteLazy participanteLazy;

	@Inject
	private EventoServiceVO eventoServiceVO;

	public void iniciar() {

		if (participante == null) {
			limpar();
		}

		Evento evtTemp = eventoServiceVO.getEvento(evento.getId());
		
		if (evtTemp.getId() == evento.getId()) {
			participanteLazy = new ParticipanteLazy(participanteDAO, participante);
		}
	}

	public void adicionar() throws NegocioException {

		try {

			ParticipanteEvento participanteEvento = new ParticipanteEvento();
			participanteEvento.adicionarInscricao(evento, participante);

			participanteEventoService.salvar(participanteEvento);

			FacesUtil.addInfoMessage(participante.getNome() + " participará do evento");

		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}

		limpar();
	}

	private void limpar() {
		participante = new Participante();
	}

	public Participante getParticipante() {
		return participante;
	}

	public void setParticipante(Participante participante) {
		this.participante = participante;
	}

	public ParticipanteLazy getParticipanteLazy() {
		return participanteLazy;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

}
