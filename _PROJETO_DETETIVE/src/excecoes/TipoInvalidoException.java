package excecoes;

/**
 * Classe que contem a axceção que indica se um tipo de arma ou local é inválido
 * @author Samuell
 *
 */
@SuppressWarnings("serial")
public class TipoInvalidoException extends Exception{
/**
 * COnstrutor da classe {@link TipoInvalidoException}
 * @param msg
 */
	public TipoInvalidoException (String msg){
		super(msg);
	}

}
