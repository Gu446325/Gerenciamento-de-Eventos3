package br.com.trisoft.eventos.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.trisoft.eventos.dao.AtividadeDAO;
import br.com.trisoft.eventos.lazymodel.AtividadeLazy;
import br.com.trisoft.eventos.model.Atividade;
import br.com.trisoft.eventos.model.Evento;
import br.com.trisoft.eventos.model.Participante;
import br.com.trisoft.eventos.model.relationship.ParticipanteAtividade;
import br.com.trisoft.eventos.model.relationship.ParticipanteEvento;
import br.com.trisoft.eventos.security.Seguranca;
import br.com.trisoft.eventos.service.AtividadeService;
import br.com.trisoft.eventos.service.NegocioException;
import br.com.trisoft.eventos.service.ParticipanteAtividadeService;
import br.com.trisoft.eventos.service.ParticipanteEventoService;
import br.com.trisoft.eventos.service.ParticipanteService;
import br.com.trisoft.eventos.util.jsf.FacesUtil;

@Named
@ViewScoped
public class AtividadesAbertasMB implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Evento evento;

	@Inject
	private ParticipanteEventoService participanteEventoService;

	@Inject
	private AtividadeDAO atividadeDAO;

	@Inject
	private Participante participante;

	@Inject
	private ParticipanteService participanteService;

	@Inject
	private Seguranca seguranca;

	@Inject
	private Atividade atividade;

	@Inject
	private AtividadeService atividadeService;

	@Inject
	private ParticipanteAtividadeService participanteAtividadeService;

	@Inject
	private ParticipanteAtividade participanteAtividade;

	private AtividadeLazy atividadeLazy;

	@PostConstruct
	public void iniciar() {
		atividadeLazy = new AtividadeLazy(evento, atividadeDAO);
	}

	public void inscricao(Long codigoAtividade) throws NegocioException {

		String email = seguranca.getEmailUsuario();
		participante = participanteService.porEmail(email);

		adicionarNoEvento();
		adicionarNaAtividade(codigoAtividade);

	}

	private void adicionarNoEvento() throws NegocioException {
		try {

			ParticipanteEvento participanteEvento = new ParticipanteEvento();
			participanteEvento.adicionarInscricao(evento, participante);

			if (participanteEventoService.buscarInscricao(participanteEvento.getId()) == null) {

				participanteEventoService.salvar(participanteEvento);
				FacesUtil.addInfoMessage(participante.getNome() + " participará do evento");
			}

		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	private void adicionarNaAtividade(Long codigoAtividade) throws NegocioException {

		try {
			atividade = atividadeService.buscarPeloCodigo(codigoAtividade);

			if (getNumeroVagas() > 0) {

				participanteAtividade = new ParticipanteAtividade();
				
				participanteAtividade.setValorInscricao(atividade.getValorDaAtividade());
				participanteAtividade.adicionarParticipante(participante, atividade);

				participanteAtividadeService.salvar(participanteAtividade);

				FacesUtil.addInfoMessage(participante.getNome() + " inscrito(a) com sucesso na atividade");

			} else {
				FacesUtil.addWarnMessage("Não há mais vagas para esta atividade");
			}

		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public Long getNumeroVagas() {
		return participanteAtividadeService.getNumeroVagas(atividade);
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
