package br.com.trisoft.eventos.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.trisoft.eventos.model.Atividade;
import br.com.trisoft.eventos.model.Evento;
import br.com.trisoft.eventos.model.TipoAtividade;
import br.com.trisoft.eventos.service.AtividadeService;
import br.com.trisoft.eventos.service.EventoService;
import br.com.trisoft.eventos.service.NegocioException;
import br.com.trisoft.eventos.service.validationobject.EventoServiceVO;
import br.com.trisoft.eventos.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroAtividadesMB implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Atividade atividade;

	@Inject
	private Evento evento;

	@Inject
	private AtividadeService atividadeService;

	@Inject
	private EventoService eventoService;

	@Inject
	private EventoServiceVO eventoServiceVO;

	public void iniciar() {


		if (evento == null && atividade.getId() != null) {

			Long eventoId = atividade.getEvento().getId();
			evento = eventoService.buscarPeloCodigo(eventoId);

			Evento evtTemp = eventoServiceVO.getEvento(evento.getId());
			
			if (evtTemp.getId() == evento.getId()) {
				atividade.setEvento(evento);
			}
		}

		if (atividade == null) {
			limpar();
		}
	}

	public void adicionarAtividade() {
		try {

			atividade.setEvento(evento);
			atividadeService.salvar(atividade);

			FacesUtil.addInfoMessage("Sucesso");

			limpar();

		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}

	}

	public void excluirAtividade(Long id) {
		try {

			atividade = atividadeService.buscarPeloCodigo(id);
			atividadeService.remover(atividade.getId());

			// evento.getAtividades().remove(atividade);

			FacesUtil.addInfoMessage("Atividade removida com sucesso");

			limpar();

		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	private void limpar() {
		atividade = new Atividade();
	}

	public TipoAtividade[] getAtividades() {
		return TipoAtividade.values();
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public Atividade getAtividade() {
		return atividade;
	}

	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}

}
