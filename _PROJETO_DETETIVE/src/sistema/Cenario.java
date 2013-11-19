package sistema;

import java.text.SimpleDateFormat;
import java.util.Date;

import entidades.Arma;
import entidades.Local;
import entidades.Suspeito;
import excecoes.EvidenciaInvalidaException;

/**
 * Classe que contem o cenário
 * 
 * @author Samuell e Luiz
 * 
 */
public class Cenario {
	private Suspeito suspeito;
	private Local local;
	private Arma arma;
	private final String DATA;

	/**
	 * Um cenário só pode ser construido se receber um suspeito, um local e uma
	 * arma como argumentos. O cenário também catura a hora em que ele foi
	 * criado.
	 * 
	 * @param suspeito
	 * @param local
	 * @param arma
	 * @throws EvidenciaInvalidaException
	 */
	public Cenario(Suspeito suspeito, Local local, Arma arma)
			throws EvidenciaInvalidaException {
		SimpleDateFormat formatoDeData = new SimpleDateFormat(
				"dd/MM/yyyy - k:m");
		this.DATA = formatoDeData.format(new Date());
		setSuspeito(suspeito);
		setLocal(local);
		setArma(arma);
	}

	/**
	 * Retorna suspeito
	 * 
	 * @return Suspeito
	 */
	public Suspeito getSuspeito() {
		return this.suspeito;
	}

	/**
	 * Modifica suspeito.
	 * 
	 * @param suspeito
	 * @throws EvidenciaInvalidaException
	 *             se o suspeito for nulo.
	 */
	public void setSuspeito(Suspeito suspeito)
			throws EvidenciaInvalidaException {
		if (suspeito == null)
			throw new EvidenciaInvalidaException(
					"Evidência nula! Impossível criar cenário.");
		this.suspeito = suspeito;
	}

	/**
	 * Retorna local
	 * 
	 * @return Local
	 */
	public Local getLocal() {
		return local;
	}

	/**
	 * Modifica local
	 * 
	 * @param local
	 * @throws EvidenciaInvalidaException
	 *             se local for nulo
	 */
	public void setLocal(Local local) throws EvidenciaInvalidaException {
		if (local == null)
			throw new EvidenciaInvalidaException(
					"Evidência nula! Impossível criar cenário.");
		this.local = local;
	}

	/**
	 * Retorna arma
	 * 
	 * @return Arma
	 */
	public Arma getArma() {
		return arma;
	}

	/**
	 * Modifica arma
	 * 
	 * @param arma
	 * @throws EvidenciaInvalidaException
	 *             se arma for nula
	 */
	public void setArma(Arma arma) throws EvidenciaInvalidaException {
		if (arma == null)
			throw new EvidenciaInvalidaException(
					"Evidência nula! Impossível criar cenário.");
		this.arma = arma;
	}

	/**
	 * Retorna data gerada na hora em que o objeto foi criado. Este valor é
	 * definitivo para cada cenário gerado.
	 * 
	 * @return String com data formatada
	 */
	public String getData() {
		return this.DATA;
	}

	/**
	 * Hash code reescrito. Utilizado para a comparação de um objeto do tipo
	 * cenário com outro objeto cenário.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arma == null) ? 0 : arma.hashCode());
		result = prime * result + ((local == null) ? 0 : local.hashCode());
		result = prime * result
				+ ((suspeito == null) ? 0 : suspeito.hashCode());
		return result;
	}

	@Override
	/**
	 * Hash code reescrito. Utilizado para a comparação de um objeto 
	 * do tipo cenário com outro objeto cenário.
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cenario other = (Cenario) obj;
		if (arma == null) {
			if (other.arma != null)
				return false;
		} else if (!arma.equals(other.arma))
			return false;
		if (local == null) {
			if (other.local != null)
				return false;
		} else if (!local.equals(other.local))
			return false;
		if (suspeito == null) {
			if (other.suspeito != null)
				return false;
		} else if (!suspeito.equals(other.suspeito))
			return false;
		return true;
	}

	/**
	 * toString. Retorna uma representação em strings do objeto
	 * 
	 * @return String
	 */
	public String toString() {
		return String.format("%s - %s - %s", this.suspeito.getNome(),
				this.local.getNome(), this.arma.getNome());

	}

}
