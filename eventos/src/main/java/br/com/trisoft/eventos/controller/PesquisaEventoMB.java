package br.com.trisoft.eventos.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.trisoft.eventos.dao.EventoDAO;
import br.com.trisoft.eventos.lazymodel.EventoLazy;
import br.com.trisoft.eventos.model.Evento;
import br.com.trisoft.eventos.model.TipoAtividade;
import br.com.trisoft.eventos.service.validationobject.InstituicaoServiceVO;

@Named
@ViewScoped
public class PesquisaEventoMB implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Evento evento;

	@Inject
	private EventoDAO eventoDAO;
	private EventoLazy eventoLazy;

	@Inject
	private InstituicaoServiceVO ServiceVO;

	public void iniciar() {

		if (evento == null) {
			limpar();
		}

		evento.setInstituicao(ServiceVO.getInstituicao());
		eventoLazy = new EventoLazy(evento, eventoDAO);
	}

	private void limpar() {
		evento = new Evento();
	}

	public TipoAtividade[] getListaAtividades() {
		return TipoAtividade.values();
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public EventoLazy getEventoLazy() {
		return eventoLazy;
	}

}
