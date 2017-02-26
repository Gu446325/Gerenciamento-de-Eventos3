package teste;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import br.com.trisoft.eventos.model.Participante;
import br.com.trisoft.eventos.service.ParticipanteService;

public class CadastrarPreviamente {

	public static void main(String[] args) {
		try {
			EntityManagerFactory emf = JPAUtil.createEntityManager().getEntityManagerFactory();
			EntityManager em = emf.createEntityManager();

			em.getTransaction().begin();
			
			// TODO salvar aqui
			Participante p = new Participante();
			p.setEmail("precarregado@gmail.com");
			p.setNome("Pre carregado");

			ParticipanteService s = new ParticipanteService();
			s.salvar(p);
			
			em.getTransaction().commit();

			em.close();

		} catch (Exception e) {
			// rollback
		}
	}
}
