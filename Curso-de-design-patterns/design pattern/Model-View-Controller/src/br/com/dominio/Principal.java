package br.com.dominio;

import br.com.dominio.controller.Controlador;
import br.com.dominio.model.Modelo;
import br.com.dominio.view.Visao;

public class Principal {

	public static void main(String[] args) {
		
		Visao visao = new Visao();
		
		Modelo modelo = new Modelo();
		
		Controlador controlador = new Controlador(visao, modelo);
		
		visao.setVisible(true);
		
	}
	
}
