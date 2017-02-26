package br.com.trisoft.eventos.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import br.com.trisoft.eventos.dao.ParticipanteAtividadeDAO;
import br.com.trisoft.eventos.model.Atividade;
import br.com.trisoft.eventos.model.Evento;
import br.com.trisoft.eventos.model.Participante;
import br.com.trisoft.eventos.model.key.ParticipanteAtividadeId;
import br.com.trisoft.eventos.model.relationship.ParticipanteAtividade;

public class HibernateParticipanteAtividadeDAO extends HibernateGenericDAO<ParticipanteAtividade, Long>
		implements ParticipanteAtividadeDAO, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public ParticipanteAtividade buscarInscricao(ParticipanteAtividadeId id) {
		return getEntityManager().find(ParticipanteAtividade.class, id);
	}

	@Override
	public List<ParticipanteAtividade> buscarInscricaoComPaginacao(int first, int pageSize, Atividade atividade) {
		List<ParticipanteAtividade> atividades = getEntityManager()
				.createNamedQuery("ParticipanteAtividade.buscarTodos", ParticipanteAtividade.class)
				.setParameter("Atividade", atividade).setFirstResult(first).setMaxResults(pageSize).getResultList();
		return atividades;
	}

	@Override
	public Long encontrarInscricaoQuantidade(Atividade atividade) {
		Long count = getEntityManager().createNamedQuery("ParticipanteAtividade.contar", Long.class)
				.setParameter("Atividade", atividade).getSingleResult();
		return count;
	}

	@Override
	public List<ParticipanteAtividade> buscarComPaginacaoPorParticipante(Participante participante, int first,
			int pageSize) {
		List<ParticipanteAtividade> atividades = getEntityManager()
				.createNamedQuery("ParticipanteAtividade.buscarTodosPorParticipante", ParticipanteAtividade.class)
				.setParameter("Participante", participante).setFirstResult(first).setMaxResults(pageSize)
				.getResultList();
		return atividades;
	}

	@Override
	public Long encontrarQuantidadePorParticipante(Participante participante) {
		Long count = getEntityManager().createNamedQuery("ParticipanteAtividade.contarPorParticipante", Long.class)
				.setParameter("Participante", participante).getSingleResult();
		return count;
	}

	@Override
	public List<ParticipanteAtividade> buscarParticipanteConfirmado(int first, int pageSize, Evento evento) {
		List<ParticipanteAtividade> atividades = getEntityManager()
				.createNamedQuery("ParticipanteAtividade.buscarParticipanteConfirmado", ParticipanteAtividade.class)
				.setParameter("Evento", evento).setFirstResult(first).setMaxResults(pageSize).getResultList();
		return atividades;
	}

	@Override
	public Long contarParticipanteConfirmado(Evento evento) {
		Long count = getEntityManager()
				.createNamedQuery("ParticipanteAtividade.contarParticipanteConfirmado", Long.class)
				.setParameter("Evento", evento).getSingleResult();
		return count;
	}

	@Override
	public List<ParticipanteAtividade> buscarParticipanteConfirmado(Evento evento) {
		List<ParticipanteAtividade> atividades = getEntityManager()
				.createNamedQuery("ParticipanteAtividade.buscarParticipanteConfirmado", ParticipanteAtividade.class)
				.setParameter("Evento", evento).getResultList();
		return atividades;
	}

	@Override
	public ParticipanteAtividade porCodigoValidacao(Long codigo) {
		ParticipanteAtividade pa = getEntityManager()
				.createNamedQuery("ParticipanteAtividade.porValidacao", ParticipanteAtividade.class)
				.setParameter("codigo", codigo).getSingleResult();
		return pa;
	}

}
