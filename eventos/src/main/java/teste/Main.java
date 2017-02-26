package teste;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.trisoft.eventos.model.Participante;
import br.com.trisoft.eventos.model.relationship.ParticipanteEvento;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException {

		Main minhaClasse = new Main();
		minhaClasse.salvarParticipante();

	}

	private void salvarParticipante() {
		try {

			PreparedStatement ps = con().prepareStatement("INSERT INTO participante(id, email, nome, ) VALUES (?, ?, ?, ?)");

			System.out.println("sql");

			Participante p = new Participante();
			
			List<ParticipanteEvento> l = new ArrayList<>();

			for (int i = 1; i < 100; i++) {

				p.setId(1 + 20L);
				p.setNome(i + "Participante do evento");
				p.setEmail(i + ".participante@exemplo.com");
				p.setParticipanteEventoList(l);

				System.out.println(".participante@exemplo.com");
				ps.execute();
			}

			System.out.println("saiu");

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	/**
	 * Conexão com banco de dados
	 * 
	 * @return
	 * @throws SQLException
	 */
	private Connection con() throws SQLException {
		return DriverManager.getConnection("jdbc:postgresql://localhost:5432/trisoft_eventos", "postgres", "jarvis");
	}
}
