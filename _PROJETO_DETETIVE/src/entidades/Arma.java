package entidades;

import java.util.ArrayList;

import excecoes.ExtensaoDeImagemInvalidaException;
import excecoes.NomeInvalidoException;

import auxiliar.Auxiliar;

/**
 * Classe que contém a arma. Herda da classe Evidência
 * @author Samuell e Luiz
 * 
 */
public class Arma extends Evidencia {

	private static final long serialVersionUID = -6374446100583084324L;
	public static final int BRANCA = 1;
	public static final int DEFOGO = 2;

	private final int TIPO;

	/**
	 * Construtor da classe Arma
	 * 
	 * @param nome
	 * @param imagem
	 * @param tipo
	 * @throws ExtensaoDeImagemInvalidaException
	 * @throws NomeInvalidoException
	 */
	public Arma(String nome, String imagem, int tipo)
			throws ExtensaoDeImagemInvalidaException, NomeInvalidoException {
		super(nome, imagem);
		this.TIPO = tipo;
		this.setDicas(geraDicas());
	}

	/**
	 * Obtem o tipo da arma. Retorna 1 para arma branca, 2 para arma de fogo
	 * 
	 * @return int
	 */
	public int getTIPO() {
		return TIPO;
	}

	/**
	 * Método que gera uma ArrayList de Strings contendo dicas padronizadas de
	 * acordo com os critérios do programador e levando em conta as
	 * caracteristicas dos atributos da Arma
	 */
	public ArrayList<String> geraDicas() {
		ArrayList<String> dicasGeradas = new ArrayList<String>();
		// Primeira letra do nome da arma
		dicasGeradas.add("Primeira Letra Do Nome: "
				+ String.valueOf(getNome().charAt(0)));
		// tipo
		if (this.getTIPO() == BRANCA)
			dicasGeradas.add("Tipo de arma: " + "BRANCA");
		else if (this.getTIPO() == DEFOGO)
			dicasGeradas.add("Tipo de arma: " + "DE FOGO");
		// nome embaralhado
		dicasGeradas.add("Nome embaralhado: " + Auxiliar.embaralha(getNome()));

		return dicasGeradas;
	}

	/**
	 * toString: retorna uma representação em String do Objeto Arma
	 */
	public String toString() {
		String aux = null;
		if (this.TIPO == BRANCA)
			aux = "Branca";
		else if (this.TIPO == DEFOGO)
			aux = "De fogo";
		return String.format("%s %s", super.toString(), aux);
	}

	/**
	 * Método utilizado para comparar se um objeto Arma é igual a outro objeto
	 * arma
	 */
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + TIPO;
		return result;
	}

	/**
	 * Método utilizado para comparar se um objeto Arma é igual a outro objeto
	 * arma
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Arma other = (Arma) obj;
		if (TIPO != other.TIPO)
			return false;
		return true;
	}

}
