package br.com.trisoft.eventos.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.trisoft.eventos.dao.EventoDAO;
import br.com.trisoft.eventos.model.Evento;
import br.com.trisoft.eventos.model.Instituicao;
import br.com.trisoft.eventos.model.Status;
import br.com.trisoft.eventos.service.EventoService;
import br.com.trisoft.eventos.util.jsf.FacesUtil;

@Named
@ViewScoped
public class StatusEventoMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Evento> eventos;

	@Inject
	private Evento eventoSelecionado;

	@Inject
	private Instituicao instituicao;

	@Inject
	private EventoDAO eventoDAO;

	public void iniciar() {
		eventos = eventoDAO.buscarEventosPorInstituicao(instituicao.getId());
	}

	@Inject
	private EventoService eventoService;

	public void alterarStatus() {
		try {

			if (eventoSelecionado.getStatus() == Status.PENDENTE) {
				eventoSelecionado.setStatus(Status.RECEBIDO);

			} else {
				eventoSelecionado.setStatus(Status.PENDENTE);
			}

			eventoService.salvar(eventoSelecionado);
			FacesUtil.addInfoMessage("Evento alterado para " + eventoSelecionado.getStatus().getDescricao());

		} catch (Exception e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}

	}

	public List<Evento> getEventos() {
		return eventos;
	}

	public Instituicao getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}

	public Evento getEventoSelecionado() {
		return eventoSelecionado;
	}

	public void setEventoSelecionado(Evento eventoSelecionado) {
		this.eventoSelecionado = eventoSelecionado;
	}

}
