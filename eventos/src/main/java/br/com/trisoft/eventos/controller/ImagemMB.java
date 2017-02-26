package br.com.trisoft.eventos.controller;

import java.io.ByteArrayInputStream;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.trisoft.eventos.model.Instituicao;

@Named
@SessionScoped
public class ImagemMB implements Serializable{

	private static final long serialVersionUID = 1L;

	private Instituicao instituicaoSelecionada;
	
	public StreamedContent getLogotipo() {
		DefaultStreamedContent content = null;
		if (this.instituicaoSelecionada != null && this.instituicaoSelecionada.getLogotipo() != null
				&& this.instituicaoSelecionada.getLogotipo().length > 0) {
			byte[] imagem = this.instituicaoSelecionada.getLogotipo();
			content = new DefaultStreamedContent(new ByteArrayInputStream(imagem), "image/jpg", "logotipo.jpg");
		}
		
		return content;
	}
	
	public StreamedContent getAssinatura() {
		DefaultStreamedContent content = null;
		if (this.instituicaoSelecionada != null && this.instituicaoSelecionada.getAssinatura() != null
				&& this.instituicaoSelecionada.getAssinatura().length > 0) {
			byte[] imagem = this.instituicaoSelecionada.getAssinatura();
			content = new DefaultStreamedContent(new ByteArrayInputStream(imagem), "image/jpg", "assinatura.jpg");
		}
		
		return content;
	}

	public Instituicao getInstituicaoSelecionada() {
		return instituicaoSelecionada;
	}

	public void setInstituicaoSelecionada(Instituicao instituicaoSelecionada) {
		this.instituicaoSelecionada = instituicaoSelecionada;
	}
	
	

}
