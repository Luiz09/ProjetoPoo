package entidades;

import java.io.Serializable;
import java.util.ArrayList;

import excecoes.ExtensaoDeImagemInvalidaException;
import excecoes.NomeInvalidoException;

/**
 * Classe que contem a Evid�ncia do crime
 * 
 * @author Samuell e Luiz
 * 
 */
public abstract class Evidencia implements Comparable<Evidencia>, Serializable {

	private static final long serialVersionUID = -2504798606510683254L;
	private String nome;
	private String imagem;
	private ArrayList<String> dicas;

	/**
	 * Construtor de Evid�ncia
	 * 
	 * @param nome
	 * @param imagem
	 * @throws ExtensaoDeImagemInvalidaException
	 * @throws NomeInvalidoException
	 */
	
	public Evidencia(String nome, String imagem)
			throws ExtensaoDeImagemInvalidaException, NomeInvalidoException {
		setNome(nome);
		setImagem(imagem);
		this.dicas = new ArrayList<String>();
	}

	/**
	 * M�todo que retorna nome da Evid�ncia
	 * 
	 * @return String
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * M�todo que altera o nome.
	 * 
	 * @param nome
	 * @throws NomeInvalidoException
	 *             se a String for nula ou for uma String vazia
	 */
	public void setNome(String nome) throws NomeInvalidoException {
		if (nome == null || nome.equals(""))
			throw new NomeInvalidoException("Nome muito curto!");
		this.nome = nome;
	}

	/**
	 * M�todo que retorna o caminho da imagem
	 * 
	 * @return String (path)
	 */
	public String getImagem() {
		return imagem;
	}

	/**
	 * M�todo que altera o o camnho da imagem
	 * 
	 * @param imagem
	 * @throws ExtensaoDeImagemInvalidaException
	 *             se o caminho n�o terminar em "jpg", "png" ou "gif"
	 */
	public void setImagem(String imagem)
			throws ExtensaoDeImagemInvalidaException {
		if (!imagem.endsWith("jpg") && !imagem.endsWith("png")
				&& !imagem.endsWith("gif"))
			throw new ExtensaoDeImagemInvalidaException(
					"Imagem ou arquivo inv�lido!");
		this.imagem = imagem;
	}

	/**
	 * M�todo que retorna as dicas da evid�ncia
	 * 
	 * @return ArrayList de Strings
	 */
	public ArrayList<String> getDicas() {
		return this.dicas;
	}

	/**
	 * M�todo que altera o array de dicas
	 * 
	 * @param dicas
	 */
	public void setDicas(ArrayList<String> dicas) {
		this.dicas = dicas;
	}

	/**
	 * M�todo abstrato que deve ser implementado pelas classes filhas
	 * 
	 * @return ArrayList de Strings
	 */
	public abstract ArrayList<String> geraDicas();

	/**
	 * Retorna uma representa��o em String do objeto Evid�ncia
	 * 
	 * @return String
	 */
	public String toString() {
		return String.format("%s -", this.nome);
	}

	/**
	 * M�todo utilizado para definir o crit�rio de compara��o de um Objeto
	 * Evid�ncia
	 * 
	 */
	public int compareTo(Evidencia evidencia) {
		return this.nome.compareToIgnoreCase(evidencia.getNome());
	}

	/**
	 * M�todo utilizado para comparar se um objeto � igual ao outro
	 */
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((imagem == null) ? 0 : imagem.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	/**
	 * Equals: utilizado para comparar se um objeto � igual ao outro
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Evidencia other = (Evidencia) obj;
		if (imagem == null) {
			if (other.imagem != null)
				return false;
		} else if (!imagem.equals(other.imagem))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

}
