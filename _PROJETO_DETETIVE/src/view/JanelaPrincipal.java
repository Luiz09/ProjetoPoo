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

	// bot�es e campos do cadastro
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
																	// bot�es
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

	// r�tulos que contem as imagens das listas
	private JLabel lblImagemDoLocal;
	private JLabel lblImagemDaArma;
	private JLabel lblImagemDoSuspeito;

	// bot�es de partida
	private JButton btnIniciar;
	private JButton btnDeletarEvidencias;
	private JButton btnDesistir;

	// Vari�veis do Jogo
	protected static Jogo jogo;
	private Suspeito suspeitoSugerido;
	private Local localSugerido;
	private Arma armaSugerida;
	private Cenario cenarioSugerido;
	private JButton btnVerRanking;

	// bot�es de jogada
	private JButton btnApostar;
	private JButton btnMostrarCenarioDoCrime;
	private JLabel lblPontos;
	private JButton btnDicaDeSuspeito;
	private JButton btnDicaDeLocal;
	private JButton btnDicaDeArma;
	protected static Jogador jogador;
	protected static TelaInicial ti;

	/**
	 * m�todo de execu��o da aplica��o
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

		// BOT�ES
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

		// bot�o de escolher a imagem
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
		// bot�o de cadastrar
		btnCadastrar = new JButton("CADASTRAR");
		btnCadastrar.setHorizontalAlignment(SwingConstants.LEFT);
		btnCadastrar.setIcon(new ImageIcon(JanelaPrincipal.class.getResource("/view/gravar.png")));
		btnCadastrar.setToolTipText("Cadastrar evid\u00EAncia");
		btnCadastrar.setEnabled(false);
		btnCadastrar.setBounds(180, 159, 120, 49);
		contentPane.add(btnCadastrar);
		BotoesOuvintes boCadastrar = new BotoesOuvintes();
		btnCadastrar.addActionListener(boCadastrar);

		// bot�o de iniciar a partida
		btnIniciar = new JButton("INICIAR!");
		btnIniciar.setForeground(SystemColor.textHighlight);
		btnIniciar.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		btnIniciar
				.setToolTipText("Depois de iniciar o jogo, \nselecione evid�ncias clique em apostar. ");
		btnIniciar.setBounds(244, 295, 139, 49);
		contentPane.add(btnIniciar);
		BotoesOuvintes boIniciar = new BotoesOuvintes();
		btnIniciar.addActionListener(boIniciar);

		// deletar arquivo de evid�ncias
		btnDeletarEvidencias = new JButton("Deletar arquivo");
		btnDeletarEvidencias.setIcon(new ImageIcon(JanelaPrincipal.class.getResource("/view/apagar.png")));
		btnDeletarEvidencias.setToolTipText("Deletar arquivo de evid\u00EAncias");
		btnDeletarEvidencias.setBounds(173, 453, 139, 23);
		contentPane.add(btnDeletarEvidencias);
		BotoesOuvintes boDeletarEvidencias = new BotoesOuvintes();
		btnDeletarEvidencias.addActionListener(boDeletarEvidencias);

		// bot�o desistir (sem implementa��o)
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

		// R�TULOS DIN�MICOS: mostram a imagem do suspeito, local e arma
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

		// BOT�ES DE DICAS
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

		// BOT�O DE APOSTAR
		btnApostar = new JButton("APOSTAR");
		btnApostar.setToolTipText("Selecione as evid\u00EAncias e fa\u00E7a a sua aposta");
		btnApostar.setEnabled(false);
		btnApostar.setBounds(482, 429, 258, 47);
		contentPane.add(btnApostar);
		BotoesOuvintes boApostar = new BotoesOuvintes();
		btnApostar.addActionListener(boApostar);

		// BOT�O QUE MOSTRA O RANKING
		btnVerRanking = new JButton("");
		btnVerRanking.setIcon(new ImageIcon(JanelaPrincipal.class
				.getResource("/view/Coroa.png")));
		btnVerRanking.setToolTipText("Ver Ranking");
		btnVerRanking.setBounds(819, 429, 55, 45);
		contentPane.add(btnVerRanking);
		BotoesOuvintes boVerRanking = new BotoesOuvintes();
		btnVerRanking.addActionListener(boVerRanking);

		// BOT�O QUE MOSTRA O CEN�RIO DO CRIME
		btnMostrarCenarioDoCrime = new JButton("");
		btnMostrarCenarioDoCrime.setEnabled(false);
		btnMostrarCenarioDoCrime.setToolTipText("Mostrar cen�rio do crime");
		btnMostrarCenarioDoCrime.setIcon(new ImageIcon(JanelaPrincipal.class
				.getResource("/view/question.png")));
		btnMostrarCenarioDoCrime.setBounds(750, 429, 55, 45);
		contentPane.add(btnMostrarCenarioDoCrime);

		// ROTULO DIN�MICO: contem pontua��o do jogador. se atualiza conforme as
		// jogadas
		lblPontos = new JLabel("100");
		lblPontos.setToolTipText("Pontua\u00E7\u00E3o do jogador");
		lblPontos.setVerticalAlignment(SwingConstants.BOTTOM);
		lblPontos.setHorizontalAlignment(SwingConstants.CENTER);
		lblPontos.setForeground(Color.RED);
		lblPontos.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPontos.setBounds(436, 429, 46, 47);
		contentPane.add(lblPontos);

		// este r�tulo fica acima da pontua��o
		JLabel lblRotuloDePontos = new JLabel("Pontos");
		lblRotuloDePontos.setForeground(Color.RED);
		lblRotuloDePontos.setHorizontalAlignment(SwingConstants.CENTER);
		lblRotuloDePontos.setBounds(436, 435, 46, 14);
		contentPane.add(lblRotuloDePontos);
		BotoesOuvintes boMostrarCenarioDoCrime = new BotoesOuvintes();
		btnMostrarCenarioDoCrime.addActionListener(boMostrarCenarioDoCrime);

	}// final do construtor de JanelaPrincipal

	/**
	 * Classe interna que gerencia o comportamento dos bot�es
	 * 
	 * @author Samuell e Luiz
	 * 
	 */
	private class BotoesOuvintes implements ActionListener {

		/**
		 * ActionPerformed � o m�todo assinado na interface ActionListener que
		 * identifica quando um bot�o � clicado
		 */
		public void actionPerformed(ActionEvent evento) {

			/**
			 * BOT�O CADASTRAR SUSPEITO
			 */
			if (evento.getSource().equals(btnCadSuspeito)) {
				setCamposDeSuspeito();
				controleDeCadastro = 1;
				/**
				 * O controleDeCadastro indica qual bot�o de cadastro foi
				 * clicado. Um bot�o de cadastro sempre ser� clicado, pois o
				 * jogo � sempre iniciado com os campos de cadastro desativados.
				 * Assim que um � clicado, os seus respectivos campos s�o
				 * ativados. A informa��o que este controle carrega �
				 * importante, pois indicar� ao bot�o de cadastro geral qual
				 * objeto dever� instanciar (Suspeito, Local ou Arma) 1 (um)
				 * indica que um suspeito ser� instanciado
				 */
			}

			/**
			 * BOT�O CADASTRAR LOCAL
			 */
			if (evento.getSource().equals(btnCadLocal)) {
				setCamposDeLocal();
				controleDeCadastro = 0;
				// 0 (zero) indica que um local ser� instanciado.

			}

			/**
			 * Bot�o cadastrar Arma
			 */
			if (evento.getSource().equals(btnCadArma)) {
				setCamposDeArma();
				controleDeCadastro = -1;
				// -1 (menos um) indica que uma arma ser� instanciada
			}

			/** BOT�O ESCOLHER IMAGEM */
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
				/** bot�o que abre o navegador de ficheiros do swing do Java */
			}

			/**
			 * Bot�o de cadastro geral
			 */
			if (evento.getSource().equals(btnCadastrar)) {
				/**
				 * � uma das rotinas mais importantes da interface ela depende
				 * da informa��o que o controDeCadastro armazena para saber que
				 * tipo de objeto dever� instanciar. NUNCA REMOVA O CONTROLE DE
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
			 * Bot�o Iniciar
			 */
			if (evento.getSource().equals(btnIniciar)) {
				/**
				 * O bot�o Iniciar � respons�vel por chamar o m�todo que gera o
				 * cen�rio do crime. Sempre que for clicado, ele gerar� um
				 * cen�rio do crime diferente. Quando ele � clicado, ele ativa a
				 * parte do jogo onde ser�o realizadas as jogadas. Tamb�m reseta
				 * a pontua��o do jogador
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
					 * A vari�vel controleDeDicas � importante! Aqui ela �
					 * zerada, pois uma nova partida ser� iniciada e ela
					 * armazena a informa��o que os bot�es de dicas dever�o
					 * saber para poderem exibir as dicas. S� podem ser exibidas
					 * 3 (tr�s) dicas, sempre que um bot�o de dica � clicado,
					 * ele incrementa o controle de dicas. Se esta vari�vel
					 * chegar a tr�s, ent�o as dicas s�o desativadas
					 */
				} catch (PoucasEvidenciasException e) {
					/**
					 * s� ser�o gerados cen�rios de crimes se existirem pelo
					 * menos 2 evid�ncias de cada cadastradas
					 */
					JOptionPane.showMessageDialog(null, e.getMessage());
				} catch (EvidenciaInvalidaException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			/**
			 * Bot�o de deletar evid�ncias
			 */
			if (evento.getSource().equals(btnDeletarEvidencias)) {
				/** Este bot�o ir� deletar o arquivo de suspeitos, locais, armas */
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
			 * Bot�o de Desistir
			 */
			if (evento.getSource().equals(btnDesistir)) {
				JOptionPane.showMessageDialog(null, "Parece que voc� arregou.",
						"Chateado!", JOptionPane.CLOSED_OPTION, new ImageIcon(
								"src\\view\\chateado.png"));
				System.exit(ABORT);
			}

			/**
			 * Bot�o Apostar
			 */
			if (evento.getSource().equals(btnApostar)) {
				/**
				 * O bot�o Apostar � respons�vel por submeter o cen�rio sugerido
				 * pelo jogador ao m�todo que compara com o cen�rio do crime
				 * gerado pela aplica��o. Se o cen�rio sugerido for igual ao do
				 * crime && a pontua��o for maior que zero, ent�o o jogador
				 * ganha; Se n�o for igual, mas a pontua��o ainda for maior que
				 * zero, ent�o o jogador continua jogando. Se chegar a zero,
				 * ent�o o jogador perde A cada rodada de aposta, pontos s�o
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
								"KKKKK, perdeu! \n pontua��o: "
										+ jogo.getJogador().getPontuacao());
					}
				} catch (EvidenciaInvalidaException e) {
					JOptionPane.showMessageDialog(null,
							"Opa! Selecione as evid�cias!");
				} catch (NullPointerException e) {
					/**
					 * Se o jogador n�o selecionar evid�ncias, um
					 * nullPointException ser� lan�ado, at� que ele gere uma
					 * aposta v�lida
					 */
					JOptionPane
							.showMessageDialog(null, "Selecione evid�ncias!");

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}// fim do bot�o apostar

			/**
			 * Bot�o que mostra o cen�rio do crime
			 */
			if (evento.getSource().equals(btnMostrarCenarioDoCrime)) {
				JOptionPane.showMessageDialog(null, jogo.getCenarioDoCrime()
						+ "\nData do ocorrido: "
						+ jogo.getCenarioDoCrime().getData());
			}

			/**
			 * BOT�ES DE DICAS bot�o dica de suspeito
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
			 * Bot�o dica de local
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
			 * Bot�o dica de arma
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
			 * Bot�o Ver Ranking
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

		/**M�TODOS DA CLASSE INTERNA QUE GERENCIAM O COMPORTAMENTO DOS BOT�ES
		 * M�todo que reseta os campos de cadastro
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
		 * Servi�o que ativa ou desativa os bot�es que est�o localizados no lado
		 * em que s�o feitas as jogadas
		 * 
		 * @param b
		 */
		private void ativarLadoAposta(boolean b) {
			/* Servi�o auxiliar para desativar o lado da aposta */
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
		 * Servi�o que ativa ou desativa dicas
		 * 
		 * @param b
		 */
		private void ativarDicas(boolean b) {
			/* Servi�o auxiliar para desativar dicas */
			btnDicaDeArma.setEnabled(b);
			btnDicaDeSuspeito.setEnabled(b);
			btnDicaDeLocal.setEnabled(b);
		}

		/**
		 * Servi�o que retorna o tipo de arma que foi selecionado no radio
		 * button
		 * 
		 * @return int
		 */
		private int getTipoDeArmaSelecionado() {
			/*
			 * Servi�o auxiliar que retorna o tipo de arma selecionada de acordo
			 * com os bot�es de r�dio
			 */
			if (rdbtnBranca.isSelected())
				return Arma.BRANCA;
			else
				return Arma.DEFOGO;
		}

		/**
		 * Servi�o que retorna o tipo de local que foi selecionado no radio
		 * button
		 * 
		 * @return int
		 */
		private int getTipoDeLocalSelecionado() {
			/*
			 * Servi�o auxiliar que retorna o tipo de local selecionado de
			 * acordo com os bot�es de r�dio
			 */
			if (rdbtnAberto.isSelected())
				return Local.ABERTO;
			else
				return Local.FECHADO;
		}

		/**
		 * Servi�o que ativa os campos gerais de cadastro(Nome, cadastrar e
		 * imagem)
		 */
		public void setCamposGerais() {
			/*
			 * Servi�o auxiliar que ativa os campos os campos imagem, cadastrar
			 * e campo nome
			 */
			btnImagem.setEnabled(true);
			btnCadastrar.setEnabled(true);
			campoNome.setEnabled(true);

		}

		/**
		 * Servi�o que ativa os campos de suspeito
		 */
		public void setCamposDeSuspeito() {
			/* Servi�o auxiliar que altera os campos de suspeito */
			setCamposGerais();
			setRadioButtonsDeArma(false);
			setRadioButtonsDeLocal(false);
			campoProfissao.setEnabled(true);

			btnCadSuspeito.setEnabled(false);
			btnCadLocal.setEnabled(true);
			btnCadArma.setEnabled(true);

		}

		/**
		 * Servi�o que ativa os campos de local
		 */
		public void setCamposDeLocal() {
			/* Servi�o auxiliar que altera os campos de local */
			setCamposGerais();
			setRadioButtonsDeArma(false);
			setRadioButtonsDeLocal(true);

			campoProfissao.setEnabled(false);
			btnCadSuspeito.setEnabled(true);
			btnCadLocal.setEnabled(false);
			btnCadArma.setEnabled(true);

		}

		/**
		 * servi�o que ativa os campos de arma
		 */
		public void setCamposDeArma() {
			/* Servi�o auxiliar que altera os campos de arma */
			setCamposGerais();
			setRadioButtonsDeArma(true);
			setRadioButtonsDeLocal(false);

			campoProfissao.setEnabled(false);
			btnCadSuspeito.setEnabled(true);
			btnCadLocal.setEnabled(true);
			btnCadArma.setEnabled(false);

		}

		/**
		 * servi�i que ativa ou desativa os Radio Buttons de arma
		 * 
		 * @param b
		 */
		private void setRadioButtonsDeArma(boolean b) {
			/*
			 * Servi�o auxiliar que ativa ou desativa os radio buttons de arma
			 */
			rdbtnBranca.setEnabled(b);
			rdbtnDeFogo.setEnabled(b);
		}

		/**
		 * servi�o que ativa ou desativa os radio buttons de local
		 * 
		 * @param b
		 */
		private void setRadioButtonsDeLocal(boolean b) {
			/*
			 * Servi�o auxiliar que ativa ou desativa os radio buttons de local
			 */
			rdbtnAberto.setEnabled(b);
			rdbtnFechado.setEnabled(b);
		}

	}// fim bot�es ouvintes

	/**
	 * Classe privada que gerencia os eventos das JList's
	 * 
	 * @author Samuell e Luiz
	 * 
	 */
	private class ListasOuvintes implements MouseListener {

		/**
		 * m�todo implementado da interface MouseListener que cont�m condi��es
		 * que verificam os eventos que partem da lista de Suspeitos, Locais e
		 * armas
		 */
		public void mouseClicked(MouseEvent evento) {

			/**
			 * Condi��o que captura os eventos partidos da lista de suspeitos no
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
			 * Condi��o que captura eventos partidos da lista de locais
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
			 * Condi��o que captura eventos partidos da lista de armas
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
