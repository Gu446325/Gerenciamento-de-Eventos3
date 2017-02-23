package br.com.dominio.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.com.dominio.model.Modelo;
import br.com.dominio.view.Visao;

public class Controlador {
	
	private Visao visao;
	private Modelo modelo;
	
	public Controlador(Visao visao, Modelo modelo) {

		this.visao = visao;
		this.modelo = modelo;
	
		this.visao.adicionaListener(new Listener());
		
	}
	
	class Listener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			int numero1, numero2 = 0;
			
			try {
				
				numero1 = visao.getPrimeiroNumero();
				numero2 = visao.getSegundoNumero();
				
				modelo.somaNumeros(numero1, numero2);
				
				visao.setResultado(modelo.getValor());
				
				
			} catch (Exception exception) {

				visao.mensagemErro("Insira dois números inteiros");
				
			}
			
		}
		
		
		
	}
	
	

}
