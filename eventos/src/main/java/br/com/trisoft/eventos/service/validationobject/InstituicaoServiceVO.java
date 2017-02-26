package br.com.trisoft.eventos.service.validationobject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import br.com.trisoft.eventos.dao.InstituicaoDAO;
import br.com.trisoft.eventos.dao.UsuarioDAO;
import br.com.trisoft.eventos.model.Instituicao;
import br.com.trisoft.eventos.model.Usuario;
import br.com.trisoft.eventos.security.Seguranca;
import br.com.trisoft.eventos.service.InstituicaoService;
import br.com.trisoft.eventos.service.NegocioException;
import br.com.trisoft.eventos.service.UsuarioInstituicaoService;
import br.com.trisoft.eventos.util.jpa.Transactional;

public class InstituicaoServiceVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private InstituicaoDAO instituicaoDAO;
	@Inject
	private UsuarioDAO usuarioDAO;

	@Transactional
	public void salvar(Instituicao instituicao, Usuario usuario) throws NegocioException {

		boolean existeCNPJ = instituicaoDAO.isExisteCNPJ(instituicao.getCnpj());

		String emailInstituicao = instituicao.getEmail().toLowerCase();

		if (StringUtils.isBlank(instituicao.getNome())) {
			throw new NegocioException("O nome da empresa/instituição é obrigatória");
		}

		if (instituicao.getNome().length() < 3 || instituicao.getNome().length() > 80) {
			throw new NegocioException("O nome da instituição deve conter entre 3 e 80 caracteres");
		}

		if (existeCNPJ == true) {
			throw new NegocioException("Este CNPJ já está cadastrado");
		}

		if (StringUtils.isBlank(instituicao.getEndereco())) {
			throw new NegocioException("O endereço é obrigatório");
		}

		if (StringUtils.isBlank(instituicao.getCidade())) {
			throw new NegocioException("O nome da cidade é obrigatória");
		}

		if (StringUtils.isBlank(emailInstituicao)) {
			throw new NegocioException("E-mail é obrigatório");
		}

		if (usuarioDAO.isExisteEmail(usuario.getEmail()) == true) {
			throw new NegocioException("Este e-mail já está cadastrado");
		}

		if (usuario.getSenha().length() < 6) {
			throw new NegocioException("Sua senha precisa ter pelo menos 6 digitos");
		}

		if (instituicao.getLogotipo().length <= 0) {
			throw new NegocioException("O logotipo não pode ficar vazio. Selecione uma imagem jpg ou png");
		}

		if (instituicao.getAssinatura().length <= 0) {
			throw new NegocioException("A assinatura não pode ficar vazia. Selecione uma imagem jpg ou png");
		}

		instituicao = instituicaoDAO.salvarComRetorno(instituicao);

		if (instituicao != null) {

			// usuario.setNome(instituicao.getNome());
			// usuario.setEmail(instituicao.getEmail());

			List<Instituicao> lista = new ArrayList<>();
			lista.add(instituicao);
			usuario.setUsuarioInstituicao(lista);

			usuarioDAO.salvar(usuario);
		}

	}

	@Inject
	private Seguranca seguranca;
	@Inject
	private UsuarioInstituicaoService usuarioInstituicaoService;
	@Inject
	private InstituicaoService instituicaoService;

	/**
	 * Busca a instituição através do código do usuário logado
	 * 
	 * @author luiz
	 * @return Instituicao
	 */
	public Instituicao getInstituicao() {

		Long idUsuario = seguranca.getUsuarioLogado().getUsuario().getId();
		Long idInstituicao = usuarioInstituicaoService.buscarCodigoDaInstituicao(idUsuario);
		Instituicao instituicao = instituicaoService.buscarPeloCodigo(idInstituicao);

		return instituicao;
	}

}
