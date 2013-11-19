package entidades;

import java.util.ArrayList;

import excecoes.ExtensaoDeImagemInvalidaException;
import excecoes.NomeInvalidoException;

import auxiliar.Auxiliar;

/**
 * Classe que contem o Local. herda de Evidência.
 * @author Samuell
 *
 */
public class Local extends Evidencia {
	private static final long serialVersionUID = -4426011760352304408L;

	private final int TIPO;
	public static final int ABERTO = 1;
	public static final int FECHADO = 2;

	/**
	 * Construtor da classe local.
	 * @param nome
	 * @param imagem
	 * @param tipo
	 * @throws ExtensaoDeImagemInvalidaException
	 * @throws NomeInvalidoException
	 */
	public Local(String nome, String imagem, int tipo) throws ExtensaoDeImagemInvalidaException, NomeInvalidoException {
		super(nome, imagem);
		this.TIPO = tipo;
		this.setDicas(geraDicas());
		
	}


	/**
	 * Obtem o tipo de Local. 1 para aberto, 2 para fechado.
	 * @return int
	 */
	public int getTIPO() {
		return TIPO;
	}

	
	//@Override //gera dicas padronizadas para local
	/**
	 * Método que gera uma ArrayList de Strings contendo dicas padronizadas de
	 * acordo com os critérios do programador e levando em conta as
	 * caracteristicas dos atributos da local
	 * @return ArrayList de strings
	 */
	public ArrayList<String> geraDicas() {
		ArrayList<String> dicasGeradas = new ArrayList<String>();
		// Primeira letra do nome da arma
		dicasGeradas.add("Primeira Letra Do Nome: " + String.valueOf(getNome().charAt(0)));
		// nome embaralhado
		dicasGeradas.add("Nome embaralhado: " + Auxiliar.embaralha(getNome()));
		// tipo
		if (getTIPO() == 1) dicasGeradas.add("Tipo de local: " + "ABERTO");
		else if (getTIPO() == 2) dicasGeradas.add("Tipo de Local: " + "FECHADO");

		return dicasGeradas;

	}


	/**
	 * Método utilizado para comparar se um objeto Local é igual a outro objeto local
	 */
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + TIPO;
		return result;
	}


	/**
	 * Método utilizado para comparar se um objeto Local é igual a outro objeto local
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Local other = (Local) obj;
		if (TIPO != other.TIPO)
			return false;
		return true;
	}
	
	/**
	 * Métodoq que retorna uma representação em String de um objeto Local
	 */
	public String toString() {
		String aux = null;
		if (this.TIPO == ABERTO )
				aux = "Aberto";
		else if (this.TIPO == FECHADO)
			aux = "Fechado";
		return String.format("%s %s", super.toString(), aux);
	}


    
	
}
