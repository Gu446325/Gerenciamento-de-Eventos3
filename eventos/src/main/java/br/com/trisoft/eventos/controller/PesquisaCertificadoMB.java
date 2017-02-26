package br.com.trisoft.eventos.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.trisoft.eventos.model.relationship.ParticipanteAtividade;
import br.com.trisoft.eventos.service.ParticipanteAtividadeService;
import br.com.trisoft.eventos.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaCertificadoMB implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ParticipanteAtividade certificado;

	@Inject
	private ParticipanteAtividadeService service;

	@Inject
	private RelatorioMB relatorio;

	private Long codigo = 0L;

	public void iniciar() {
		limpar();
	}

	private void limpar() {
		certificado = new ParticipanteAtividade();
	}

	public void pesquisar() {

		try {

			certificado = service.buscarPeloCodigoValidacao(certificado.getCodigo());
			FacesUtil.addInfoMessage("Certificado encontrado no sistema");

		} catch (Exception e) {
			FacesUtil.addErrorMessage("Certificado não encontrado");
			limpar();
		}
	}

	public void emitir() {

		Map<String, Object> parametros = new HashMap<>();
		parametros.put("id_atividade", certificado.getId().getAtividade().getId());
		parametros.put("id_participante", certificado.getId().getParticipante().getId());

		relatorio.emitir(parametros, "/relatorios/certificado.jasper", "Certificado.pdf");
	}

	public ParticipanteAtividade getCertificado() {
		return certificado;
	}

	public void setCertificado(ParticipanteAtividade certificado) {
		this.certificado = certificado;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

}
