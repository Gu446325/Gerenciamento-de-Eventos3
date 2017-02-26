package br.com.trisoft.eventos.service.validationobject;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.trisoft.eventos.model.Evento;
import br.com.trisoft.eventos.security.Seguranca;
import br.com.trisoft.eventos.service.EventoService;

public class EventoServiceVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Seguranca seguranca;
	
	@Inject
	private EventoService eventoService;

	/**
	 * Retorna um evento especifico através do id do evento e do usuário logado
	 * 
	 * @author luiz
	 * @param idEvento
	 * @return Evento
	 */
	public Evento getEvento(Long idEvento) {

		Long idUsuario = seguranca.getUsuarioLogado().getUsuario().getId();
		Evento eventoTemp = eventoService.buscarCodigoDoEvento(idEvento, idUsuario);

		return eventoTemp;
	}

}
