package br.com.dominio.venda;

public class NFE {

	private String uf;

	public NFE(String uf) {

		this.uf = uf;

	}

	public double calcularImposto() {

		if (uf.equalsIgnoreCase("MG")) {

			return 18;

		}
		else if (uf.equalsIgnoreCase("RJ")) {

			return 20;

		}

		else {

			return 10;

		}
	}

}
