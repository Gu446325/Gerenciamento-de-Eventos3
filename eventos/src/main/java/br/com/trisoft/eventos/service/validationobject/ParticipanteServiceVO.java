package br.com.trisoft.eventos.service.validationobject;

import java.io.Serializable;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import br.com.trisoft.eventos.dao.ParticipanteDAO;
import br.com.trisoft.eventos.dao.UsuarioDAO;
import br.com.trisoft.eventos.model.Participante;
import br.com.trisoft.eventos.model.Usuario;
import br.com.trisoft.eventos.service.NegocioException;
import br.com.trisoft.eventos.util.jpa.Transactional;

public class ParticipanteServiceVO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ParticipanteDAO participanteDAO;
	
	@Inject
	private UsuarioDAO usuarioDAO;
	
	@Transactional
	public void salvar(Participante participante, Usuario usuario) throws NegocioException{
		
		String email = participante.getEmail().toLowerCase();
		participante.setEmail(email);
		
		
		//Participante
		if (StringUtils.isBlank(participante.getNome())) {
			throw new NegocioException("O nome do participante é obrigatório");
		}

		if (participante.getNome().length() < 3 || participante.getNome().length() > 80) {
			throw new NegocioException("O nome deve conter entre 3 e 80 caracteres");
		}

		if (isExisteEmail(email)) {
			throw new NegocioException("Este e-mail já está cadastrado no sistema");
		}

		if (StringUtils.isBlank(participante.getEmail())) {
			throw new NegocioException("O e-mail do participante é obrigatório");
		}

		usuario.setEmail(email);
		usuario.setNome(participante.getNome());

		//Usuario
		if (usuarioDAO.isExisteEmail(usuario.getEmail()) == true) {
			throw new NegocioException("Este e-mail já está cadastrado");
		}

		if (usuario.getSenha().length() < 6) {
			throw new NegocioException("Sua senha precisa ter pelo menos 6 digitos");
		}
		
		
		participanteDAO.salvar(participante);
		usuarioDAO.salvar(usuario);
	}

	//TODO não deve necessitar transactional
	private boolean isExisteEmail(String email) {
		return participanteDAO.isExisteEmail(email.toLowerCase());
	}
}
