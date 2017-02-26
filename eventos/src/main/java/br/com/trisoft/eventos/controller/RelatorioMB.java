package br.com.trisoft.eventos.controller;

import java.io.Serializable;
import java.util.Map;

import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import br.com.trisoft.eventos.util.jsf.FacesUtil;
import br.com.trisoft.eventos.util.report.ExecutorRelatorio;

@Named
@RequestScoped
public class RelatorioMB implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FacesContext facesContext;

	@Inject
	private HttpServletResponse response;

	@Inject
	private EntityManager manager;

	public void emitir(Map<String, Object> parametros, String caminho, String nome) {

		ExecutorRelatorio executor = new ExecutorRelatorio(caminho, this.response, parametros, nome);

		Session session = manager.unwrap(Session.class);
		session.doWork(executor);

		if (executor.isRelatorioGerado()) {

			facesContext.responseComplete();

		} else {

			FacesUtil.addErrorMessage("A execução do relatório não retornou dados.");
		}

	}

}
