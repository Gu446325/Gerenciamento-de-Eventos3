package teste.util.print;

import java.math.BigDecimal;

public class Print {

	public static void value(String titulo, String valor) {
		System.out.println(titulo.toUpperCase() + ": " + valor);
	}

	public static void value(String titulo, BigDecimal valor) {
		System.out.println(titulo.toUpperCase() + ": " + valor);
	}

	public static void value(String titulo, Integer valor) {
		System.out.println(titulo.toUpperCase() + ": " + valor);
	}

	public static void value(String titulo, Long valor) {
		System.out.println(titulo.toUpperCase() + ": " + valor);
	}

	public static void value(String titulo, Boolean valor) {
		System.out.println(titulo.toUpperCase() + ": " + valor);
	}

}
