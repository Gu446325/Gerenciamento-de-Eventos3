package br.com.trisoft.eventos.lazymodel;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.trisoft.eventos.dao.ParticipanteAtividadeDAO;
import br.com.trisoft.eventos.model.Participante;
import br.com.trisoft.eventos.model.relationship.ParticipanteAtividade;

public class MinhasAtividadesLazy extends LazyDataModel<ParticipanteAtividade>implements Serializable {

	private static final long serialVersionUID = 1L;

	private Participante participante;
	private ParticipanteAtividadeDAO participanteAtividadeDAO;

	public MinhasAtividadesLazy(Participante participante, ParticipanteAtividadeDAO participanteAtividadeDAO) {
		this.participante = participante;
		this.participanteAtividadeDAO = participanteAtividadeDAO;
	}

	@Override
	public List<ParticipanteAtividade> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {

		List<ParticipanteAtividade> atividades = this.participanteAtividadeDAO
				.buscarComPaginacaoPorParticipante(participante, first, pageSize);
		this.setRowCount(this.participanteAtividadeDAO.encontrarQuantidadePorParticipante(participante).intValue());

		return atividades;
	}

}
