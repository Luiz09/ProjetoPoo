package excecoes;
/**
 * Classe que cont�m a exce��o que idica se uma String contendo um nome � inv�lida
 * @author Samuell
 *
 */
@SuppressWarnings("serial")
public class NomeInvalidoException extends Exception {

	/**
	 * Construtor da classe {@link NomeInvalidoException}
	 * @param msg
	 */
	public NomeInvalidoException(String msg) {
		super(msg); 
	}

	
	
	

}
