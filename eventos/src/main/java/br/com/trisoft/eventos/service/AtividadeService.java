package br.com.trisoft.eventos.service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.inject.Inject;

import br.com.trisoft.eventos.dao.AtividadeDAO;
import br.com.trisoft.eventos.model.Atividade;
import br.com.trisoft.eventos.model.Evento;
import br.com.trisoft.eventos.util.jpa.Transactional;

public class AtividadeService implements Serializable, Service<Atividade> {

	private static final long serialVersionUID = 1L;

	@Inject
	private AtividadeDAO atividadeDAO;

	@Override
	@Transactional
	public void salvar(Atividade atividade) throws NegocioException {

		if (atividade.getEvento() == null) {
			throw new NegocioException("Esta atividade não está associada a nenhum evento");
		}

		if (atividade.getNome().isEmpty()) {
			throw new NegocioException("O nome da atividade deve ser preenchido. Exemplo: Curso de culinária japonesa");
		}

		if (atividade.getLocalDeRealizacao().isEmpty()) {
			throw new NegocioException("O local de realização deve ser preenchido. Exemplo: Sala 20 primeiro andar");
		}

		if (atividade.getDataDeInicio() == null) {
			throw new NegocioException("Defina uma data de ínicio para a atividade");
		}

		if (atividade.getDataDeTermino() == null) {
			throw new NegocioException("Defina uma data de término para a atividade");
		}

		if (atividade.getValorDaAtividade() == null) {
			atividade.setValorDaAtividade(BigDecimal.ZERO);
		}

		if (atividade.getValorDaAtividade().doubleValue() < 0) {
			throw new NegocioException("O valor da atividade não pode ser menor que 0,00");
		}

		atividade.setNome(atividade.getNome());

		atividadeDAO.salvar(atividade);
	}

	@Override
	@Transactional
	public void remover(Long id) throws NegocioException {
		atividadeDAO.remover(id);
	}

	@Override
	public List<Atividade> buscarTodos() {
		return null;
	}

	@Override
	public Atividade buscarPeloCodigo(Long id) {
		return atividadeDAO.buscarPeloCodigo(id);
	}

}
