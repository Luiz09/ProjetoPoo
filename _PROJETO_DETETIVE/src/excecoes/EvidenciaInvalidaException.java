package excecoes;

/**
 * Classe que contem a exce��o que indica se um objeto Evid�ncia � �nv�lido
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
