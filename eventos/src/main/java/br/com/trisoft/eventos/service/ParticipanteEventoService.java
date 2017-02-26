package br.com.trisoft.eventos.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import br.com.trisoft.eventos.dao.ParticipanteEventoDAO;
import br.com.trisoft.eventos.model.key.ParticipanteEventoId;
import br.com.trisoft.eventos.model.relationship.ParticipanteEvento;
import br.com.trisoft.eventos.util.jpa.Transactional;

public class ParticipanteEventoService implements Serializable, Service<ParticipanteEvento> {

	private static final long serialVersionUID = 1L;

	@Inject
	private ParticipanteEventoDAO participanteEventoDAO;

	@Override
	@Transactional
	public void salvar(ParticipanteEvento participanteEvento) throws NegocioException {

		participanteEvento.setDataDeInscricao(new Date());

		if (participanteEvento.getId().getParticipante() == null) {
			throw new NegocioException("Você deve informar um participante para adicionar ao evento");
		}

		if (participanteEvento.getId().getEvento() == null) {
			throw new NegocioException(
					"Ocorreu um erro com o evento cadastrado. Volte a página de eventos e tente o processo novamente");
		}

		ParticipanteEvento temp = buscarInscricao(participanteEvento.getId());

		if (temp != null) {
			String participanteTemp = participanteEvento.getId().getParticipante().getNome();
			throw new NegocioException(participanteTemp + " já está inscrito(a) neste evento");
		}

		participanteEventoDAO.persistir(participanteEvento);
	}

	@Override
	@Transactional
	public void remover(Long id) throws NegocioException {
		participanteEventoDAO.remover(id);
	}

	@Override
	public List<ParticipanteEvento> buscarTodos() {
		return participanteEventoDAO.buscarTodos();
	}

	@Override
	public ParticipanteEvento buscarPeloCodigo(Long id) {
		return null;
	}

	@Transactional
	public ParticipanteEvento buscarInscricao(ParticipanteEventoId id) {
		return participanteEventoDAO.buscarInscricao(id);
	}

	@Transactional
	public void remover(ParticipanteEvento participanteEvento) throws NegocioException {

		// TODO implementar condições de remoção(se ja esta inscrito se ja pagou
		// a inscrição, etc)
		participanteEventoDAO.removerParticipanteInscrito(participanteEvento);
	}

}