package br.com.trisoft.eventos.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.trisoft.eventos.dao.ParticipanteAtividadeDAO;
import br.com.trisoft.eventos.lazymodel.MinhasAtividadesLazy;
import br.com.trisoft.eventos.model.Participante;
import br.com.trisoft.eventos.model.relationship.ParticipanteAtividade;
import br.com.trisoft.eventos.security.Seguranca;
import br.com.trisoft.eventos.service.ParticipanteService;

@Named
@ViewScoped
public class MinhasAtividadesMB implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private RelatorioMB relatorio;

	@Inject
	private ParticipanteAtividade participanteAtividade;

	@Inject
	private ParticipanteAtividadeDAO participanteAtividadeDAO;

	@Inject
	private Participante participante;

	@Inject
	private Seguranca seguranca;

	@Inject
	private ParticipanteService participanteService;

	private MinhasAtividadesLazy atividadesLazy;

	public void iniciar() {

		String email = seguranca.getEmailUsuario();
		participante = participanteService.porEmail(email);

		atividadesLazy = new MinhasAtividadesLazy(participante, participanteAtividadeDAO);

	}

	public void emitir(Long idAtividade) {

		Map<String, Object> parametros = new HashMap<>();
		parametros.put("id_atividade", idAtividade);
		parametros.put("id_participante", participante.getId());

		relatorio.emitir(parametros, "/relatorios/certificado.jasper", "Certificado.pdf");

	}

	public MinhasAtividadesLazy getAtividadesLazy() {
		return atividadesLazy;
	}

	public ParticipanteAtividade getParticipanteAtividade() {
		return participanteAtividade;
	}

	public void setParticipanteAtividade(ParticipanteAtividade participanteAtividade) {
		this.participanteAtividade = participanteAtividade;
	}

}
