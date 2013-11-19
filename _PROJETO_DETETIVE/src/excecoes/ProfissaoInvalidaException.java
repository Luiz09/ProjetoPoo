package excecoes;
/**
 * Classe que contem a exce��o que indica se uma string contendo o nome de uma profiss�o � inv�lida
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
