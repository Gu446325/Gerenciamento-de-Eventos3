package br.com.trisoft.eventos.lazymodel;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.trisoft.eventos.dao.AtividadeDAO;
import br.com.trisoft.eventos.model.Atividade;
import br.com.trisoft.eventos.model.Evento;

public class AtividadeLazy extends LazyDataModel<Atividade>implements Serializable {

	private static final long serialVersionUID = 1L;

	private AtividadeDAO atividadeDAO;
	private Evento evento;

	public AtividadeLazy(Evento evento, AtividadeDAO atividadeDAO) {
		this.atividadeDAO = atividadeDAO;
		this.evento = evento;
	}

	@Override
	public List<Atividade> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {

		List<Atividade> Atividades = this.atividadeDAO.buscarComPaginacaoPorEvento(evento, first, pageSize);
		
		this.setRowCount(this.atividadeDAO.encontrarQuantidadePorEvento(evento).intValue());

		return Atividades;
	}

}
