package br.com.dominio.strategy.operacoes;

import br.com.dominio.strategy.StrategyMath;

public class Subtrair implements StrategyMath{
	
	@Override
	public int operacao(int numero1, int numero2) {
	
		return numero1 - numero2;
	
	}

}
