package view;

import java.awt.Dialog.ModalExclusionType;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
/**
 * Classe que contem a janela do Ranking
 * @author Samuell
 *
 */
public class JanelaRanking extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected JPanel contentPane;
	protected JList list;
	private JScrollPane scrollPane;

	/**
	 * Construtor da janela do ranking
	 */
	public JanelaRanking() {
		setResizable(false);
		setModalExclusionType(ModalExclusionType.TOOLKIT_EXCLUDE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				JanelaRanking.class.getResource("/view/Coroa.png")));
		setTitle("Ranking");

		setBounds(100, 100, 190, 365);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("Panel.background"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblRanking = new JLabel("RANKING");
		lblRanking.setForeground(SystemColor.textHighlight);
		lblRanking.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblRanking.setHorizontalTextPosition(SwingConstants.CENTER);
		lblRanking.setVerticalAlignment(SwingConstants.BOTTOM);
		lblRanking.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblRanking.setHorizontalAlignment(SwingConstants.CENTER);
		lblRanking.setIcon(new ImageIcon(JanelaRanking.class
				.getResource("/view/rank.png")));
		lblRanking.setBounds(45, 0, 95, 70);
		contentPane.add(lblRanking);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 81, 185, 257);
		contentPane.add(scrollPane);

		list = new JList();
		scrollPane.setViewportView(list);
		setVisible(true);
	}
}
