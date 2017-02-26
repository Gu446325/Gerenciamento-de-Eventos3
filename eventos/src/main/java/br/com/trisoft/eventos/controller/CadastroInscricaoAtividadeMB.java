package br.com.trisoft.eventos.controller;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.trisoft.eventos.dao.ParticipanteEventoDAO;
import br.com.trisoft.eventos.lazymodel.ParticipanteEventoLazy;
import br.com.trisoft.eventos.model.Atividade;
import br.com.trisoft.eventos.model.Evento;
import br.com.trisoft.eventos.model.Participante;
import br.com.trisoft.eventos.model.relationship.ParticipanteAtividade;
import br.com.trisoft.eventos.service.NegocioException;
import br.com.trisoft.eventos.service.ParticipanteAtividadeService;
import br.com.trisoft.eventos.service.ParticipanteService;
import br.com.trisoft.eventos.service.validationobject.EventoServiceVO;
import br.com.trisoft.eventos.util.jsf.FacesUtil;

/**
 * 
 * @author luiz
 *
 *         Inscreve os participantes na atividade
 */
@Named
@ViewScoped
public class CadastroInscricaoAtividadeMB implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ParticipanteEventoDAO participanteEventoDAO;

	@Inject
	private Atividade atividade;

	@Inject
	private ParticipanteAtividade participanteAtividade;

	@Inject
	private ParticipanteService participanteService;

	@Inject
	private ParticipanteAtividadeService participanteAtividadeService;

	private ParticipanteEventoLazy participanteLazy;

	private Long percentual;

	@Inject
	private EventoServiceVO eventoServiceVO;

	public void iniciar() {

		percentual = 100L;
		Evento evtTemp = eventoServiceVO.getEvento(atividade.getEvento().getId());

		if (evtTemp.getId() == atividade.getEvento().getId()) {
			participanteLazy = new ParticipanteEventoLazy(participanteEventoDAO, atividade.getEvento());
		}
	}

	public void adicionar(Long idParticipante) {

		try {

			if (getNumeroVagas() > 0) {
				Participante participante = participanteService.buscarPeloCodigo(idParticipante);

				participanteAtividade = new ParticipanteAtividade();

				participanteAtividade.setValorInscricao(calculaValorInscricao());
				participanteAtividade.adicionarParticipante(participante, atividade);

				Long codigoValidacao = geraCodigoValidacao(participante.getId());
				participanteAtividade.setCodigo(codigoValidacao);

				participanteAtividadeService.salvar(participanteAtividade);

				FacesUtil.addInfoMessage(participante.getNome() + " inscrito(a) com sucesso na atividade");
				FacesUtil.addWarnMessage(
						"Inscrição de " + percentual + "%.\n R$ " + participanteAtividade.getValorInscricao());

			} else {
				FacesUtil.addErrorMessage("Não há mais vagas!");
			}

		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}

	}

	private Long geraCodigoValidacao(Long valor) {
		final int prime = 31;
		Long result = atividade.getId();
		result = prime * result + valor;// ((valor == null) ? 0 :
										// valor.hashCode());
		return result;
	}

	private BigDecimal calculaValorInscricao() {

		participanteAtividade.setPercentual(getPercentual());
		Long valorPercentual = participanteAtividade.getPercentual();
		BigDecimal valorAtividade = atividade.getValorDaAtividade();

		BigDecimal valorInscricao = (valorAtividade.multiply(BigDecimal.valueOf(valorPercentual)))
				.divide(BigDecimal.valueOf(100));

		return valorInscricao;
	}

	public Long getNumeroVagas() {
		return participanteAtividadeService.getNumeroVagas(atividade);
	}

	@SuppressWarnings("unused")
	private void limpar() {
		// TODO Auto-generated method stub
	}

	public Atividade getAtividade() {
		return atividade;
	}

	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}

	public ParticipanteEventoLazy getParticipanteLazy() {
		return participanteLazy;
	}

	public void setParticipanteLazy(ParticipanteEventoLazy participanteLazy) {
		this.participanteLazy = participanteLazy;
	}

	public ParticipanteAtividade getParticipanteAtividade() {
		return participanteAtividade;
	}

	public void setParticipanteAtividade(ParticipanteAtividade participanteAtividade) {
		this.participanteAtividade = participanteAtividade;
	}

	public Long getPercentual() {
		return percentual;
	}

	public void setPercentual(Long percentual) {
		this.percentual = percentual;
	}

}