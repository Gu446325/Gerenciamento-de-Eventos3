package br.com.trisoft.eventos.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.trisoft.eventos.model.Participante;
import br.com.trisoft.eventos.service.NegocioException;
import br.com.trisoft.eventos.service.ParticipanteService;
import br.com.trisoft.eventos.util.jsf.FacesUtil;

@Named
@ViewScoped
public class EdicaoParticipanteMB implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Participante participante;

	@Inject
	private ParticipanteService participanteService;

	public void iniciar() {

		if (participante == null) {

			try {

				ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
				String url = "/pages/empresa/PesquisaParticipante.xhtml?faces-redirect=true";
				context.redirect(context.getRequestContextPath() + url);

			} catch (IOException e) {
			}
		}

	}

	public void salvar() {
		try {
			if (participante.getNome().length() >= 3) {
				participanteService.salvar(participante);

				FacesUtil.addInfoMessage("Participante alterado com sucesso");
			} else {
				FacesUtil.addWarnMessage("O nome deve possuir 3 ou mais caracteres");
			}

		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public Participante getParticipante() {
		return participante;
	}

	public void setParticipante(Participante participante) {
		this.participante = participante;
	}

}
