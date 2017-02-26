package br.com.trisoft.eventos.service;

import java.sql.SQLException;
import java.util.List;

public interface Service<T> {

	public void salvar(T t) throws NegocioException, SQLException;

	public void remover(Long id) throws NegocioException;

	public List<T> buscarTodos();

	public T buscarPeloCodigo(Long id);

}
