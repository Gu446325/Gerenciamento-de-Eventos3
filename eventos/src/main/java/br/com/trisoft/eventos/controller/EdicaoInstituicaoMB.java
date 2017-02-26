package br.com.trisoft.eventos.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;

import br.com.trisoft.eventos.model.Instituicao;
import br.com.trisoft.eventos.service.InstituicaoService;
import br.com.trisoft.eventos.service.NegocioException;
import br.com.trisoft.eventos.service.validationobject.InstituicaoServiceVO;
import br.com.trisoft.eventos.util.jsf.FacesUtil;

@Named
@ViewScoped
public class EdicaoInstituicaoMB implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Instituicao instituicao;

	@Inject
	private InstituicaoService instituicaoService;

	@Inject
	private InstituicaoServiceVO serviceVO;

	public void iniciar() {

		instituicao = serviceVO.getInstituicao();

	}

	public void logotipo(FileUploadEvent event) {
		try {

			instituicao.setLogotipo(event.getFile().getContents());
			instituicaoService.salvar(instituicao);

			FacesUtil.addInfoMessage("Logo da empresa foi alterada com sucesso");

		} catch (Exception e) {
			FacesUtil.addWarnMessage("Ocorreu um problema com a logo da empresa");
		}
	}

	public void assinatura(FileUploadEvent event) {
		try {

			instituicao.setAssinatura(event.getFile().getContents());
			instituicaoService.salvar(instituicao);

			FacesUtil.addInfoMessage("Assinatura foi alterada com sucesso");

		} catch (Exception e) {
			FacesUtil.addWarnMessage("Ocorreu um problema com a assinatura");
		}
	}

	public void salvar() {
		try {

			instituicaoService.salvar(instituicao);
			FacesUtil.addInfoMessage("Empresa/Instituição adicionada com sucesso");

		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());

		} catch (Exception e) {
			e.printStackTrace();
			FacesUtil.addErrorMessage("Erro desconhecido: " + e.getMessage());
		}

	}

	public Instituicao getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}
}
