package br.com.trisoft.eventos.dao;

import java.util.List;

import br.com.trisoft.eventos.model.Participante;

public interface ParticipanteDAO extends GenericDAO<Participante, Long> {

	public boolean isExisteEmail(String email);

	public List<Participante> buscarParticipanteComPaginacao(int first, int pageSize, Participante participante);

	public Long encontrarParticipanteQuantidade(Participante participante);

	public Participante porEmail(String email);

}