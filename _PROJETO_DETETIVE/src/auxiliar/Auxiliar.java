package auxiliar;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Classe que contem métodos auxiliares utilizados no jogo
 * 
 * @author Samuell e Luiz
 * 
 */
public class Auxiliar {

	/**
	 * Método estático utilizado para embaralhar Strings
	 * 
	 * @param s
	 * @return String
	 */
	public static String embaralha(String s) {
		char[] caracteres = s.toCharArray();
		ArrayList<Character> listaDeCaracteres = new ArrayList<Character>();

		for (int i = 0; i < caracteres.length; i++) {
			listaDeCaracteres.add(caracteres[i]);
		}

		Collections.shuffle(listaDeCaracteres);

		StringBuilder stringFinal = new StringBuilder();

		for (int i = 0; i < listaDeCaracteres.size(); i++) {
			stringFinal.append(listaDeCaracteres.get(i));
		}

		return stringFinal.toString();
	}

}
