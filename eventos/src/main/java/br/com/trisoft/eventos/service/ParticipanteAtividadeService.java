package br.com.trisoft.eventos.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import br.com.trisoft.eventos.dao.ParticipanteAtividadeDAO;
import br.com.trisoft.eventos.model.Atividade;
import br.com.trisoft.eventos.model.Evento;
import br.com.trisoft.eventos.model.key.ParticipanteAtividadeId;
import br.com.trisoft.eventos.model.relationship.ParticipanteAtividade;
import br.com.trisoft.eventos.util.jpa.Transactional;

public class ParticipanteAtividadeService implements Serializable, Service<ParticipanteAtividade> {

	private static final long serialVersionUID = 1L;

	@Inject
	private ParticipanteAtividadeDAO participanteAtividadeDAO;

	@Override
	@Transactional
	public void salvar(ParticipanteAtividade participante) throws NegocioException {

		participante.setDataInscricao(new Date());

		if (participante.getId().getAtividade() == null) {
			throw new NegocioException("Não existe atividade vinculada ao participante");
		}

		if (participante.getId().getParticipante() == null) {
			throw new NegocioException("Você deve informar um participante para ser adicionado");
		}

		ParticipanteAtividade temp = buscarInscricao(participante.getId());

		if (temp != null) {
			String participanteTemp = participante.getId().getParticipante().getNome();
			throw new NegocioException(participanteTemp + " já está inscrito(a) nesta atividade");
		}

		this.participanteAtividadeDAO.persistir(participante);
	}

	@Override
	@Transactional
	public void remover(Long id) throws NegocioException {
		this.participanteAtividadeDAO.remover(id);

	}

	@Override
	public List<ParticipanteAtividade> buscarTodos() {
		return participanteAtividadeDAO.buscarTodos();
	}

	@Override
	public ParticipanteAtividade buscarPeloCodigo(Long id) {
		return participanteAtividadeDAO.buscarPeloCodigo(id);
	}

	@Transactional
	public void modificarPresenca(ParticipanteAtividade participante) throws NegocioException {

		if (participante.getPresenteNaAtividade() == false) {
			participante.setPresenteNaAtividade(true);
		}

		this.participanteAtividadeDAO.salvar(participante);
	}

	@Transactional
	public void modificarPagamento(ParticipanteAtividade participante) throws NegocioException {

		if (participante.getAtividadePaga() == false) {
			participante.setAtividadePaga(true);
		}

		this.participanteAtividadeDAO.salvar(participante);
	}

	@Transactional
	public ParticipanteAtividade buscarInscricao(ParticipanteAtividadeId id) {
		return participanteAtividadeDAO.buscarInscricao(id);
	}

	public Long buscarQuantidade(Atividade atividade) {
		return participanteAtividadeDAO.encontrarInscricaoQuantidade(atividade);
	}

	@Transactional
	public void remover(ParticipanteAtividade participante) throws NegocioException {

		if (participante.getAtividadePaga() == true) {
			throw new NegocioException("O participante já pagou a inscrição!");
		}

		if (participante.getPresenteNaAtividade() == true) {
			throw new NegocioException("O participante já confirmou sua presença!");
		}

		participante = participanteAtividadeDAO.buscarInscricao(participante.getId());
		participanteAtividadeDAO.remover(participante);
	}

	public Long getNumeroVagas(Atividade atividade) {
		Long inscritos = buscarQuantidade(atividade);
		Long vagas = atividade.getNumeroDeVagas();
		return vagas - inscritos;
	}

	public List<ParticipanteAtividade> buscarParticipanteConfirmado(Evento evento) {
		return participanteAtividadeDAO.buscarParticipanteConfirmado(evento);
	}

	public ParticipanteAtividade buscarPeloCodigoValidacao(Long codigo) {
		return participanteAtividadeDAO.porCodigoValidacao(codigo);
	}

}
