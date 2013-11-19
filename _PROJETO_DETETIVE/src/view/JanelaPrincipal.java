package view;

import java.awt.Color;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import sistema.Cenario;
import sistema.Jogador;
import sistema.Jogo;
import entidades.Arma;
import entidades.Local;
import entidades.Suspeito;
import excecoes.DicasEsgotadasException;
import excecoes.EvidenciaInvalidaException;
import excecoes.ExtensaoDeImagemInvalidaException;
import excecoes.NomeInvalidoException;
import excecoes.PoucasEvidenciasException;
import excecoes.ProfissaoInvalidaException;

/**
 * Classe que contem a interface principal do jogo
 * 
 * @author Samuell e Luiz
 * @version 1.0
 * 
 */
@SuppressWarnings("serial")
public class JanelaPrincipal extends JFrame {
	private int controleDeCadastro;
	private int controleDeDicas;

	private JPanel contentPane;

	// botões e campos do cadastro
	private JTextField campoNome;
	private JTextField campoProfissao;
	private JLabel lblNome;
	private JButton btnCadSuspeito;
	private JButton btnCadLocal;
	private JButton btnCadArma;
	private JButton btnImagem;
	private JFileChooser jf; // explorador de arquivos
	private JButton btnCadastrar;
	private JRadioButton rdbtnDeFogo;
	private JRadioButton rdbtnBranca;
	private JRadioButton rdbtnAberto;
	private JRadioButton rdbtnFechado;
	private final ButtonGroup buttonGroupArmas = new ButtonGroup(); // grupo de
																	// botões
	private final ButtonGroup buttonGroupLocais = new ButtonGroup();
	private JLabel lblCadastroDeEvidencias;

	// listas e barras de rolagem das listas
	protected static JList listaSuspeitos;
	protected static JList listaLocais;
	protected static JList listaArmas;
	private static JLabel lblSuspeitos;
	private JLabel lblLocais;
	private JLabel lblArmas;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JScrollPane scrollPane_2;

	// rótulos que contem as imagens das listas
	private JLabel lblImagemDoLocal;
	private JLabel lblImagemDaArma;
	private JLabel lblImagemDoSuspeito;

	// botões de partida
	private JButton btnIniciar;
	private JButton btnDeletarEvidencias;
	private JButton btnDesistir;

	// Variáveis do Jogo
	protected static Jogo jogo;
	private Suspeito suspeitoSugerido;
	private Local localSugerido;
	private Arma armaSugerida;
	private Cenario cenarioSugerido;
	private JButton btnVerRanking;

	// botões de jogada
	private JButton btnApostar;
	private JButton btnMostrarCenarioDoCrime;
	private JLabel lblPontos;
	private JButton btnDicaDeSuspeito;
	private JButton btnDicaDeLocal;
	private JButton btnDicaDeArma;
	protected static Jogador jogador;
	protected static TelaInicial ti;

	/**
	 * método de execução da aplicação
	 */
	public static void main(String[] args) {
		ti = new TelaInicial();
		ti.setVisible(true);
		ti.setLocationRelativeTo(null);
	}

