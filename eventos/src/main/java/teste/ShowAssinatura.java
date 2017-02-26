package teste;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import br.com.trisoft.eventos.model.Instituicao;

public class ShowAssinatura {

	public static void main(String[] args) {
		try {

			EntityManagerFactory emf = JPAUtil.createEntityManager().getEntityManagerFactory();
			EntityManager manager = emf.createEntityManager();

			Instituicao instituicao = manager.find(Instituicao.class, 8L);

			if (instituicao.getAssinatura() != null) {
			
				BufferedImage img;
				img = ImageIO.read(new ByteArrayInputStream(instituicao.getAssinatura()));
				JOptionPane.showMessageDialog(null, new JLabel(new ImageIcon(img)));

			} else {
				System.out.println("Carro não possui foto.");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
