package br.com.trisoft.eventos.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.UploadedFile;

import br.com.trisoft.eventos.model.Estado;
import br.com.trisoft.eventos.model.Grupo;
import br.com.trisoft.eventos.model.Instituicao;
import br.com.trisoft.eventos.model.Usuario;
import br.com.trisoft.eventos.service.GrupoService;
import br.com.trisoft.eventos.service.NegocioException;
import br.com.trisoft.eventos.service.validationobject.InstituicaoServiceVO;
import br.com.trisoft.eventos.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroInstituicaoMB implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Instituicao instituicao;

	@Inject
	private Usuario usuario;

	@Inject
	private GrupoService grupoService;

	@Inject
	private InstituicaoServiceVO serviceVO;

	private String senha;

	private Grupo grupo;

	private List<Grupo> grupos;

	private UploadedFile assinatura;
	private UploadedFile logotipo;

	public void iniciar() {
		if (instituicao == null) {
			limpar();
		}
	}

	public Estado[] getEstados() {
		return Estado.values();
	}

	public void cadastroInstituicao() {

		try {

			if (this.assinatura != null) {
				this.instituicao.setAssinatura(assinatura.getContents());
			}

			if (this.logotipo != null) {
				this.instituicao.setLogotipo(logotipo.getContents());
			}

			prepararUsuario();
			serviceVO.salvar(instituicao, usuario);

			FacesUtil.addInfoMessage("Empresa/Instituição adicionada com sucesso");
			limpar();

		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());

		} catch (Exception e) {
			FacesUtil.addErrorMessage("Erro desconhecido: " + e.getMessage());
		}

	}

	private void prepararUsuario() throws NegocioException {

		grupos = new ArrayList<>();

		grupo = grupoService.buscarPorEmpresa();
		grupos.add(grupo);

		usuario.setNome(instituicao.getNome());
		usuario.setEmail(instituicao.getEmail().toLowerCase());
		usuario.setSenha(senha);
		usuario.setGrupos(grupos);
	}

	private void limpar() {
		instituicao = new Instituicao();
		usuario = new Usuario();
		senha = "";
	}

	public Instituicao getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public UploadedFile getAssinatura() {
		return assinatura;
	}

	public void setAssinatura(UploadedFile assinatura) {
		this.assinatura = assinatura;
	}

	public UploadedFile getLogotipo() {
		return logotipo;
	}

	public void setLogotipo(UploadedFile logotipo) {
		this.logotipo = logotipo;
	}

}
