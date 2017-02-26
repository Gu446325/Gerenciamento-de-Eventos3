package br.com.trisoft.eventos.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.trisoft.eventos.dao.ParticipanteDAO;
import br.com.trisoft.eventos.lazymodel.ParticipanteLazy;
import br.com.trisoft.eventos.model.Participante;
import br.com.trisoft.eventos.service.NegocioException;
import br.com.trisoft.eventos.service.ParticipanteService;
import br.com.trisoft.eventos.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaParticipanteMB implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Participante participante;

	@Inject
	private Participante participanteEdicao;

	// private List<Participante> participantes;

	// @Inject
	// private ParticipanteService participanteService;

	@Inject
	private ParticipanteDAO participanteDAO;
	private ParticipanteLazy participanteLazy;

	@PostConstruct
	public void iniciar() {

		if (participante == null) {
			limpar();
		}

		participanteLazy = new ParticipanteLazy(participanteDAO, participante);

	}

	@Inject
	private ParticipanteService participanteService;

	public void alterarNome() throws NegocioException {

		FacesUtil.addInfoMessage(participante.getNome() + " para " + participanteEdicao.getNome());

		try {
			// TODO service em teste
			if (participanteEdicao.getNome().length() >= 3) {

				participante = participanteService.buscarPeloCodigo(participante.getId());
				participante.setNome(participanteEdicao.getNome());

				participanteService.salvar(participante);
				FacesUtil.addInfoMessage("O nome do usuário foi alterado");

				limpar();

			} else {
				FacesUtil.addWarnMessage("O nome deve possuir pelo menos 3 caracteres");
			}

		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}

	}

	private void limpar() {
		participante = new Participante();
		participanteEdicao = new Participante();
	}

	public Participante getParticipante() {
		return participante;
	}

	public void setParticipante(Participante participante) {
		this.participante = participante;
	}

	// TODO em testes
	public Participante getParticipanteEdicao() {
		return participanteEdicao;
	}

	public void setParticipanteEdicao(Participante participanteEdicao) {
		this.participanteEdicao = participanteEdicao;
	}

	public ParticipanteLazy getParticipanteLazy() {
		return participanteLazy;
	}

}
