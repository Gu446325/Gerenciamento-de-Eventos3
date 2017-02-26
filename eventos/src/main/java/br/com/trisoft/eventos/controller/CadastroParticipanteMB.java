package br.com.trisoft.eventos.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.trisoft.eventos.model.Grupo;
import br.com.trisoft.eventos.model.Participante;
import br.com.trisoft.eventos.model.Usuario;
import br.com.trisoft.eventos.service.GrupoService;
import br.com.trisoft.eventos.service.NegocioException;
import br.com.trisoft.eventos.service.validationobject.ParticipanteServiceVO;
import br.com.trisoft.eventos.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroParticipanteMB implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Participante participante;

	@Inject
	private ParticipanteServiceVO participanteVO;

	@Inject
	private Usuario usuario;

	@Inject
	private GrupoService grupoService;

	@PostConstruct
	public void iniciar() {
		if (participante == null) {
			limpar();
		}
	}

	public void salvar() throws NegocioException {

		// participanteService.salvar(participante);

		try {

			prepararUsuario();
			participanteVO.salvar(participante, usuario);

			FacesUtil.addInfoMessage("Participante cadastrado com sucesso");

			limpar();

		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	private void prepararUsuario() throws NegocioException {

		List<Grupo> grupos = new ArrayList<>();
		Grupo grupo = grupoService.buscarPorParticipante();

		grupos.add(grupo);
		usuario.setGrupos(grupos);
	}

	private void limpar() {
		participante = new Participante();
		usuario = new Usuario();
	}

	public Participante getParticipante() {
		return participante;
	}

	public void setParticipante(Participante participante) {
		this.participante = participante;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
