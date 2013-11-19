package excecoes;

/**
 * Classe que contem a exceção que indica se um objeto Evidência é ínválido
 * @author Samuell
 *
 */
@SuppressWarnings("serial")
public class EvidenciaInvalidaException extends Exception {
	/**
	 * Construtor da classe {@link EvidenciaInvalidaException}
	 * @param msg
	 */
	public EvidenciaInvalidaException(String msg){
		super(msg);
	}

}
