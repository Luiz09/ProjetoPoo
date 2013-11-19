package excecoes;
/**
 * Classe que contem a exce��o que diz se uma dica est� esgotada
 * @author Samuell e Luiz 
 *
 */
@SuppressWarnings("serial")
public class DicasEsgotadasException extends Exception {
	/**
	 * Construtor da classe {@link DicasEsgotadasException}
	 * @param msg
	 */
	public DicasEsgotadasException(String msg) {
		super(msg);
	}
}
