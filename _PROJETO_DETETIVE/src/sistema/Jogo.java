package sistema;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import arquivo.Repositorio;
import entidades.Arma;
import entidades.Local;
import entidades.Suspeito;
import excecoes.DicasEsgotadasException;
import excecoes.EvidenciaInvalidaException;
import excecoes.PoucasEvidenciasException;
/**
 * Classe que cuida das regas do jogo
 * @author Samuell e Luiz
 *
 */
public class Jogo {
	private  Cenario cenarioDoCrime;
	private Jogador jogador;
	private Random sorte;
	public Repositorio repositorio;
	
//	Set<String> dicasSelecionadasArma = new HashSet<String>();
//	public Set<String> dicasSelecionadasSuspeito = new HashSet<String>();
//	Set<String> dicasSelecionadasLocais = new HashSet<String>();

	/**
	 * Construtor da classe Jogo. Recebe um Jogador(nome) como argumento
	 * @param jogador
	 * @throws IOException
	 */
		public Jogo(Jogador jogador) throws IOException {
			this.jogador = jogador;
			this.repositorio = new Repositorio();
		}

	/**
	 * M�todo que retorna o jogador 
	 * @return Jogador
	 */
	public Jogador getJogador() {
		return jogador;
	}

	/**
	 * M�todo que altera o Jogador
	 * @param jogador
	 */
	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}

	/**
	 * M�todo que gera um cen�rio o cen�rio do crime aletoriamente
	 * @throws PoucasEvidenciasException
	 * @throws EvidenciaInvalidaException
	 */
	public void geraCenario() throws PoucasEvidenciasException, EvidenciaInvalidaException {
		if (repositorio.getSuspeitos().size() <=1 )
			throw new PoucasEvidenciasException("Poucos suspeitos! Deve haver pelo menos 2 de cada");
		
		if  (repositorio.getLocais().size() <=1)
			throw new  PoucasEvidenciasException("Poucos Locais! Deve haver pelo menos 2 de cada");
		if (repositorio.getArmas().size() <=1) 
			throw new PoucasEvidenciasException("Poucas armas! Deve haver pelo menos 2 de cada");
			
		this.sorte = new Random();
		Arma armaEscolhida = repositorio.getArmas().get(this.sorte.nextInt(repositorio.getArmas().size()));
		Suspeito suspeitoEscolhido = repositorio.getSuspeitos().get(this.sorte.nextInt(repositorio.getSuspeitos().size()));
		Local localEscolhido = repositorio.getLocais().get(this.sorte.nextInt(repositorio.getLocais().size()));
		this.cenarioDoCrime = new Cenario(suspeitoEscolhido, localEscolhido,armaEscolhida);
		
	}
	
	/**
	 * M�todo que retorna o Ranking do jogo orndenado pela pontua��o
	 * @return ArrayList de Rank
	 * @throws IOException
	 */
	public ArrayList<Rank> getRankingOrdenandoPorPontuacao() throws IOException{
		String[] pedacos;
		ArrayList<Rank> arrayRank = new ArrayList<Rank>();
		repositorio.getRankingOrdenado();
		
		for(int i =0; i < repositorio.getRankingOrdenado().size(); i++){
			pedacos= repositorio.getRankingOrdenado().get(i).toString().trim().split("##");
			arrayRank.add(new Rank(pedacos[0], Integer.parseInt(pedacos[1])));
			
		}
		Collections.sort(arrayRank);
		Collections.reverse(arrayRank);
		return arrayRank;
	}

	/**
	 * M�todo que retorna o cen�rio do crime
	 * @return Cenario
	 */
	public Cenario getCenarioDoCrime() {
		return this.cenarioDoCrime;
	}

//N�o usamos
//	//verifica se uma dica j� existe dentro de uma cole��o
//	public boolean isRepetida(String dica, Set<String> lista) {
//		boolean repetida = false;
//		for (String s : lista) {
//			if (s.equals(dica))
//				repetida = true;
//		}
//		return repetida;
//	}
	
    /**
     * Retorna uma dica aleat�ria de um suspeito e sem repeti��o
     * @return String contendo uma dica
     * @throws DicasEsgotadasException
     */
	public String getDicaAleatoriaSuspeito() throws DicasEsgotadasException{
		Random random = new Random();
		String dicaGerada = null;
		int tamanho = cenarioDoCrime.getSuspeito().getDicas().size();
		
		if (tamanho == 0) throw new DicasEsgotadasException("Dicas esgotadas");
		
		int indiceRandomizado = random.nextInt(tamanho);
		dicaGerada = cenarioDoCrime.getSuspeito().getDicas().get(indiceRandomizado); 

		cenarioDoCrime.getSuspeito().getDicas().remove(indiceRandomizado);
		this.diminuirPontos();
		return dicaGerada;
	} 
	
	/**
     * Retorna uma dica aleat�ria de um Local e sem repeti��o
     * @return String contendo uma dica
     * @throws DicasEsgotadasException
	 */
	public String getDicaAleatoriaLocal() throws DicasEsgotadasException {
		Random random = new Random();
		String dicaGerada = null;
		int tamanho = cenarioDoCrime.getLocal().getDicas().size();
		
		if (tamanho == 0) throw new DicasEsgotadasException("Dicas esgotadas");
		
		int indiceRandomizado = random.nextInt(tamanho);
		dicaGerada = cenarioDoCrime.getLocal().getDicas().get(indiceRandomizado); 
		cenarioDoCrime.getLocal().getDicas().remove(indiceRandomizado);
		this.diminuirPontos();
		return dicaGerada;
	}
	
	/**
	 * Retorna uma dica aleat�ria de um Local e sem repeti��o
	 * @return String contendo uma dica
	 * @throws DicasEsgotadasException
	 */
	public String getDicaAleatoriaArma() throws DicasEsgotadasException {
		Random random = new Random();
		String dicaGerada = null;
		int tamanho = cenarioDoCrime.getArma().getDicas().size();	
		
		if (tamanho == 0)throw new DicasEsgotadasException("Dicas esgotadas!");
		
		int indiceRandomizado = random.nextInt(tamanho);
		dicaGerada = cenarioDoCrime.getArma().getDicas().get(indiceRandomizado); 
		cenarioDoCrime.getArma().getDicas().remove(indiceRandomizado);
		this.diminuirPontos();
		return dicaGerada;
	}
	
	/**
	 * Compara cen�rio do crime com um cen�rio sugerido.
	 * Retorna True se os cen�rios forem iguais.
	 * @param cenario
	 * @return Boolean
	 */
	public boolean isCenarioDoCrime(Cenario cenario){
		return this.cenarioDoCrime.equals(cenario);
	}
	
	/**
	 * Returna true se o jogador for ganhador, false se n�o
	 * @param cenario
	 * @return Boolean
	 */
	public boolean isGanhador(Cenario cenario){
		if (this.cenarioDoCrime.equals(cenario)){
			return true;
		} else return false;
	}
	

	
	/**
	 * Diminui pontua��o do jogador
	 */
	public void  diminuirPontos(){
		jogador.diminuirPontos();
	}
	
	/**
	 * Aumenta pontua��o do jogador
	 */
	public void aumentarPontos(){
		jogador.aumentarPontos();
	}
}
