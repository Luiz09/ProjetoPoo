package excecoes;
/**
 * Classe que cont�m a axec��o que idica se a exten��o de uma imagem � inv�lida
 * @author Samuell
 *
 */
@SuppressWarnings("serial")
public class ExtensaoDeImagemInvalidaException extends Exception {
	/**
	 * Construtor da Classe {@link ExtensaoDeImagemInvalidaException}
	 * @param msg
	 */
	public  ExtensaoDeImagemInvalidaException (String msg){
		super(msg);
	}
}
