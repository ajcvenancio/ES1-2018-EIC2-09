package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * This java file contains class that constructs the New Email
 *              Windows of the APP. JFrame is extended.
 * @author ajcvo-iscteiul
 * @category Graphical User Interface (GUI)
 * @since 2018
 * @version 1.0
 */
public class NewEmailWindows extends JFrame {

	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Constructor of the NewEmailWindows that receives an E-mail adress as a
	 * parameter.
	 * 
	 * @author ajcvo-iscteiul
	 * @since 2018
	 * @param String origem
	 */

	public NewEmailWindows(String origem) {
		init("Resposta ao E-mail", 700, 700);
		getContentPane().setLayout(new BorderLayout(0, 20));

		JPanel panelUp = new JPanel();
		getContentPane().add(panelUp, BorderLayout.NORTH);
		panelUp.setLayout(new GridLayout(2, 2, -420, 5));

		JLabel lblEndereoDestino = new JLabel("  Endere\u00E7o Destino:");
		lblEndereoDestino.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		panelUp.add(lblEndereoDestino);

		textField = new JTextField(origem);
		textField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		panelUp.add(textField);
		textField.setColumns(10);

		JLabel lblAssunto = new JLabel("                  Assunto:");
		lblAssunto.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		panelUp.add(lblAssunto);

		textField_1 = new JTextField();
		textField_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		panelUp.add(textField_1);
		textField_1.setColumns(10);

		JPanel panelDown = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelDown.getLayout();
		flowLayout.setVgap(20);
		getContentPane().add(panelDown, BorderLayout.SOUTH);

		JButton btnResponder = new JButton("RESPONDER");
		panelDown.add(btnResponder);

		JPanel panelMiddle = new JPanel();
		getContentPane().add(panelMiddle, BorderLayout.CENTER);
		panelMiddle.setLayout(null);

		JTextArea conteudoDaResposta = new JTextArea();
		conteudoDaResposta.setForeground(Color.GRAY);
		conteudoDaResposta.setFont(new Font("Monospaced", Font.ITALIC, 13));
		conteudoDaResposta.setText("Corpo do e-mail...");
		conteudoDaResposta.setBounds(12, 13, 658, 473);
		conteudoDaResposta.setBorder(new LineBorder(new Color(105, 105, 105)));
		panelMiddle.add(conteudoDaResposta);

		setVisible(true);
	}

	/**
	 * This method sets the type to (POPUP), sets the title of the JFrame, set the
	 * size, centers the frame and set The default close operation of the frame as
	 * (DISPOSE_ON_CLOSE).
	 * 
	 * @author ajcvo-iscteiul
	 * @since 2018
	 */
	private void init(String title, int width, int height) {
		setType(Type.POPUP);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle(title);
		setSize(width, height);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
	}
}
