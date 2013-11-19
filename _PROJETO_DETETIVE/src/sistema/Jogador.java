package sistema;

import excecoes.NomeInvalidoException;
/**
 * Classe que cont�m o jogador
 * @author Samuell
 *
 */
public class Jogador {

	private String nome;
	private int pontuacao;

	/**
	 * Construtor da classe Jogador
	 * @param nome
	 * @throws NomeInvalidoException
	 */
	public Jogador(String nome) throws NomeInvalidoException {
		this.pontuacao = 100;
		this.setNome(nome);
	}
	
	/**
	 * Obter o nome d jogador
	 * @return String
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Altera o nome do jogador. 
	 * @param nome
	 * @throws {@link NomeInvalidoException} se o nome for nulo ou vazio
	 */
	public void setNome(String nome) throws NomeInvalidoException{
		if (nome == null || nome.equals("")) throw new NomeInvalidoException("Nome inv�lido!");
		this.nome = nome;
	}

	/**
	 * Obter a pontua��o do jogador
	 * @return int
	 */
	public int getPontuacao() {
		return this.pontuacao;
	}

	/**
	 * Aumenta pontua��o do jogador em 5 pontos
	 */
	public void aumentarPontos() {
		this.pontuacao+=5;
	}

	/**
	 * diminui pontua��o do jogador em 5 pontos
	 */
	public void diminuirPontos() {
		this.pontuacao-=5;
	}
	
	/**
	 * Reseta os pontos do jogador para 100 pontos
	 */
	public void resetPontos(){
		this.pontuacao = 100;
	}

}
