package com.trisoft.dao;

import java.io.Serializable;
import java.util.List;

public abstract interface GenericDAO<T, ID extends Serializable> {

	public T buscarPeloCodigo(ID id);

	public void salvar(T entidade);

	public List<T> filtrar(T entidade, String... propriedades);

	public void remover(T entidade);
	
	public void remover(ID id);

	public List<T> buscarTodos();
}