	/**
	 * Construtor da classe Janela Principal
	 */
	public JanelaPrincipal() {
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setResizable(false);
		setTitle("Detetive");
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				JanelaPrincipal.class.getResource("/view/icone.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 911, 508);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// CAMPOS
		campoNome = new JTextField();
		campoNome.setEnabled(false);
		campoNome.setToolTipText("Nome da evid\u00EAncia");
		campoNome.setBounds(65, 90, 235, 27);
		contentPane.add(campoNome);
		campoNome.setColumns(10);
		campoProfissao = new JTextField();
		campoProfissao.setToolTipText("Profiss\u00E3o do suspeito");
		campoProfissao.setEnabled(false);
		campoProfissao.setBounds(65, 121, 235, 27);
		contentPane.add(campoProfissao);
		campoProfissao.setColumns(10);

		// ROTULOS
		lblNome = new JLabel("Nome:");
		lblNome.setBounds(22, 96, 46, 14);
		contentPane.add(lblNome);

		JLabel lblProfissao = new JLabel("Prof.:");
		lblProfissao.setBounds(22, 127, 46, 14);
		contentPane.add(lblProfissao);

		lblCadastroDeEvidencias = new JLabel("CADASTRO DE EVID\u00CANCIAS");
		lblCadastroDeEvidencias.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCadastroDeEvidencias.setBounds(22, 18, 182, 14);
		contentPane.add(lblCadastroDeEvidencias);

		lblSuspeitos = new JLabel("SUSPEITOS");
		lblSuspeitos.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSuspeitos.setBounds(482, 19, 95, 14);
		contentPane.add(lblSuspeitos);

		lblLocais = new JLabel("LOCAIS");
		lblLocais.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblLocais.setBounds(616, 19, 46, 14);
		contentPane.add(lblLocais);

		lblArmas = new JLabel("ARMAS");
		lblArmas.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblArmas.setBounds(750, 19, 46, 14);
		contentPane.add(lblArmas);

		// BOTÕES
		btnCadSuspeito = new JButton("Cad. Suspeito");
		btnCadSuspeito.setToolTipText("Cadastrar suspeito");
		btnCadSuspeito.setBounds(22, 43, 124, 35);
		contentPane.add(btnCadSuspeito);
		BotoesOuvintes boSuspeito = new BotoesOuvintes();
		btnCadSuspeito.addActionListener(boSuspeito);

		btnCadLocal = new JButton("Cad. local");
		btnCadLocal.setToolTipText("Cadastrar local");
		btnCadLocal.setBounds(156, 43, 124, 35);
		contentPane.add(btnCadLocal);
		BotoesOuvintes boLocal = new BotoesOuvintes();
		btnCadLocal.addActionListener(boLocal);

		btnCadArma = new JButton("Cad. arma");
		btnCadArma.setToolTipText("Cadastrar arma");
		btnCadArma.setBounds(290, 43, 124, 35);
		contentPane.add(btnCadArma);
		BotoesOuvintes boArma = new BotoesOuvintes();
		btnCadArma.addActionListener(boArma);

		// botão de escolher a imagem
		jf = new JFileChooser();
		btnImagem = new JButton("FOTO");
		btnImagem.setToolTipText("Foto da evid\u00EAncia");
		btnImagem.setIcon(new ImageIcon(JanelaPrincipal.class
				.getResource("/view/addimg.png")));
		btnImagem.setEnabled(false);
		btnImagem.setBounds(310, 87, 105, 121);
		contentPane.add(btnImagem);
		BotoesOuvintes boImagem = new BotoesOuvintes();
		btnImagem.addActionListener(boImagem);
		// botão de cadastrar
		btnCadastrar = new JButton("CADASTRAR");
		btnCadastrar.setHorizontalAlignment(SwingConstants.LEFT);
		btnCadastrar.setIcon(new ImageIcon(JanelaPrincipal.class.getResource("/view/gravar.png")));
		btnCadastrar.setToolTipText("Cadastrar evid\u00EAncia");
		btnCadastrar.setEnabled(false);
		btnCadastrar.setBounds(180, 159, 120, 49);
		contentPane.add(btnCadastrar);
		BotoesOuvintes boCadastrar = new BotoesOuvintes();
		btnCadastrar.addActionListener(boCadastrar);

		// botão de iniciar a partida
		btnIniciar = new JButton("INICIAR!");
		btnIniciar.setForeground(SystemColor.textHighlight);
		btnIniciar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		btnIniciar
				.setToolTipText("Depois de iniciar o jogo, \nselecione evidências clique em apostar. ");
		btnIniciar.setBounds(244, 295, 139, 49);
		contentPane.add(btnIniciar);
		BotoesOuvintes boIniciar = new BotoesOuvintes();
		btnIniciar.addActionListener(boIniciar);

		// deletar arquivo de evidências
		btnDeletarEvidencias = new JButton("Deletar arquivo");
		btnDeletarEvidencias.setIcon(new ImageIcon(JanelaPrincipal.class.getResource("/view/apagar.png")));
		btnDeletarEvidencias.setToolTipText("Deletar arquivo de evid\u00EAncias");
		btnDeletarEvidencias.setBounds(173, 453, 139, 23);
		contentPane.add(btnDeletarEvidencias);
		BotoesOuvintes boDeletarEvidencias = new BotoesOuvintes();
		btnDeletarEvidencias.addActionListener(boDeletarEvidencias);

		// botão desistir (sem implementação)
		btnDesistir = new JButton("Desistir");
		btnDesistir.setToolTipText("Desistir do jogo");
		btnDesistir.setBounds(322, 453, 89, 23);
		contentPane.add(btnDesistir);
		BotoesOuvintes boDesistir = new BotoesOuvintes();
		btnDesistir.addActionListener(boDesistir);

		// RADIO BUTTONS
		rdbtnDeFogo = new JRadioButton("De fogo");
		rdbtnDeFogo.setToolTipText("Arma de fogo");
		rdbtnDeFogo.setEnabled(false);
		buttonGroupArmas.add(rdbtnDeFogo);
		rdbtnDeFogo.setBounds(22, 185, 73, 23);
		contentPane.add(rdbtnDeFogo);

		rdbtnBranca = new JRadioButton("Branca");
		rdbtnBranca.setToolTipText("Arma branca");
		rdbtnBranca.setSelected(true);
		rdbtnBranca.setEnabled(false);
		buttonGroupArmas.add(rdbtnBranca);
		rdbtnBranca.setBounds(22, 159, 73, 23);
		contentPane.add(rdbtnBranca);

		rdbtnAberto = new JRadioButton("Aberto");
		rdbtnAberto.setToolTipText("Local aberto");
		rdbtnAberto.setSelected(true);
		rdbtnAberto.setEnabled(false);
		buttonGroupLocais.add(rdbtnAberto);
		rdbtnAberto.setBounds(98, 159, 87, 23);
		contentPane.add(rdbtnAberto);

		rdbtnFechado = new JRadioButton("Fechado");
		rdbtnFechado.setToolTipText("Local fechado");
		rdbtnFechado.setEnabled(false);
		buttonGroupLocais.add(rdbtnFechado);
		rdbtnFechado.setBounds(98, 185, 87, 23);
		contentPane.add(rdbtnFechado);

		// JLISTS E BARRAS DE ROLAGEM
		scrollPane = new JScrollPane();
		scrollPane.setBounds(482, 43, 124, 205);
		contentPane.add(scrollPane);

		listaSuspeitos = new JList();
		listaSuspeitos.setToolTipText("Lista de Suspeitos");
		listaSuspeitos.setEnabled(false);
		listaSuspeitos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(listaSuspeitos);
		ListasOuvintes loListaSuspeitosOuvinte = new ListasOuvintes();
		listaSuspeitos.addMouseListener(loListaSuspeitosOuvinte);

		scrollPane_1 = new JScrollPane();
		/** Adiciona a lista de suspeitos no painel de scroll */
		scrollPane_1.setBounds(616, 44, 124, 205);
		contentPane.add(scrollPane_1);

		listaLocais = new JList();
		listaLocais.setToolTipText("Lista de Locais");
		listaLocais.setEnabled(false);
		listaLocais.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ListasOuvintes loListaLocaisOuvinte = new ListasOuvintes();
		listaLocais.addMouseListener(loListaLocaisOuvinte);
		scrollPane_1.setViewportView(listaLocais);

		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(750, 43, 124, 205);
		contentPane.add(scrollPane_2);

		listaArmas = new JList();
		listaArmas.setToolTipText("Lista de Armas");
		listaArmas.setEnabled(false);
		listaArmas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ListasOuvintes loListaArmasOuvinte = new ListasOuvintes();
		listaArmas.addMouseListener(loListaArmasOuvinte);
		scrollPane_2.setViewportView(listaArmas);

		// IMAGEM DO CANTO
		JLabel lblImagemDoCanto = new JLabel("");
		lblImagemDoCanto.setIcon(new ImageIcon(JanelaPrincipal.class
				.getResource("/view/Detetive_view.png")));
		lblImagemDoCanto.setBounds(6, 256, 212, 220);
		contentPane.add(lblImagemDoCanto);

		// SEPARDOR
		JSeparator separador = new JSeparator();
		separador.setToolTipText("Cadastro|Jogo");
		separador.setOrientation(SwingConstants.VERTICAL);
		separador.setBounds(424, 0, 2, 485);
		contentPane.add(separador);

		// RÓTULOS DINÂMICOS: mostram a imagem do suspeito, local e arma
		lblImagemDoSuspeito = new JLabel("");
		lblImagemDoSuspeito.setToolTipText("Foto do Suspeito");
		lblImagemDoSuspeito.setIcon(new ImageIcon(JanelaPrincipal.class
				.getResource("/view/question2.png")));
		lblImagemDoSuspeito.setBounds(484, 256, 120, 128);
		contentPane.add(lblImagemDoSuspeito);

		lblImagemDoLocal = new JLabel("");
		lblImagemDoLocal.setToolTipText("Foto do Local");
		lblImagemDoLocal.setIcon(new ImageIcon(JanelaPrincipal.class
				.getResource("/view/question2.png")));
		lblImagemDoLocal.setBounds(618, 256, 120, 128);
		contentPane.add(lblImagemDoLocal);

		lblImagemDaArma = new JLabel("");
		lblImagemDaArma.setIcon(new ImageIcon(JanelaPrincipal.class
				.getResource("/view/question2.png")));
		lblImagemDaArma.setToolTipText("Foto da arma");
		lblImagemDaArma.setBounds(752, 256, 120, 128);
		contentPane.add(lblImagemDaArma);

		// BOTÕES DE DICAS
		btnDicaDeSuspeito = new JButton("Dica de Suspeito");
		btnDicaDeSuspeito.setToolTipText("Dica de suspeito");
		btnDicaDeSuspeito.setEnabled(false);
		btnDicaDeSuspeito.setBounds(482, 395, 124, 23);
		contentPane.add(btnDicaDeSuspeito);
		BotoesOuvintes boDicaDeSuspeito = new BotoesOuvintes();
		btnDicaDeSuspeito.addActionListener(boDicaDeSuspeito);

		btnDicaDeLocal = new JButton("Dica de Local");
		btnDicaDeLocal.setToolTipText("Dica de local");
		btnDicaDeLocal.setEnabled(false);
		btnDicaDeLocal.setBounds(616, 395, 124, 23);
		contentPane.add(btnDicaDeLocal);
		BotoesOuvintes boDicaDeLocal = new BotoesOuvintes();
		btnDicaDeLocal.addActionListener(boDicaDeLocal);

		btnDicaDeArma = new JButton("Dica de Arma");
		btnDicaDeArma.setToolTipText("Dica de Arma");
		btnDicaDeArma.setEnabled(false);
		btnDicaDeArma.setBounds(750, 395, 124, 23);
		contentPane.add(btnDicaDeArma);
		BotoesOuvintes boDicaDeArma = new BotoesOuvintes();
		btnDicaDeArma.addActionListener(boDicaDeArma);

		// BOTÃO DE APOSTAR
		btnApostar = new JButton("APOSTAR");
		btnApostar.setToolTipText("Selecione as evid\u00EAncias e fa\u00E7a a sua aposta");
		btnApostar.setEnabled(false);
		btnApostar.setBounds(482, 429, 258, 47);
		contentPane.add(btnApostar);
		BotoesOuvintes boApostar = new BotoesOuvintes();
		btnApostar.addActionListener(boApostar);

		// BOTÃO QUE MOSTRA O RANKING
		btnVerRanking = new JButton("");
		btnVerRanking.setIcon(new ImageIcon(JanelaPrincipal.class
				.getResource("/view/Coroa.png")));
		btnVerRanking.setToolTipText("Ver Ranking");
		btnVerRanking.setBounds(819, 429, 55, 45);
		contentPane.add(btnVerRanking);
		BotoesOuvintes boVerRanking = new BotoesOuvintes();
		btnVerRanking.addActionListener(boVerRanking);

		// BOTÃO QUE MOSTRA O CENÁRIO DO CRIME
		btnMostrarCenarioDoCrime = new JButton("");
		btnMostrarCenarioDoCrime.setEnabled(false);
		btnMostrarCenarioDoCrime.setToolTipText("Mostrar cenário do crime");
		btnMostrarCenarioDoCrime.setIcon(new ImageIcon(JanelaPrincipal.class
				.getResource("/view/question.png")));
		btnMostrarCenarioDoCrime.setBounds(750, 429, 55, 45);
		contentPane.add(btnMostrarCenarioDoCrime);

		// ROTULO DINÂMICO: contem pontuação do jogador. se atualiza conforme as
		// jogadas
		lblPontos = new JLabel("100");
		lblPontos.setToolTipText("Pontua\u00E7\u00E3o do jogador");
		lblPontos.setVerticalAlignment(SwingConstants.BOTTOM);
		lblPontos.setHorizontalAlignment(SwingConstants.CENTER);
		lblPontos.setForeground(Color.RED);
		lblPontos.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPontos.setBounds(436, 429, 46, 47);
		contentPane.add(lblPontos);

		// este rótulo fica acima da pontuação
		JLabel lblRotuloDePontos = new JLabel("Pontos");
		lblRotuloDePontos.setForeground(Color.RED);
		lblRotuloDePontos.setHorizontalAlignment(SwingConstants.CENTER);
		lblRotuloDePontos.setBounds(436, 435, 46, 14);
		contentPane.add(lblRotuloDePontos);
		BotoesOuvintes boMostrarCenarioDoCrime = new BotoesOuvintes();
		btnMostrarCenarioDoCrime.addActionListener(boMostrarCenarioDoCrime);

	}// final do construtor de JanelaPrincipal

	/**
	 * Classe interna que gerencia o comportamento dos botões
	 * 
	 * @author Samuell e Luiz
	 * 
	 */
	private class BotoesOuvintes implements ActionListener {

		/**
		 * ActionPerformed é o método assinado na interface ActionListener que
		 * identifica quando um botão é clicado
		 */
		public void actionPerformed(ActionEvent evento) {

			/**
			 * BOTÃO CADASTRAR SUSPEITO
			 */
			if (evento.getSource().equals(btnCadSuspeito)) {
				setCamposDeSuspeito();
				controleDeCadastro = 1;
				/**
				 * O controleDeCadastro indica qual botão de cadastro foi
				 * clicado. Um botão de cadastro sempre será clicado, pois o
				 * jogo é sempre iniciado com os campos de cadastro desativados.
				 * Assim que um é clicado, os seus respectivos campos são
				 * ativados. A informação que este controle carrega é
				 * importante, pois indicará ao botão de cadastro geral qual
				 * objeto deverá instanciar (Suspeito, Local ou Arma) 1 (um)
				 * indica que um suspeito será instanciado
				 */
			}

			/**
			 * BOTÃO CADASTRAR LOCAL
			 */
			if (evento.getSource().equals(btnCadLocal)) {
				setCamposDeLocal();
				controleDeCadastro = 0;
				// 0 (zero) indica que um local será instanciado.

			}

			/**
			 * Botão cadastrar Arma
			 */
			if (evento.getSource().equals(btnCadArma)) {
				setCamposDeArma();
				controleDeCadastro = -1;
				// -1 (menos um) indica que uma arma será instanciada
			}

			/** BOTÃO ESCOLHER IMAGEM */
			if (evento.getSource().equals(btnImagem)) {
				jf.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				jf.showOpenDialog(null);
				try {
					btnImagem.setIcon(new ImageIcon(jf.getSelectedFile()
							.getPath()));
				} catch (NullPointerException e) {
					btnImagem.setIcon(new ImageIcon(JanelaPrincipal.class
							.getResource("/view/addimg.png")));
				}
				/** botão que abre o navegador de ficheiros do swing do Java */
			}

			/**
			 * Botão de cadastro geral
			 */
			if (evento.getSource().equals(btnCadastrar)) {
				/**
				 * é uma das rotinas mais importantes da interface ela depende
				 * da informação que o controDeCadastro armazena para saber que
				 * tipo de objeto deverá instanciar. NUNCA REMOVA O CONTROLE DE
				 * CADASTRO
				 */
				// cadastrar suspeito
				if (controleDeCadastro > 0) {
					try {
						String prof = campoProfissao.getText();
						Suspeito suspeito = new Suspeito(campoNome.getText(),
								jf.getSelectedFile().getPath(), prof);
						jogo.repositorio.addSuspeito(suspeito);
						listaSuspeitos.setListData(jogo.repositorio
								.getSuspeitos().toArray());
						JOptionPane.showMessageDialog(null,
								"Suspeito cadastrado com sucesso!");
						limpaCampos();
					} catch (NullPointerException e) {
						JOptionPane
								.showMessageDialog(null,
										"Preencha todos os campos antes de efetuar o cadastro!!");
					} catch (NomeInvalidoException e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					} catch (ProfissaoInvalidaException e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					} catch (ExtensaoDeImagemInvalidaException e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					} catch (FileNotFoundException e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					} catch (IOException e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
					// cadastrar arma
				} else if (controleDeCadastro < 0) {
					try {
						Arma arma = new Arma(campoNome.getText(), jf
								.getSelectedFile().getPath(),
								getTipoDeArmaSelecionado());
						jogo.repositorio.addArmas(arma);
						listaArmas.setListData(jogo.repositorio.getArmas()
								.toArray());
						JOptionPane.showMessageDialog(null,
								"Arma cadastrada com sucesso!");
						limpaCampos();
					} catch (NullPointerException e) {
						JOptionPane
								.showMessageDialog(null,
										"Preencha todos os campos antes de efetuar o cadastro");
					} catch (NomeInvalidoException e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					} catch (ExtensaoDeImagemInvalidaException e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					} catch (FileNotFoundException e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					} catch (ClassNotFoundException e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					} catch (IOException e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
				} else {// cadastrar local
					try {
						Local local = new Local(campoNome.getText(), jf
								.getSelectedFile().getPath(),
								getTipoDeLocalSelecionado());
						jogo.repositorio.addLocal(local);
						listaLocais.setListData(jogo.repositorio.getLocais()
								.toArray());
						JOptionPane.showMessageDialog(null,
								"Local cadastrado com sucesso!");
						limpaCampos();
					} catch (NullPointerException e) {
						JOptionPane
								.showMessageDialog(null,
										"Preencha todos os campos antes de efetuar o cadastro");
					} catch (NomeInvalidoException e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					} catch (ExtensaoDeImagemInvalidaException e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					} catch (FileNotFoundException e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					} catch (IOException e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
					}

				}

			}

			/**
			 * Botão Iniciar
			 */
			if (evento.getSource().equals(btnIniciar)) {
				/**
				 * O botão Iniciar é responsável por chamar o método que gera o
				 * cenário do crime. Sempre que for clicado, ele gerará um
				 * cenário do crime diferente. Quando ele é clicado, ele ativa a
				 * parte do jogo onde serão realizadas as jogadas. Também reseta
				 * a pontuação do jogador
				 */
				try {
					jogo.geraCenario();
					jogo.getJogador().resetPontos();
					btnApostar.setEnabled(true);
					ativarLadoAposta(true);
					btnIniciar.setText("REINICIAR");
					controleDeDicas = 0;
					listaSuspeitos.clearSelection();
					listaLocais.clearSelection();
					listaArmas.clearSelection();
					suspeitoSugerido = null;
					localSugerido = null;
					armaSugerida = null;
					/**
					 * A variável controleDeDicas é importante! Aqui ela é
					 * zerada, pois uma nova partida será iniciada e ela
					 * armazena a informação que os botões de dicas deverão
					 * saber para poderem exibir as dicas. Só podem ser exibidas
					 * 3 (três) dicas, sempre que um botão de dica é clicado,
					 * ele incrementa o controle de dicas. Se esta variável
					 * chegar a três, então as dicas são desativadas
					 */
				} catch (PoucasEvidenciasException e) {
					/**
					 * só serão gerados cenários de crimes se existirem pelo
					 * menos 2 evidências de cada cadastradas
					 */
					JOptionPane.showMessageDialog(null, e.getMessage());
				} catch (EvidenciaInvalidaException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			/**
			 * Botão de deletar evidências
			 */
			if (evento.getSource().equals(btnDeletarEvidencias)) {
				/** Este botão irá deletar o arquivo de suspeitos, locais, armas */
				int opcao = JOptionPane.showConfirmDialog(null, "Tem Certeza?");

				if (opcao == JOptionPane.YES_OPTION) {
					try {
						jogo.repositorio.limparArquivo();
						JOptionPane.showMessageDialog(null,
								"Arquivo do jogo deletado com sucesso!");
						listaArmas.setListData(jogo.repositorio.getArmas()
								.toArray());
						listaLocais.setListData(jogo.repositorio.getLocais()
								.toArray());
						listaSuspeitos.setListData(jogo.repositorio
								.getSuspeitos().toArray());
						listaArmas.clearSelection();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			/**
			 * Botão de Desistir
			 */
			if (evento.getSource().equals(btnDesistir)) {
				JOptionPane.showMessageDialog(null, "Parece que você arregou.",
						"Chateado!", JOptionPane.CLOSED_OPTION, new ImageIcon(
								"src\\view\\chateado.png"));
				System.exit(ABORT);
			}

			/**
			 * Botão Apostar
			 */
			if (evento.getSource().equals(btnApostar)) {
				/**
				 * O botão Apostar é responsável por submeter o cenário sugerido
				 * pelo jogador ao método que compara com o cenário do crime
				 * gerado pela aplicação. Se o cenário sugerido for igual ao do
				 * crime && a pontuação for maior que zero, então o jogador
				 * ganha; Se não for igual, mas a pontuação ainda for maior que
				 * zero, então o jogador continua jogando. Se chegar a zero,
				 * então o jogador perde A cada rodada de aposta, pontos são
				 * debitados do jogador
				 */

				try {
					cenarioSugerido = new Cenario(suspeitoSugerido,
							localSugerido, armaSugerida);
					// JOptionPane.showMessageDialog(null, "Sugerido: " +
					// cenarioSugerido);

					if (jogo.isGanhador(cenarioSugerido)
							&& jogo.getJogador().getPontuacao() > 0) {
						for (int i = 1; i <= 3; i++) {
							jogo.getJogador().aumentarPontos();
						}
						jogo.repositorio.escreverRanking(jogo.getJogador()
								.getNome()
								+ "##"
								+ jogo.getJogador().getPontuacao());
						btnApostar.setEnabled(false);
						ativarDicas(false);
						listaSuspeitos.setEnabled(false);
						listaLocais.setEnabled(false);
						listaArmas.setEnabled(false);
						lblPontos.setText(Integer.toString(jogo.getJogador()
								.getPontuacao()));
						JOptionPane.showMessageDialog(null,
								"Ganhou! \nPontos: "
										+ jogo.getJogador().getPontuacao());
					} else if (!jogo.isGanhador(cenarioSugerido)
							&& (jogo.getJogador().getPontuacao() > 0)) {
						jogo.getJogador().diminuirPontos();
						JOptionPane.showMessageDialog(null,
								"Continua!\nPontos: "
										+ jogo.getJogador().getPontuacao());
						lblPontos.setText(Integer.toString(jogo.getJogador()
								.getPontuacao()));
					} else if (!jogo.isGanhador(cenarioSugerido)
							&& jogo.getJogador().getPontuacao() < 5) {
						ativarDicas(false);
						btnApostar.setEnabled(false);
						JOptionPane.showMessageDialog(null,
								"KKKKK, perdeu! \n pontuação: "
										+ jogo.getJogador().getPontuacao());
					}
				} catch (EvidenciaInvalidaException e) {
					JOptionPane.showMessageDialog(null,
							"Opa! Selecione as evidêcias!");
				} catch (NullPointerException e) {
					/**
					 * Se o jogador não selecionar evidências, um
					 * nullPointException será lançado, até que ele gere uma
					 * aposta válida
					 */
					JOptionPane
							.showMessageDialog(null, "Selecione evidências!");

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}// fim do botão apostar

			/**
			 * Botão que mostra o cenário do crime
			 */
			if (evento.getSource().equals(btnMostrarCenarioDoCrime)) {
				JOptionPane.showMessageDialog(null, jogo.getCenarioDoCrime()
						+ "\nData do ocorrido: "
						+ jogo.getCenarioDoCrime().getData());
			}

			/**
			 * BOTÕES DE DICAS botão dica de suspeito
			 */
			if (evento.getSource().equals(btnDicaDeSuspeito)) {
				try {
					JOptionPane.showMessageDialog(null,
							jogo.getDicaAleatoriaSuspeito() + "\n -5 pontos!");
					lblPontos.setText(Integer.toString(jogo.getJogador()
							.getPontuacao()));
					controleDeDicas++;
					if (controleDeDicas >= 3)
						ativarDicas(false);

				} catch (DicasEsgotadasException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e.getMessage());
					// e.printStackTrace();
				}

			}

			/**
			 * Botão dica de local
			 */
			if (evento.getSource().equals(btnDicaDeLocal)) {
				try {
					JOptionPane.showMessageDialog(null,
							jogo.getDicaAleatoriaLocal() + "\n -5 pontos!");
					lblPontos.setText(Integer.toString(jogo.getJogador()
							.getPontuacao()));
					controleDeDicas++;
					if (controleDeDicas >= 3)
						ativarDicas(false);
				} catch (DicasEsgotadasException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e.getMessage());
					// e.printStackTrace();
				}

			}
			/**
			 * Botão dica de arma
			 */
			if (evento.getSource().equals(btnDicaDeArma)) {
				try {
					JOptionPane.showMessageDialog(null,
							jogo.getDicaAleatoriaArma() + "\n -5 pontos!");
					lblPontos.setText(Integer.toString(jogo.getJogador()
							.getPontuacao()));
					controleDeDicas++;
					if (controleDeDicas >= 3)
						ativarDicas(false);
				} catch (DicasEsgotadasException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e.getMessage());
					// e.printStackTrace();
				}

			}

			/**
			 * Botão Ver Ranking
			 */
			if (evento.getSource().equals(btnVerRanking)) {
				try {
					JanelaRanking jr = new JanelaRanking();
					jr.setLocationRelativeTo(null);
					jr.list.setListData(jogo.getRankingOrdenandoPorPontuacao()
							.toArray());
					// jf.setLocationRelativeTo(null);

				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}

			/**
			 * Fim do action performed
			 */

		}

		/**MÉTODOS DA CLASSE INTERNA QUE GERENCIAM O COMPORTAMENTO DOS BOTÕES
		 * Método que reseta os campos de cadastro
		 */
		private void limpaCampos() {
			campoNome.setText(null);
			campoProfissao.setText(null);
			rdbtnAberto.setSelected(true);
			rdbtnBranca.setSelected(true);
			jf.setSelectedFile(null);
			btnImagem.setIcon(new ImageIcon(JanelaPrincipal.class
					.getResource("/view/addimg.png")));
		}

		/**
		 * Serviço que ativa ou desativa os botões que estão localizados no lado
		 * em que são feitas as jogadas
		 * 
		 * @param b
		 */
		private void ativarLadoAposta(boolean b) {
			/* Serviço auxiliar para desativar o lado da aposta */
			listaSuspeitos.setEnabled(b);
			// listaSuspeitos.clearSelection();
			listaLocais.setEnabled(b);
			// listaLocais.clearSelection();
			listaArmas.setEnabled(b);
			// listaArmas.clearSelection();

			lblImagemDoSuspeito.setIcon(new ImageIcon(JanelaPrincipal.class
					.getResource("/view/question2.png")));
			lblImagemDoLocal.setIcon(new ImageIcon(JanelaPrincipal.class
					.getResource("/view/question2.png")));
			lblImagemDaArma.setIcon(new ImageIcon(JanelaPrincipal.class
					.getResource("/view/question2.png")));
			btnMostrarCenarioDoCrime.setEnabled(b);
			lblPontos.setText("100");
			jogo.getJogador().resetPontos();
			ativarDicas(b);

		}

		/**
		 * Serviço que ativa ou desativa dicas
		 * 
		 * @param b
		 */
		private void ativarDicas(boolean b) {
			/* Serviço auxiliar para desativar dicas */
			btnDicaDeArma.setEnabled(b);
			btnDicaDeSuspeito.setEnabled(b);
			btnDicaDeLocal.setEnabled(b);
		}

		/**
		 * Serviço que retorna o tipo de arma que foi selecionado no radio
		 * button
		 * 
		 * @return int
		 */
		private int getTipoDeArmaSelecionado() {
			/*
			 * Serviço auxiliar que retorna o tipo de arma selecionada de acordo
			 * com os botões de rádio
			 */
			if (rdbtnBranca.isSelected())
				return Arma.BRANCA;
			else
				return Arma.DEFOGO;
		}

		/**
		 * Serviço que retorna o tipo de local que foi selecionado no radio
		 * button
		 * 
		 * @return int
		 */
		private int getTipoDeLocalSelecionado() {
			/*
			 * Serviço auxiliar que retorna o tipo de local selecionado de
			 * acordo com os botões de rádio
			 */
			if (rdbtnAberto.isSelected())
				return Local.ABERTO;
			else
				return Local.FECHADO;
		}

		/**
		 * Serviço que ativa os campos gerais de cadastro(Nome, cadastrar e
		 * imagem)
		 */
		public void setCamposGerais() {
			/*
			 * Serviço auxiliar que ativa os campos os campos imagem, cadastrar
			 * e campo nome
			 */
			btnImagem.setEnabled(true);
			btnCadastrar.setEnabled(true);
			campoNome.setEnabled(true);

		}

		/**
		 * Serviço que ativa os campos de suspeito
		 */
		public void setCamposDeSuspeito() {
			/* Serviço auxiliar que altera os campos de suspeito */
			setCamposGerais();
			setRadioButtonsDeArma(false);
			setRadioButtonsDeLocal(false);
			campoProfissao.setEnabled(true);

			btnCadSuspeito.setEnabled(false);
			btnCadLocal.setEnabled(true);
			btnCadArma.setEnabled(true);

		}

		/**
		 * Serviço que ativa os campos de local
		 */
		public void setCamposDeLocal() {
			/* Serviço auxiliar que altera os campos de local */
			setCamposGerais();
			setRadioButtonsDeArma(false);
			setRadioButtonsDeLocal(true);

			campoProfissao.setEnabled(false);
			btnCadSuspeito.setEnabled(true);
			btnCadLocal.setEnabled(false);
			btnCadArma.setEnabled(true);

		}

		/**
		 * serviço que ativa os campos de arma
		 */
		public void setCamposDeArma() {
			/* Serviço auxiliar que altera os campos de arma */
			setCamposGerais();
			setRadioButtonsDeArma(true);
			setRadioButtonsDeLocal(false);

			campoProfissao.setEnabled(false);
			btnCadSuspeito.setEnabled(true);
			btnCadLocal.setEnabled(true);
			btnCadArma.setEnabled(false);

		}

		/**
		 * serviçi que ativa ou desativa os Radio Buttons de arma
		 * 
		 * @param b
		 */
		private void setRadioButtonsDeArma(boolean b) {
			/*
			 * Serviço auxiliar que ativa ou desativa os radio buttons de arma
			 */
			rdbtnBranca.setEnabled(b);
			rdbtnDeFogo.setEnabled(b);
		}

		/**
		 * serviço que ativa ou desativa os radio buttons de local
		 * 
		 * @param b
		 */
		private void setRadioButtonsDeLocal(boolean b) {
			/*
			 * Serviço auxiliar que ativa ou desativa os radio buttons de local
			 */
			rdbtnAberto.setEnabled(b);
			rdbtnFechado.setEnabled(b);
		}

	}// fim botões ouvintes

	/**
	 * Classe privada que gerencia os eventos das JList's
	 * 
	 * @author Samuell e Luiz
	 * 
	 */
	private class ListasOuvintes implements MouseListener {

		/**
		 * método implementado da interface MouseListener que contém condições
		 * que verificam os eventos que partem da lista de Suspeitos, Locais e
		 * armas
		 */
		public void mouseClicked(MouseEvent evento) {

			/**
			 * Condição que captura os eventos partidos da lista de suspeitos no
			 * caso, os cliques
			 */
			if (evento.getSource().equals(listaSuspeitos)) {
				try {
					lblImagemDoSuspeito
							.setIcon(new ImageIcon(jogo.repositorio
									.getSuspeitos()
									.get(listaSuspeitos.getSelectedIndex())
									.getImagem()));
					String nomeSugerido = jogo.repositorio.getSuspeitos()
							.get(listaSuspeitos.getSelectedIndex()).getNome();
					String profissaoSugerida = jogo.repositorio.getSuspeitos()
							.get(listaSuspeitos.getSelectedIndex())
							.getProfissao();
					;
					String imgSugerida = jogo.repositorio.getSuspeitos()
							.get(listaSuspeitos.getSelectedIndex()).getImagem();

					suspeitoSugerido = new Suspeito(nomeSugerido, imgSugerida,
							profissaoSugerida);
				} catch (ArrayIndexOutOfBoundsException e) {

				} catch (NomeInvalidoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExtensaoDeImagemInvalidaException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ProfissaoInvalidaException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			/**
			 * Condição que captura eventos partidos da lista de locais
			 */
			if (evento.getSource().equals(listaLocais)) {
				try {
					lblImagemDoLocal.setIcon(new ImageIcon(jogo.repositorio
							.getLocais().get(listaLocais.getSelectedIndex())
							.getImagem()));
					// mudar labels
					String nomeSugerido = jogo.repositorio.getLocais()
							.get(listaLocais.getSelectedIndex()).getNome();
					String imgSugerida = jogo.repositorio.getLocais()
							.get(listaLocais.getSelectedIndex()).getImagem();
					int tipoSugerido = jogo.repositorio.getLocais()
							.get(listaLocais.getSelectedIndex()).getTIPO();
					// criar o objeto
					localSugerido = new Local(nomeSugerido, imgSugerida,
							tipoSugerido);
				} catch (ArrayIndexOutOfBoundsException e) {

				} catch (ExtensaoDeImagemInvalidaException e) {
					e.printStackTrace();
				} catch (NomeInvalidoException e) {
					e.printStackTrace();
				}

			}

			/**
			 * Condição que captura eventos partidos da lista de armas
			 */
			if (evento.getSource().equals(listaArmas)) {
				try {
					lblImagemDaArma.setIcon(new ImageIcon(jogo.repositorio
							.getArmas().get(listaArmas.getSelectedIndex())
							.getImagem()));
					// mudar labels
					String nomeSugerido = jogo.repositorio.getArmas()
							.get(listaArmas.getSelectedIndex()).getNome();
					String imgSugerida = jogo.repositorio.getArmas()
							.get(listaArmas.getSelectedIndex()).getImagem();
					int tipoSugerido = jogo.repositorio.getArmas()
							.get(listaArmas.getSelectedIndex()).getTIPO();
					// criar o objeto

					armaSugerida = new Arma(nomeSugerido, imgSugerida,
							tipoSugerido);
				} catch (ArrayIndexOutOfBoundsException e) {
				} catch (ExtensaoDeImagemInvalidaException e) {
					e.printStackTrace();
				} catch (NomeInvalidoException e) {
					e.printStackTrace();
				}
			}

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}
}
