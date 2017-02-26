package br.com.trisoft.eventos.service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import br.com.trisoft.eventos.dao.EventoDAO;
import br.com.trisoft.eventos.model.Estado;
import br.com.trisoft.eventos.model.Evento;
import br.com.trisoft.eventos.util.jpa.Transactional;

public class EventoService implements Serializable, Service<Evento> {

	private static final long serialVersionUID = 1L;

	@Inject
	private EventoDAO eventoDAO;

	@Transactional
	@Override
	public void salvar(Evento evento) throws NegocioException {
		eventoDAO.salvar(evento);
	}

	@Transactional
	public void remover(Long id) throws NegocioException {
		eventoDAO.remover(id);
	}

	@Override
	public List<Evento> buscarTodos() {
		return eventoDAO.buscarTodos();
	}

	@Override
	public Evento buscarPeloCodigo(Long id) {
		return eventoDAO.buscarPeloCodigo(id);
	}

	@Transactional
	public Evento salvarComRetorno(Evento evento) throws NegocioException {

		if (StringUtils.isBlank(evento.getNome())) {
			throw new NegocioException("O nome do evento é obrigatório!");
		}

		if (evento.getCusto() == null) {
			evento.setCusto(BigDecimal.ZERO);
		}

		if (evento.getCusto().doubleValue() < 0) {
			throw new NegocioException("O valor do evento não pode ser menor que 0,00");
		}

		if (StringUtils.isBlank(evento.getLocal())) {
			throw new NegocioException("O local do evento é obrigatório!");
		}

		if (StringUtils.isBlank(evento.getCidade())) {
			throw new NegocioException("A cidade do evento é obrigatória!");
		}

		if (evento.getEstado() == null) {
			evento.setEstado(Estado.RO);
		}

		return eventoDAO.salvarComRetorno(evento);
	}
	
	public Evento buscarCodigoDoEvento(Long idEvento, Long idUsuario) {
		return eventoDAO.buscarCodigoPorUsuario(idEvento, idUsuario);
	}
}
