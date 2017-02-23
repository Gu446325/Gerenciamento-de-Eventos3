package teste;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.trisoft.model.Jogador;
import com.trisoft.model.Posicao;

public class SalvarJogador {

	public static void main(String[] args) {
		EntityManagerFactory f = Persistence.createEntityManagerFactory("PhantomPU");
		EntityManager manager = f.createEntityManager();

		EntityTransaction t = manager.getTransaction();
		t.begin();

		Jogador j = new Jogador();
		j.setNome("RONALDINHO GAUCHO");
		j.setSalario(new BigDecimal(13000));
		j.setValorTransferenciaMaisEncargos(new BigDecimal(50000));
		j.setDuracaoContrato(2);
		j.setPosicao(Posicao.AT);
		j.setIntegrado(true);

		manager.persist(j);
		t.commit();
	}

}
