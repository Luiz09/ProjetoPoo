package view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JButton;

import excecoes.NomeInvalidoException;

import sistema.Jogador;
import sistema.Jogo;
import java.awt.Toolkit;
/**
 * Classe que contem a Tela Inicial do Jogo "Quem Matou Dona MAria?"
 * @author Samuell
 *
 */
@SuppressWarnings("serial")
public class TelaInicial extends JFrame {

	JButton btnContinuar;
	private JTextField campoNomeJogador;
	JButton btnSair;
	private JLabel lblLuizE;

	/**
	 * COnstrutor da classe TelaInicial
	 */
	public TelaInicial() {
		setTitle("Bem Vindo!");
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				TelaInicial.class.getResource("/view/icone.png")));
		setResizable(false);
		getContentPane().setLayout(null);
		setSize(484, 465);

		campoNomeJogador = new JTextField();
		campoNomeJogador.setBounds(323, 332, 145, 32);
		getContentPane().add(campoNomeJogador);
		campoNomeJogador.setColumns(10);
		EscutaTecla ecCampoNome = new EscutaTecla();
		campoNomeJogador.addKeyListener(ecCampoNome);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNome.setForeground(Color.WHITE);
		lblNome.setBounds(267, 338, 46, 17);
		getContentPane().add(lblNome);

		btnSair = new JButton("Sair");
		btnSair.setBounds(267, 375, 89, 32);
		getContentPane().add(btnSair);
		BotoesOuvintes boSair = new BotoesOuvintes();
		btnSair.addActionListener(boSair);

		btnContinuar = new JButton("Continuar");
		btnContinuar.setBounds(366, 375, 102, 32);
		getContentPane().add(btnContinuar);
		btnContinuar.setEnabled(false);
		BotoesOuvintes boContinuar = new BotoesOuvintes();
		btnContinuar.addActionListener(boContinuar);

		lblLuizE = new JLabel("\u00A9 Luiz e Samuell   Vers\u00E3o: 1.0");
		lblLuizE.setForeground(Color.WHITE);
		lblLuizE.setBounds(298, 418, 170, 14);
		getContentPane().add(lblLuizE);

		JLabel lblImagemDeFundo = new JLabel("");
		lblImagemDeFundo.setIcon(new ImageIcon(TelaInicial.class
				.getResource("/view/quem_matou.png")));
		lblImagemDeFundo.setBounds(0, 0, 484, 440);
		getContentPane().add(lblImagemDeFundo);

	}

	/**
	 * Classe interna que gerencia os eventos partidos dos botões da janela TelaInicial
	 * @author Samuell
	 *
	 */
	private class BotoesOuvintes implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent evento) {
			if (evento.getSource().equals(btnContinuar)) {
				try {
					JanelaPrincipal.jogador = new Jogador(
							campoNomeJogador.getText());
					JanelaPrincipal frame = new JanelaPrincipal();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);

					JanelaPrincipal.ti.setVisible(false);
					JanelaPrincipal.jogo = new Jogo(JanelaPrincipal.jogador);
					JanelaPrincipal.listaSuspeitos
							.setListData(JanelaPrincipal.jogo.repositorio
									.getSuspeitos().toArray());
					JanelaPrincipal.listaArmas
							.setListData(JanelaPrincipal.jogo.repositorio
									.getArmas().toArray());
					JanelaPrincipal.listaLocais
							.setListData(JanelaPrincipal.jogo.repositorio
									.getLocais().toArray());
				} catch (NomeInvalidoException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
					// TODO Auto-generated catch block
					// e1.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			if (evento.getSource().equals(btnSair)) {
				System.exit(ABORT);
			}

		}

	}

	/**
	 * Classe interna que gerencia os eventos da ela inicial
	 * 
	 * @author Samuell
	 * 
	 */
	private class EscutaTecla extends KeyAdapter implements KeyListener {

		/**
		 * Método assinado na interface implements que verifica se as teclas
		 * estão sendo pressionadas
		 */
		public void keyPressed(KeyEvent e) {
			if (e.getSource().equals(campoNomeJogador)) {
				/**
				 * Acende o botão de continuar caso seja digitado algum texto
				 * nele
				 */
				if (!campoNomeJogador.getText().isEmpty()) {
					btnContinuar.setEnabled(true);
				} else {
					btnContinuar.setEnabled(false);
				}

			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub

		}
	}

}
