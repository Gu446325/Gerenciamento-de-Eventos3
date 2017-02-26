package br.com.trisoft.eventos.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.trisoft.eventos.dao.InstituicaoDAO;
import br.com.trisoft.eventos.model.Instituicao;
import br.com.trisoft.eventos.util.jpa.Transactional;

public class InstituicaoService implements Serializable, Service<Instituicao> {

	private static final long serialVersionUID = 1L;

	@Inject
	private InstituicaoDAO instituicaoDAO;

	@Override
	@Transactional
	public void salvar(Instituicao instituicao) throws NegocioException {

		if (instituicao.getLogotipo().length <= 0) {
			throw new NegocioException("O logotipo não pode ficar vazio. Selecione uma imagem jpg ou png");
		}

		if (instituicao.getAssinatura().length <= 0 ) {
			throw new NegocioException("A assinatura não pode ficar vazia. Selecione uma imagem jpg ou png");
		}

		instituicaoDAO.salvar(instituicao);
	}

	@Transactional
	public Instituicao salvarComRetorno(Instituicao instituicao) throws NegocioException {

		// boolean existeCNPJ =
		// instituicaoDAO.isExisteCNPJ(instituicao.getCnpj());

		// if (StringUtils.isBlank(instituicao.getNome())) {
		// throw new NegocioException("O nome da empresa/instituição é
		// obrigatória");
		// }

		// if (existeCNPJ == true) {
		// throw new NegocioException("Este CNPJ já está cadastrado");
		// }

		// return instituicaoDAO.salvarComRetorno(instituicao);

		return null;
	}

	@Override
	@Transactional
	public void remover(Long id) throws NegocioException {
		instituicaoDAO.remover(id);
	}

	@Override
	public List<Instituicao> buscarTodos() {
		return instituicaoDAO.buscarTodos();
	}

	@Override
	public Instituicao buscarPeloCodigo(Long id) {
		return instituicaoDAO.buscarPeloCodigo(id);
	}

}
