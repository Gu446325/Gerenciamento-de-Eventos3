package com.trisoft.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.trisoft.model.Jogador;
import com.trisoft.model.Posicao;
import com.trisoft.service.JogadorService;
import com.trisoft.service.NegocioException;
import com.trisoft.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroJogadorMB implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private JogadorService jogadorService;
	private Jogador jogador;

	public void iniciar() {
		if (jogador == null) {
			limpar();
		}
	}

	public Posicao[] getPosicoes() {
		return Posicao.values();
	}

	private void limpar() {
		this.jogador = new Jogador();
	}

	public void salvar() {
		try {
			jogadorService.salvar(jogador);
			FacesUtil.addInfoMessage("Salvo com sucesso");

			limpar();
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public void excluir() {
		try {
			jogadorService.remover(jogador.getCodigo());
			FacesUtil.addInfoMessage("Jogador removido do sistema");

			limpar();
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public Jogador getJogador() {
		return jogador;
	}

	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}

}
