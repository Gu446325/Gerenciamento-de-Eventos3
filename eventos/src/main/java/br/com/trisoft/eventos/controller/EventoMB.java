package br.com.trisoft.eventos.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.trisoft.eventos.dao.ParticipanteAtividadeDAO;
import br.com.trisoft.eventos.lazymodel.ParticipanteConfirmadoLazy;
import br.com.trisoft.eventos.model.Evento;
import br.com.trisoft.eventos.model.relationship.ParticipanteAtividade;
import br.com.trisoft.eventos.service.ParticipanteAtividadeService;
import br.com.trisoft.eventos.service.validationobject.EventoServiceVO;

@Named
@ViewScoped
public class EventoMB implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private RelatorioMB relatorio;

	@Inject
	private Evento evento;

	private ParticipanteConfirmadoLazy participanteConfirmadoLazy;

	@Inject
	private ParticipanteAtividadeDAO participanteAtividadeDAO;

	@Inject
	private ParticipanteAtividadeService participanteAtividadeService;

	private List<ParticipanteAtividade> listaParticipantes;

	@Inject
	private EventoServiceVO eventoServiceVO;

	public void iniciar() {

		Evento evtTemp = eventoServiceVO.getEvento(evento.getId());

		if (evtTemp.getId() == evento.getId()) {
			listaParticipantes = participanteAtividadeService.buscarParticipanteConfirmado(evento);
			participanteConfirmadoLazy = new ParticipanteConfirmadoLazy(participanteAtividadeDAO, evento);

		}
	}

	public void custos() {
	}

	public BigDecimal getTotalAtividades() {

		BigDecimal total = BigDecimal.ZERO;

		for (ParticipanteAtividade p : listaParticipantes) {
			total = total.add(p.getValorInscricao());
		}

		return total;
	}

	public BigDecimal getTotalTarifa() {

		BigDecimal tarifa = BigDecimal.ZERO;

		for (ParticipanteAtividade p : listaParticipantes) {
			tarifa = tarifa.add(p.getTarifa());
		}

		return tarifa;
	}

	public void emitir() {

		Map<String, Object> parametros = new HashMap<>();
		parametros.put("id_evento", evento.getId());

		relatorio.emitir(parametros, "/relatorios/publicidade.jasper", "Banner do evento.pdf");

	}

	public ParticipanteConfirmadoLazy getParticipanteConfirmadoLazy() {
		return participanteConfirmadoLazy;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

}
