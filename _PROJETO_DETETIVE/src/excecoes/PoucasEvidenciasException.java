package excecoes;

/**
 * Classe que cont�m a exce��o que indica se existem poucas evid�ncias para que um jogo seja 
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
