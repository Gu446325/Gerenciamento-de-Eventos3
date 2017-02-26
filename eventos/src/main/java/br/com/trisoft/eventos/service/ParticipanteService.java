package br.com.trisoft.eventos.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import br.com.trisoft.eventos.dao.ParticipanteDAO;
import br.com.trisoft.eventos.model.Participante;
import br.com.trisoft.eventos.util.jpa.Transactional;

public class ParticipanteService implements Serializable, Service<Participante> {

	private static final long serialVersionUID = 1L;

	@Inject
	private ParticipanteDAO participanteDAO;

	@Transactional
	@Override
	public void salvar(Participante participante) throws NegocioException {

		String email = participante.getEmail().toLowerCase();
		participante.setEmail(email);

		if (participante.getNome().length() < 3 || participante.getNome().length() > 80) {
			throw new NegocioException("O nome deve conter entre 3 e 80 caracteres");
		}

		if (StringUtils.isBlank(participante.getNome())) {
			throw new NegocioException("O nome do participante é obrigatório");
		}

		// TODO codigo null ainda em testes
		if (isExisteEmail(email) && participante.getId() == null) {
			throw new NegocioException("Este e-mail já está cadastrado no sistema");
		}

		if (StringUtils.isBlank(participante.getEmail())) {
			throw new NegocioException("O e-mail do participante é obrigatório");
		}

		participante.setNome(participante.getNome());

		participanteDAO.salvar(participante);
	}

	@Override
	public void remover(Long id) throws NegocioException {
		// TODO Auto-generated method stub
	}

	@Override
	public List<Participante> buscarTodos() {
		return participanteDAO.buscarTodos();
	}

	@Override
	public Participante buscarPeloCodigo(Long id) {
		return participanteDAO.buscarPeloCodigo(id);

	}

	@Transactional
	public boolean isExisteEmail(String email) {
		return participanteDAO.isExisteEmail(email.toLowerCase());
	}

	public Participante porEmail(String email) {
		return participanteDAO.porEmail(email);
	}
}
