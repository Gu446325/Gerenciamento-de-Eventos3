package br.com.dominio.pc.facade;

import br.com.dominio.fabrica.Acessorios;
import br.com.dominio.pc.fabrica.CPU;
import br.com.dominio.pc.fabrica.HD;
import br.com.dominio.pc.fabrica.Memoria;
import br.com.dominio.pc.fabrica.SO;

public class ComputerFacade {

	private Memoria memoria;
	private HD hd;
	private CPU cpu;
	private Acessorios acessorios;
	private SO so;

	public ComputerFacade() {

		this.memoria = new Memoria();
		this.hd = new HD();
		this.cpu = new CPU();
		this.acessorios = new Acessorios();
		this.so = new SO();

	}

	public void montarPC() {
		System.out.println("Montando uma nova maquina\n");
		
		memoria.adicionarMemoria();
		hd.adicionarHD();
		cpu.adicionaProcessador();
		acessorios.adicionarMouse();
		acessorios.adicionarTeclado();
		acessorios.adicionarMonitor();
		so.instalarSO();
		
		System.out.println("\nComputador fabricado");
	}

}
