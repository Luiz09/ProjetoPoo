package sistema;

/**
 * M�todo que contem um objeto do tipo Jogador.
 * @author Samuell e Luiz
 *
 */
public class Rank implements Comparable<Rank> {
	private String nome;
	private int pontos;

	public Rank(String nome, int pontos) {
		/**
		 * COnstrutor da classe Rank
		 */
		this.nome = nome;
		this.pontos = pontos;
	}

	/**
	 * Obtem o nome do jogador do ranking
	 * @return String
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Altera nome do jogador que comp�e o rank
	 * @param nome
	 */
	@SuppressWarnings("unused")
	private void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Retorna os pontos do jogador que comp�e o ranking
	 * @return int
	 */
	public int getPontos() {
		return pontos;
	}

	/**
	 * Altera os pontos
	 * @param pontos
	 */
	@SuppressWarnings("unused")
	private void setPontos(int pontos) {
		this.pontos = pontos;
	}

	/**
	 * Retorna uma representa��o em string do rank
	 */
	public String toString() {
		return this.pontos + ". " + this.nome;
	}

	/**
	 * Crit�rio de compara��o para saber se um objeto que est� no ranking � maior que o outro
	 */
	public int compareTo(Rank outro) {
		if (this.pontos > outro.getPontos()) {
			return 1;
		} else if (this.pontos < outro.getPontos()) {
			return -1;
		} else
			return this.nome.compareTo(outro.getNome());

	}
}
