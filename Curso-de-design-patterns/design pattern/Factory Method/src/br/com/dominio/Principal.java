package br.com.dominio;

import javax.swing.JOptionPane;

import br.com.dominio.mensagem.Mensagem;
import br.com.dominio.mensagem.MensagemFactory;

public class Principal {

	public static void main(String[] args) {

		String texto = JOptionPane.showInputDialog(null);
		
		Mensagem mensagem = MensagemFactory.getMensagem(2);
		mensagem.enviar(texto);

	}

}
