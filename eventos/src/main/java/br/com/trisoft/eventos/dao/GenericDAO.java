package br.com.trisoft.eventos.dao;

import java.io.Serializable;
import java.util.List;

import br.com.trisoft.eventos.service.NegocioException;

public abstract interface GenericDAO<T, ID extends Serializable> {

	public T buscarPeloCodigo(ID id);

	public void salvar(T entidade);

	public void persistir(T entidade);

	public T salvarComRetorno(T entidade);

	public void remover(ID id) throws NegocioException;

	public void remover(T entidade) throws NegocioException;

	public List<T> buscarTodos();

	public List<T> filtrar(T entidade, String... propriedades);


}
