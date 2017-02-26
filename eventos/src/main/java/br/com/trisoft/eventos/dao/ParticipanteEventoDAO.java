package br.com.trisoft.eventos.dao;

import java.util.List;

import br.com.trisoft.eventos.model.Evento;
import br.com.trisoft.eventos.model.key.ParticipanteEventoId;
import br.com.trisoft.eventos.model.relationship.ParticipanteEvento;

public interface ParticipanteEventoDAO extends GenericDAO<ParticipanteEvento, Long> {

	public ParticipanteEvento buscarInscricao(ParticipanteEventoId id);

	public void removerParticipanteInscrito(ParticipanteEvento participanteEvento);

	public List<ParticipanteEvento> buscarInscricaoComPaginacao(int first, int pageSize, Evento evento);

	public Long encontrarInscricaoQuantidade(Evento evento);
}
