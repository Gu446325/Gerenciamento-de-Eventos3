package br.com.trisoft.eventos.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.trisoft.eventos.dao.ParticipanteAtividadeDAO;
import br.com.trisoft.eventos.lazymodel.ParticipanteAtividadeLazy;
import br.com.trisoft.eventos.model.Atividade;
import br.com.trisoft.eventos.model.Evento;
import br.com.trisoft.eventos.model.relationship.ParticipanteAtividade;
import br.com.trisoft.eventos.service.NegocioException;
import br.com.trisoft.eventos.service.ParticipanteAtividadeService;
import br.com.trisoft.eventos.service.validationobject.EventoServiceVO;
import br.com.trisoft.eventos.util.jsf.FacesUtil;
import br.com.trisoft.eventos.util.report.XLS;

/**
 * 
 * @author luiz
 *
 *         Pesquisa os inscritos em uma atividade específica e também atua como
 *         credenciamento da atividade com presença e pagamento
 */

@Named
@ViewScoped
public class PesquisaInscricaoAtividadeMB implements Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	private RelatorioMB relatorioMB;

	@Inject
	private Atividade atividade;

	@Inject
	private ParticipanteAtividade participanteAtividade;

	@Inject
	private ParticipanteAtividadeDAO participanteAtividadeDAO;

	@Inject
	private ParticipanteAtividadeService participanteAtividadeService;

	private ParticipanteAtividadeLazy participanteLazy;

	@Inject
	private XLS xls;
	
	@Inject
	private EventoServiceVO eventoServiceVO;

	public void iniciar() {
		Evento evtTemp = eventoServiceVO.getEvento(atividade.getEvento().getId());

		if (evtTemp.getId() == atividade.getEvento().getId()) {
			participanteLazy = new ParticipanteAtividadeLazy(participanteAtividadeDAO, atividade);
		}
	}

	public void modelarPlanilha(Object documento) {
		xls.modelarPlanilha(documento);
	}

	public void modificarPresenca() {

		try {

			buscarParticipante();
			participanteAtividadeService.modificarPresenca(participanteAtividade);
			FacesUtil.addInfoMessage("Presença confirmada");

		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public void modificarPagamento() {

		try {

			buscarParticipante();
			participanteAtividadeService.modificarPagamento(participanteAtividade);
			FacesUtil.addInfoMessage("Pagamento efetuado");

		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public void emitir() {
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("id_atividade", atividade.getId());

		relatorioMB.emitir(parametros, "/relatorios/presenca.jasper", "Lista de presença.pdf");
	}

	public void emitirCredencial() {

		buscarParticipante();

		Map<String, Object> parametros = new HashMap<>();
		parametros.put("id_atividade", atividade.getId());
		parametros.put("id_participante", participanteAtividade.getId().getParticipante().getId());

		relatorioMB.emitir(parametros, "/relatorios/credencial.jasper", "Credencial.pdf");

	}

	private void buscarParticipante() {
		participanteAtividade = participanteAtividadeService.buscarInscricao(participanteAtividade.getId());
	}

	public void removerParticipante() {

		try {

			participanteAtividadeService.remover(participanteAtividade);
			FacesUtil.addInfoMessage("Participante removido");

		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public Long getNumeroInscritos() {
		return participanteAtividadeService.buscarQuantidade(atividade);
	}

	public Atividade getAtividade() {
		return atividade;
	}

	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}

	public ParticipanteAtividadeLazy getParticipanteLazy() {
		return participanteLazy;
	}

	public void setParticipanteLazy(ParticipanteAtividadeLazy participanteLazy) {
		this.participanteLazy = participanteLazy;
	}

	public ParticipanteAtividade getParticipanteAtividade() {
		return participanteAtividade;
	}

	public void setParticipanteAtividade(ParticipanteAtividade participanteAtividade) {
		this.participanteAtividade = participanteAtividade;
	}

}
