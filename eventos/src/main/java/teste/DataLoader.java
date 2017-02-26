package teste;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.trisoft.eventos.model.Evento;

public class DataLoader {
	
	static List<Evento> lista = new ArrayList<>();		

	public static List<Evento> listaDeEventos(){
		Evento evento = new Evento();
		evento.setId(1L);
		evento.setNome("I Semana academica da Faculdade São Paulo");
		evento.setCusto(new BigDecimal(0));
	
		Evento evento2 = new Evento();
		evento.setId(2L);
		evento2.setNome("II Semana academica da Faculdade São Paulo");
		evento2.setCusto(new BigDecimal(0));
		
		lista.add(evento);
		lista.add(evento2);
		
		return lista;
	}

	

}
