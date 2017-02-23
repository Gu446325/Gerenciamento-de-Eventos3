package com.trisoft.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.trisoft.dao.JogadorDAO;
import com.trisoft.model.Jogador;

@Named
@ViewScoped
public class PesquisaJogadorMB implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private JogadorDAO jogadorDAO;
	private List<Jogador> jogadores;
	private Jogador jogador;

	public void pesquisar() {
		if (jogador == null) {
			jogador = new Jogador();
		}

		jogadores = jogadorDAO.buscarTodos();
	}

	public void filtro() {
		jogadores = jogadorDAO.filtrarJogador(jogador);
	}

	public List<Jogador> getJogadores() {
		return jogadores;
	}

	public Jogador getJogador() {
		return jogador;
	}

	public void setJogador(Jogador jogadorSelecionado) {
		this.jogador = jogadorSelecionado;
	}

}
