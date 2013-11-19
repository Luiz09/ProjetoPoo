package excecoes;
/**
 * Classe que contem a exceção que indica se uma string contendo o nome de uma profissão é inválida
 * @author Samuell
 *
 */
@SuppressWarnings("serial")
public class ProfissaoInvalidaException extends Exception {
	/**
	 * Construtor da classe {@link ProfissaoInvalidaException}
	 * @param msg
	 */
	public ProfissaoInvalidaException(String msg) {
		super(msg);
	}
}
