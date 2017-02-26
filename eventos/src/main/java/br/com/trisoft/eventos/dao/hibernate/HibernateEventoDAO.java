package br.com.trisoft.eventos.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import br.com.trisoft.eventos.dao.EventoDAO;
import br.com.trisoft.eventos.model.Evento;
import br.com.trisoft.eventos.model.Participante;

public class HibernateEventoDAO extends HibernateGenericDAO<Evento, Long>implements EventoDAO, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public List<Evento> buscarComPaginacao(Evento evento, int first, int pageSize) {

		String nome = likeBothUpperCase(evento.getNome());

		List<Evento> lista = getEntityManager().createNamedQuery("Evento.buscarTodos", Evento.class)
				.setParameter("nome", nome).setParameter("codeinstituicao", evento.getInstituicao().getId())
				.setFirstResult(first).setMaxResults(pageSize).getResultList();
		return lista;
	}

	@Override
	public Long encontrarQuantidade(Evento evento) {

		String nome = likeBothUpperCase(evento.getNome());

		Long count = getEntityManager().createNamedQuery("Evento.contar", Long.class).setParameter("nome", nome)
				.setParameter("codeinstituicao", evento.getInstituicao().getId()).getSingleResult();
		return count;
	}

	@Override
	public List<Evento> buscarAbertosComPaginacao(Evento evento, int first, int pageSize) {
		List<Evento> lista = getEntityManager().createNamedQuery("Evento.buscarTodosAbertos", Evento.class)
				.setFirstResult(first).setMaxResults(pageSize).getResultList();
		return lista;
	}

	@Override
	public Long encontrarAbertosQuantidade(Evento evento) {
		Long count = getEntityManager().createNamedQuery("Evento.contarAbertos", Long.class).getSingleResult();
		return count;
	}

	@Override
	public List<Participante> participantePorNome(String nome) {
		return this.getEntityManager().createNamedQuery("Participante.porNome", Participante.class)
				.setParameter("nome", likeBothUpperCase(nome)).getResultList();

	}

	@Override
	public List<Evento> buscarEventosPorInstituicao(Long id) {
		List<Evento> lista = getEntityManager().createNamedQuery("Evento.buscarPorInstituicao", Evento.class)
				.setParameter("codigoInstituicao", id).getResultList();
		return lista;

	}

	@Override
	public Evento buscarCodigoPorUsuario(Long codEvento, Long codUsuario) {
		return  getEntityManager().createNamedQuery("Evento.porCodigoDoUsuario", Evento.class)
				.setParameter("codEvento", codEvento).setParameter("codUsuario", codUsuario).getSingleResult(); 
	}

	// private String likeBoth(String nome) {
	// return "%" + nome.toUpperCase() + "%";
	// }
	//
	// private String likeRight(String nome) {
	// return nome.toUpperCase() + "%";
	// }
}
