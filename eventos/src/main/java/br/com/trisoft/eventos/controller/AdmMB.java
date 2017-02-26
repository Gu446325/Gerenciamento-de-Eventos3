package br.com.trisoft.eventos.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.trisoft.eventos.model.Instituicao;
import br.com.trisoft.eventos.service.InstituicaoService;
import br.com.trisoft.eventos.service.NegocioException;
import br.com.trisoft.eventos.util.jsf.FacesUtil;

@Named
@ViewScoped
public class AdmMB implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Instituicao instituicao;

	@Inject
	private InstituicaoService instituicaoService;

	@Inject
	private RelatorioMB relatorio;

	private List<Instituicao> instituicoes;

	public void iniciar() {

		if (instituicao == null) {
			limpar();
		}

		instituicoes = instituicaoService.buscarTodos();
	}

	public void bloqueio() {

		try {

			if (instituicao.getAtivo() == true) {
				instituicao.setAtivo(false);
			} else {
				instituicao.setAtivo(true);
			}

			instituicaoService.salvar(instituicao);

		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public void emitir() {
		relatorio.emitir(new HashMap<>(), "/relatorios/pendentes.jasper", "Pendente.pdf");

	}

	private void limpar() {
		instituicao = new Instituicao();
	}

	public Instituicao getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}

	public List<Instituicao> getInstituicoes() {
		return instituicoes;
	}

}
