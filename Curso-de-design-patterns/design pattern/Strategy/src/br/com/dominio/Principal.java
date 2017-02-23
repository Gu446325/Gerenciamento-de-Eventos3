package br.com.dominio;

import br.com.dominio.strategy.ContextMath;
import br.com.dominio.strategy.operacoes.Somar;
import br.com.dominio.strategy.operacoes.Subtrair;

public class Principal {
	
	public static void main(String[] args) {
		
		ContextMath math = new ContextMath(new Somar());
		
		System.out.println(math.calcular(5, 3));
		
		math = new ContextMath(new Subtrair());
		
		System.out.println(math.calcular(5, 3));
		
	}

}
