package br.com.trisoft.eventos.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.trisoft.eventos.model.Estado;
import br.com.trisoft.eventos.model.Evento;
import br.com.trisoft.eventos.model.Instituicao;
import br.com.trisoft.eventos.service.EventoService;
import br.com.trisoft.eventos.service.NegocioException;
import br.com.trisoft.eventos.service.validationobject.EventoServiceVO;
import br.com.trisoft.eventos.service.validationobject.InstituicaoServiceVO;
import br.com.trisoft.eventos.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroEventoMB implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Evento evento;

	@Inject
	private EventoService service;

	@Inject
	private InstituicaoServiceVO serviceVO;

	@Inject
	private EventoServiceVO eventoServiceVO;

	public void iniciar() {

		if (evento != null) {

			Evento evtTemp = eventoServiceVO.getEvento(evento.getId());

			if (evtTemp.getId() != evento.getId()) {
				limpar();
			}

		} else {
			limpar();
		}

	}

	public Estado[] getEstados() {
		return Estado.values();
	}

	public void salvar() {

		Instituicao instituicao = serviceVO.getInstituicao();

		try {

			if (instituicao.getAtivo() == true) {

				evento.setInstituicao(serviceVO.getInstituicao());
				evento = service.salvarComRetorno(evento);

				FacesUtil.addInfoMessage("Sucesso");

				ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
				String url = "/pages/empresa/Evento.xhtml?faces-redirect=true&evento=" + evento.getId();
				context.redirect(context.getRequestContextPath() + url);

			} else {
				FacesUtil.addWarnMessage(instituicao.getNome() + " está bloqueada para criar novos eventos");
			}

		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());

		} catch (IOException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public void excluir() {

		try {

			service.remover(evento.getId());
			FacesUtil.addInfoMessage("Evento removido do sistema");

			limpar();

		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	private void limpar() {
		evento = new Evento();
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

}
