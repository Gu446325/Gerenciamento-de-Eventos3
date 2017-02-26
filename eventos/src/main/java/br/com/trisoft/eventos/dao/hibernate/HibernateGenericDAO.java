package br.com.trisoft.eventos.dao.hibernate;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.com.trisoft.eventos.dao.GenericDAO;
import br.com.trisoft.eventos.service.NegocioException;

public abstract class HibernateGenericDAO<T, ID extends Serializable> implements GenericDAO<T, ID> {

	@Inject
	private EntityManager manager;

	private Class<T> classeEntidade;

	@SuppressWarnings("unchecked")
	public HibernateGenericDAO() {
		this.classeEntidade = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	@Override
	public T buscarPeloCodigo(ID id) {
		return manager.find(classeEntidade, id);
	}

	@Override
	public void salvar(T entidade) {
		manager.merge(entidade);
	}

	@Override
	public void persistir(T entidade) {
		manager.persist(entidade);
	}

	@Override
	public T salvarComRetorno(T entidade) {
		return manager.merge(entidade);
	}

	@Override
	public void remover(ID id) throws NegocioException {
		try {
			T entidade = buscarPeloCodigo(id);

			getEntityManager().remove(entidade);
			getEntityManager().flush();

		} catch (PersistenceException e) {
			throw new NegocioException("Falha na exclusão");
		}

	}

	@Override
	public void remover(T entidade) throws NegocioException {
		getEntityManager().remove(entidade);
		getEntityManager().flush();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> buscarTodos() {
		Criteria criteria = getSession().createCriteria(classeEntidade);
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> filtrar(T entidade, String... propriedades) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(classeEntidade);

		if (propriedades != null) {
			for (String propriedade : propriedades) {
				try {
					Object valor = PropertyUtils.getProperty(entidade, propriedade);
					if (valor != null) {
						if (valor instanceof String) {
							criteria.add(Restrictions.ilike(propriedade, (String) valor, MatchMode.ANYWHERE));
						} else {
							criteria.add(Restrictions.eq(propriedade, valor));
						}
					}
				} catch (Exception e) {
					throw new RuntimeException("Propriedade não encontrada", e);
				}
			}
		}

		return criteria.list();

	}
	
	protected String likeBothUpperCase(String nome) {
		return "%" + nome.toUpperCase() + "%";
	}

	protected String likeRightUpperCase(String nome) {
		return nome.toUpperCase() + "%";
	}

	protected EntityManager getEntityManager() {
		return manager;
	}

	protected Session getSession() {
		return manager.unwrap(Session.class);
	}

}
