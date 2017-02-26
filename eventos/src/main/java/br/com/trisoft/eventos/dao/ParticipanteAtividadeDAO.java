package br.com.trisoft.eventos.dao;

import java.util.List;

import br.com.trisoft.eventos.model.Atividade;
import br.com.trisoft.eventos.model.Evento;
import br.com.trisoft.eventos.model.Participante;
import br.com.trisoft.eventos.model.key.ParticipanteAtividadeId;
import br.com.trisoft.eventos.model.relationship.ParticipanteAtividade;

public interface ParticipanteAtividadeDAO extends GenericDAO<ParticipanteAtividade, Long> {

	public ParticipanteAtividade buscarInscricao(ParticipanteAtividadeId id);

	public List<ParticipanteAtividade> buscarInscricaoComPaginacao(int first, int pageSize, Atividade atividade);

	public Long encontrarInscricaoQuantidade(Atividade atividade);

	public List<ParticipanteAtividade> buscarComPaginacaoPorParticipante(Participante participante, int first,
			int pageSize);

	public Long encontrarQuantidadePorParticipante(Participante participante);

	public List<ParticipanteAtividade> buscarParticipanteConfirmado(int first, int pageSize, Evento evento);

	public Long contarParticipanteConfirmado(Evento evento);

	public List<ParticipanteAtividade> buscarParticipanteConfirmado(Evento evento);

	public ParticipanteAtividade porCodigoValidacao(Long codigo);

}
