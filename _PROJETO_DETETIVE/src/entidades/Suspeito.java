package entidades;

import java.util.ArrayList;

import excecoes.ExtensaoDeImagemInvalidaException;
import excecoes.NomeInvalidoException;
import excecoes.ProfissaoInvalidaException;

import auxiliar.Auxiliar;

/**
 * Classe que contem um Suspeito. Herda de Evidencia.
 * 
 * @author Samuell
 * 
 */
public class Suspeito extends Evidencia {

	private static final long serialVersionUID = -6805832240575744135L;
	private String profissao;

	/**
	 * Construtor da classe Suspeito.
	 * 
	 * @param nome
	 * @param imagem
	 * @param profissao
	 * @throws NomeInvalidoException
	 * @throws ExtensaoDeImagemInvalidaException
	 * @throws ProfissaoInvalidaException
	 */
	public Suspeito(String nome, String imagem, String profissao)
			throws NomeInvalidoException, ExtensaoDeImagemInvalidaException,
			ProfissaoInvalidaException {
		super(nome, imagem);
		this.setProfissao(profissao);
		this.setDicas(geraDicas());
	}

	/**
	 * Obtem profiss�o sdo Suspeiro
	 * 
	 * @return String
	 */
	public String getProfissao() {
		return this.profissao;
	}

	/**
	 * Altera a profiss�o do suspeito
	 * 
	 * @param profissao
	 * @throws ProfissaoInvalidaException
	 *             se a profiss�o for nula ou vazia
	 */
	public void setProfissao(String profissao)
			throws ProfissaoInvalidaException {
		if (profissao == null || profissao.equals(""))
			throw new ProfissaoInvalidaException(
					"Nome da profiss�o muito curto!");
		this.profissao = profissao;
	}

	/**
	 * M�todo que gera uma ArrayList de Strings contendo dicas padronizadas de
	 * acordo com os crit�rios do programador e levando em conta as
	 * caracteristicas dos atributos de Suspeito
	 * 
	 * @return ArrayList de strings
	 */
	public ArrayList<String> geraDicas() {
		ArrayList<String> dicasGeradas = new ArrayList<String>();
		// Primeira letra do nome dos suspeito
		dicasGeradas.add("Primeira letra do nome: "
				+ String.valueOf(getNome().charAt(0)));
		// nome embaralhado
		dicasGeradas.add("Quantidade de caracteres do nome: "
				+ getNome().length());
		// profiss�o emblharadas
		dicasGeradas.add("Profiss�o embaralhada: "
				+ Auxiliar.embaralha(this.getProfissao()));

		return dicasGeradas;
	}

	/**
	 * M�todo utilizado para comparar se um objeto Suspeito � igual a outro
	 * objeto Suspeito
	 */
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((profissao == null) ? 0 : profissao.hashCode());
		return result;
	}

	/**
	 * M�todo utilizado para comparar se um objeto Suspeito � igual a outro
	 * objeto Suspeito
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Suspeito other = (Suspeito) obj;
		if (profissao == null) {
			if (other.profissao != null)
				return false;
		} else if (!profissao.equals(other.profissao))
			return false;
		return true;
	}

	/**
	 * M�todoq que retorna uma representa��o em String de um objeto Suspeito
	 */
	public String toString() {
		return String.format("%s %s", super.toString(), profissao);
	}

}
