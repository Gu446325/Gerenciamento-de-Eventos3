package br.com.trisoft.eventos.lazymodel;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.trisoft.eventos.dao.ParticipanteAtividadeDAO;
import br.com.trisoft.eventos.model.Atividade;
import br.com.trisoft.eventos.model.relationship.ParticipanteAtividade;

public class ParticipanteAtividadeLazy extends LazyDataModel<ParticipanteAtividade>implements Serializable {

	private static final long serialVersionUID = 1L;

	private ParticipanteAtividadeDAO dao;
	private Atividade atividade;

	public ParticipanteAtividadeLazy(ParticipanteAtividadeDAO dao, Atividade atividade) {
		this.dao = dao;
		this.atividade = atividade;
	}

	@Override
	public List<ParticipanteAtividade> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {

		List<ParticipanteAtividade> participantes = this.dao.buscarInscricaoComPaginacao(first, pageSize, atividade);

		this.setRowCount(this.dao.encontrarInscricaoQuantidade(atividade).intValue());

		return participantes;
	}

}
