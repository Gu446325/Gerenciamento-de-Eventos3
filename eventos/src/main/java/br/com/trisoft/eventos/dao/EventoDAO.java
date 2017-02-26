package br.com.trisoft.eventos.dao;

import java.util.List;

import br.com.trisoft.eventos.model.Evento;
import br.com.trisoft.eventos.model.Participante;

public interface EventoDAO extends GenericDAO<Evento, Long> {

	public List<Evento> buscarComPaginacao(Evento evento, int first, int pageSize);

	public Long encontrarQuantidade(Evento evento);

	public List<Evento> buscarAbertosComPaginacao(Evento evento, int first, int pageSize);

	public Long encontrarAbertosQuantidade(Evento evento);

	public List<Participante> participantePorNome(String nome);

	public List<Evento> buscarEventosPorInstituicao(Long id);

	public Evento buscarCodigoPorUsuario(Long idEvento, Long idUsuario);
}
