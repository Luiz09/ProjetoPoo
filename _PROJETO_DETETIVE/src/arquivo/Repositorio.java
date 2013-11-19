package arquivo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import sistema.Jogador;

import entidades.Arma;
import entidades.Local;

import entidades.Suspeito;
/**
 * 
 * @author Samuell e Luiz
 *
 */
public class Repositorio {
	private File arquivoArmas, arquivoLocais, arquivoSuspeitos;
	List<Jogador> jogador = new ArrayList<Jogador>();
	List<Arma> armas = new ArrayList<Arma>();
	List<Local> locais = new ArrayList<Local>();
	List<Suspeito> suspeitos = new ArrayList<Suspeito>();
	
	private BufferedWriter bw;
	private BufferedReader br;
	private File arquivoRanking;
	
	/**
	 * Construtor de Repositório. Chama o método 
	 * createFiles() para criar os arquivos
	 * @throws IOException
	 */
	public Repositorio() throws IOException {
	createFiles();
	}
	

	/**
	 * Método que cria arquivos
	 * @throws IOException
	 */
	private void createFiles() throws IOException {
		this.arquivoArmas = new File("armas.bin");
		this.arquivoLocais = new File("locais.bin");
		this.arquivoSuspeitos = new File("suspeitos.bin");
		this.arquivoRanking = new File("ranking.txt");
		
		if(!this.arquivoArmas.exists()){
			this.arquivoArmas.createNewFile();
		}
		if(!this.arquivoLocais.exists()){
			this.arquivoLocais.createNewFile();
		}
		if(!this.arquivoSuspeitos.exists()){
			this.arquivoSuspeitos.createNewFile();
		}
		
		if(!this.arquivoRanking.exists()){
			this.arquivoRanking.createNewFile();
		}
	}

	/**
	 * Método que adiciona armas no arquivo
	 * @param nova
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void addArmas(Arma nova) throws FileNotFoundException, IOException, ClassNotFoundException {
		List<Arma> armas = getArmas(); // obter arma que está no arquivo
		if(armas==null){
			armas = new ArrayList<Arma>();	
		}
		armas.add(nova); // adicionar arma no ArrayList
		Collections.sort(armas);
		// persistir ou gravar no Arraylist
		ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(arquivoArmas));
		os.writeObject(armas);
		os.close();

	}
	
	/**
	 * Método que adiciona locais no  arquivo
	 * @param novo
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void addLocal(Local novo) throws FileNotFoundException, IOException {
		ArrayList<Local> locais = (ArrayList<Local>) getLocais(); // obter local que está no arquivo
		if(locais ==null){
			locais = new ArrayList<Local>();	
		}
		
		// adicionar local
		locais.add(novo); 
		Collections.sort(locais);
		// persistir ou gravar no Arraylist
		ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(arquivoLocais));
		os.writeObject(locais);
		os.close();
	}
	
	/**
	 * Método que adiciona Suspeitos no arquivo
	 * @param novo
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void addSuspeito(Suspeito novo) throws FileNotFoundException, IOException {
		ArrayList<Suspeito> suspeitos = (ArrayList<Suspeito>) getSuspeitos(); // obter local que está no arquivo
		if(suspeitos ==null){
			suspeitos = new ArrayList<Suspeito>();	
		}
		// adicionar local
		suspeitos.add(novo); 
		Collections.sort(suspeitos); //ordena lista
		// persistir ou gravar no Arraylist
		ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(arquivoSuspeitos));
		os.writeObject(suspeitos);
		os.close();
	}
	/**
	 * Método que deleta suspeitos do arquivo
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void deletarSuspeitos() throws FileNotFoundException, IOException{
		ArrayList<Suspeito> suspeitos = (ArrayList<Suspeito>) getSuspeitos(); // obter local que está no arquivo
		suspeitos.removeAll(getSuspeitos());
		// adicionar local 
		// persistir ou gravar no Arraylist
		ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(arquivoSuspeitos));
		os.writeObject(suspeitos);
		os.close();
	}
	
	/**
	 * Método que deleta locais do arquivo
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void deletarLocais() throws FileNotFoundException, IOException{
		ArrayList<Local> locais = (ArrayList<Local>) getLocais(); // obter local que está no arquivo
		locais.removeAll(getLocais());
		// adicionar local 
		// persistir ou gravar no Arraylist
		ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(arquivoLocais));
		os.writeObject(locais);
		os.close();
	}
	
	/**
	 * Método que deleta armas do arquivo
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void deletarArmas() throws FileNotFoundException, IOException{
		ArrayList<Arma> armas = (ArrayList<Arma>) getArmas(); // obter local que está no arquivo
		armas.removeAll(getArmas());
		// adicionar local 
		// persistir ou gravar no Arraylist
		ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(arquivoArmas));
		os.writeObject(armas);
		os.close();
	}
	
	/**
	 * Método que deleta todas suspeitos,
	 * locais e arms do arquivo 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void limparArquivo() throws FileNotFoundException, IOException{
		this.deletarSuspeitos();
		this.deletarLocais();
		this.deletarArmas();
	}

	/**
	 * Método que retorna armas do arquivo
	 * @return List de Armas
	 */
	@SuppressWarnings("unchecked")
	public List<Arma> getArmas() {
		List<Arma> armas = null;
			try{
				ObjectInputStream is = new ObjectInputStream(new FileInputStream(this.arquivoArmas));				
				armas = (List<Arma>) is.readObject(); // ler objeto
			} catch (IOException e) {
			} catch (ClassNotFoundException e){
			}
			return armas;
		}

