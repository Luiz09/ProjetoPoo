package excecoes;
/**
 * Classe que contém a exceção que idica se uma String contendo um nome é inválida
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
