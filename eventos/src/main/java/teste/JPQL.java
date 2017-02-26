
package teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import br.com.trisoft.eventos.model.Evento;

public class JPQL {

	public static void main(String[] args) {
		EntityManagerFactory emf = JPAUtil.createEntityManager().getEntityManagerFactory();

		EntityManager em = emf.createEntityManager();

//		List<String> listaAtividade = em.createQuery("select a.nome from Atividade a where evento = 1 ", String.class)
//				.getResultList();

//		List<String> participantesDoEvento = em
//				.createQuery("select p.id.participante.nome from ParticipanteEvento p order by p.id.participante.nome",
//						String.class)
//				.getResultList();

//		List<String> participantesGerais = em
//				.createQuery("select p.nome from Participante p where upper(p.nome) like :nome and upper(p.email) like :email order by p.nome",
//						String.class)
//				.setParameter("nome", "")
//				.setParameter("email", "%puni%")
//				.getResultList();

//		Long count = em.createQuery("select count(p) from Participante p where upper(p.nome) like :nome", Long.class)
//				.setParameter("nome", "%LUI%").getSingleResult();
		
//		Long countEvento = em.createQuery("select count(usuario_id) from UsuarioInstituicao",Long.class).getSingleResult();
//		System.out.println("#################################################\n\n\n:"+countEvento);
		
		
//		Long id = em.createQuery("select u.id.instituicao.id from UsuarioInstituicao u where usuario_id = :codigo", Long.class)
//				.setParameter("codigo", 24L).getSingleResult();
//		System.out.println("ID: " + id);
		
//		Long contaAbertos = em.createQuery("select count(e) from Evento e where e.aberto = true", Long.class).getSingleResult();
//		System.out.println("ABERTOS ->"+contaAbertos);
		
//		Evento e = em.createQuery("select e from Evento e where instituicao_id = :codigoUsuario", Evento.class)
//				.setParameter("codigoUsuario", 2).getSingleResult();
		
		Long codEvento = 2L;
		Long codUsuario = 1L;
		
		Evento e = em.createQuery(
				"select e from Evento e where id = :codEvento and instituicao_id = (select ui.id.instituicao.id from UsuarioInstituicao ui  where usuario_id = :codUsuario)",
				Evento.class).setParameter("codEvento", codEvento).setParameter("codUsuario", codUsuario).getSingleResult();
		
//		Evento e2 = em.createQuery(
//				"select e from Evento e where instituicao_id = UsuarioInstituicao.instituicao_id and UsuarioInstituicao.usuario_id = :codigoUsuario",
//				Evento.class).setParameter("codigoUsuario", 2).getSingleResult();
//	
		
		System.out.println("Cod.: " + e.getId());
		System.out.println("Evento: "+e.getNome());
		System.out.println("Insituição: "+ e.getInstituicao().getNome());
		System.out.println("-----------------------------------------------");
		System.out.println("Evento: "+codEvento);
		System.out.println("Usuario: "+codUsuario);
		
// select count(p) from Participante p where upper(p.nome) like :nome
		
//		for (int i = 0; i < 10; i++) {
//			System.out.println("\n#");
//		}
//		 participantesGerais.forEach(System.out::println);

//		System.out.println(
//				"AMBOS \n---------------------------------------------------------------------------------------\n#\n#\n#\n#\n#\n#\n"
//						+ count);

		em.close();
	}
	
}
