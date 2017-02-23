package com.trisoft.service;

import java.io.Serializable;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import com.trisoft.dao.JogadorDAO;
import com.trisoft.model.Jogador;
import com.trisoft.util.jpa.Transactional;

public class JogadorService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private JogadorDAO repository;

	@Transactional
	public void salvar(Jogador jogador) throws NegocioException {

		jogador.setNome(upperCase(jogador));

		validar(jogador);
		repository.salvar(jogador);
	}

	private void validar(Jogador jogador) throws NegocioException {

		if (StringUtils.isBlank(jogador.getNome())) {
			throw new NegocioException("O Nome é obrigatório");
		}

		if (jogador.getDuracaoContrato() == null) {
			throw new NegocioException("Contrato não pode ficar em branco");
		}

	}

	private String upperCase(Jogador jogador) {
		return jogador.getNome().toUpperCase();
	}

	@Transactional
	public void remover(Long id) throws NegocioException{
		repository.remover(id);
	}
	
	@Transactional
	public void remover(Jogador jogador) throws NegocioException{
		repository.remover(jogador);
	}

}