	/**
	 * Método que retorna Locais do arquivo
	 * @return List de Locais
	 */
	@SuppressWarnings("unchecked")
	public List<Local> getLocais() {
		List<Local> locais = null;
		try{
			ObjectInputStream is = new ObjectInputStream(new FileInputStream(this.arquivoLocais));				
			locais = (List<Local>) is.readObject(); // ler objeto
		} catch (IOException e) {
		} catch (ClassNotFoundException e){
		}
		return locais;
	} 
	/**
	 * Método que retorna os suspeitos do arquivo
	 * @return List de Suspeitos
	 */
	@SuppressWarnings("unchecked")
	
	public List<Suspeito> getSuspeitos() {
		List<Suspeito> suspeitos= null;
		try{
			ObjectInputStream is = new ObjectInputStream(new FileInputStream(this.arquivoSuspeitos));				
			suspeitos = (List<Suspeito>) is.readObject(); // ler objeto
		} catch (IOException e) {
		} catch (ClassNotFoundException e){
		}
		
		Collections.sort(suspeitos);
		return suspeitos;
	}
	
	/**
	 * Método que abre para aleitura o arquivo de texto
	 * onde está armazenado o Ranking 
	 * @throws IOException
	 */
	public void abrirRanking() throws IOException {
		this.bw = new BufferedWriter(new PrintWriter(new FileWriter(arquivoRanking, true), true));
		this.br = new BufferedReader(new FileReader(arquivoRanking));	
	
	}

	//public void fecharRanking() throws IOException {
//	bw.close();  método comentado
//	br.close();
//	}

	/**
	 * Método que escreve no arquivo de texto que contem
	 * o ranking
	 * @param linha
	 * @throws IOException
	 */
	public void escreverRanking(String linha) throws IOException {
		this.abrirRanking();
		bw.write(linha);
		bw.newLine();
		bw.flush(); // garante a gravação dos dados
					//this.fecharRanking();
		bw.close();
	}

	/**
	 * Método que Retorna o ranking armazenado no arquivo de texto
	 * @return String contendo tadas as linhas do arquivo de texxto
	 * @throws IOException
	 */
	public String lerRanking() throws IOException { // método responsável por ler Ranking
		this.abrirRanking();
		StringBuffer sb = new StringBuffer(); 
		String linha = this.br.readLine();
		while (linha != null) {  
			sb.append(linha + "\n");
			linha = this.br.readLine();
		}
		br.close();
		return sb.toString();
	}
	
	/**
	 * Método que retorna o ranking ordenado por ordem alfabética
	 * @return ArrayList de Strings
	 * @throws IOException
	 */
	public ArrayList<String> getRankingOrdenado() throws IOException{
		
		ArrayList<String> lista = new ArrayList<String>();
		this.abrirRanking();
		String linha = this.br.readLine();
		lista.add(linha);
		while (linha != null) {  
	//	 lista.addsb.append(linha).append("\n");
			linha = this.br.readLine();
			lista.add(linha);
		}
		lista.remove(lista.size() -1);
		Collections.sort(lista);
		return lista;
	
	}
       
}	



