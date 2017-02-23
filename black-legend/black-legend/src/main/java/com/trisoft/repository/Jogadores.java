package com.trisoft.repository;

import java.util.List;

import com.trisoft.model.Jogador;
import com.trisoft.service.NegocioException;
import com.trisoft.util.jpa.Transactional;

public interface Jogadores {

	public Jogador buscarPeloCodigo(Long id);

	@Transactional
	public void remover(Long id) throws NegocioException;

	public List<Jogador> buscarTodos();

	@Transactional
	public void salvar(Jogador jogador);

	public List<Jogador> filtrarJogador(Jogador jogador);

}
