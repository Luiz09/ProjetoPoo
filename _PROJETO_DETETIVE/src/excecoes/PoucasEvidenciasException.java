package excecoes;

/**
 * Classe que contém a exceção que indica se existem poucas evidências para que um jogo seja 
 * construido
 * @author Samuell
 *
 */
@SuppressWarnings("serial")
public class PoucasEvidenciasException extends Exception {
	/**
	 * Construtor da classe {@link PoucasEvidenciasException}
	 * @param msg
	 */
	public PoucasEvidenciasException(String msg){
		super(msg);
	}

}
