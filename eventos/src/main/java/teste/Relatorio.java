package teste;

import javax.inject.Inject;

import br.com.trisoft.eventos.controller.AdmMB;

public class Relatorio {


	public static void main(String[] args) {

		AdmMB admMB =  new AdmMB();
		try {
			admMB.emitir();
			System.out.println("emitir");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
