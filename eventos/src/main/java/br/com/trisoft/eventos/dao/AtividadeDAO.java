package br.com.trisoft.eventos.dao;

import java.util.List;

import br.com.trisoft.eventos.model.Atividade;
import br.com.trisoft.eventos.model.Evento;

public interface AtividadeDAO extends GenericDAO<Atividade, Long> {

	public List<Atividade> buscarComPaginacaoPorEvento(Evento evento, int first, int pageSize);

	public Long encontrarQuantidadePorEvento(Evento evento);
	
	

}
