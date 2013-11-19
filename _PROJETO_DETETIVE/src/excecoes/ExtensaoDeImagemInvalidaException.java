package excecoes;
/**
 * Classe que contém a axecção que idica se a extenção de uma imagem é inválida
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
