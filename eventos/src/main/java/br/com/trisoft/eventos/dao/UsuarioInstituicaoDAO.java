package br.com.trisoft.eventos.dao;

import br.com.trisoft.eventos.model.key.UsuarioInstituicaoId;
import br.com.trisoft.eventos.model.relationship.UsuarioInstituicao;

public interface UsuarioInstituicaoDAO extends GenericDAO<UsuarioInstituicao, Long> {

	public UsuarioInstituicao buscarPeloCodigo(UsuarioInstituicaoId id);

	public Long buscarCodigoDaInstituicao(Long idUsuario);

}
