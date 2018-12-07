package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import channels.email;

import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * This java file contains class that constructs the New Email Windows of the
 * APP. JFrame is extended.
 * 
 * @author ajcvo-iscteiul
 * @category Graphical User Interface (GUI)
 * @since 2018
 * @version 1.0
 */
public class NewEmailWindows extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtEndercoDestino;
	private JTextField txtAssunto;
	private JTextArea conteudoDaResposta;
	private JScrollPane conteudoDaRespostaScrollPane;
	private JButton btnResponder;

	/**
	 * Constructor of the NewEmailWindows that receives an E-mail adress as a
	 * parameter.
	 * 
	 * @author ajcvo-iscteiul
	 * @since 2018
	 * @param String origem
	 */

	public NewEmailWindows(email mail) {
		init("Resposta ao E-mail", 700, 700);
		getContentPane().setLayout(new BorderLayout(0, 20));

		JPanel panelUp = new JPanel();
		getContentPane().add(panelUp, BorderLayout.NORTH);
		panelUp.setLayout(new GridLayout(2, 2, -420, 5));

		JLabel lblEndereoDestino = new JLabel("  Endere\u00E7o Destino:");
		lblEndereoDestino.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		panelUp.add(lblEndereoDestino);

		txtEndercoDestino = new JTextField();
		txtEndercoDestino.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		panelUp.add(txtEndercoDestino);
		txtEndercoDestino.setColumns(10);

		JLabel lblAssunto = new JLabel("                  Assunto:");
		lblAssunto.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		panelUp.add(lblAssunto);

		txtAssunto = new JTextField();
		txtAssunto.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		panelUp.add(txtAssunto);
		txtAssunto.setColumns(10);

		JPanel panelDown = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelDown.getLayout();
		flowLayout.setVgap(20);
		getContentPane().add(panelDown, BorderLayout.SOUTH);

		btnResponder = new JButton("RESPONDER");
		panelDown.add(btnResponder);

		JPanel panelMiddle = new JPanel();
		getContentPane().add(panelMiddle, BorderLayout.CENTER);
		panelMiddle.setLayout(null);

		conteudoDaResposta = new JTextArea();
		conteudoDaResposta.setForeground(Color.GRAY);
		conteudoDaResposta.setText("Inserir texto...");
		conteudoDaResposta.setBounds(12, 13, 658, 473);
		conteudoDaResposta.setFont(new Font("Monospaced", Font.PLAIN, 19));
//		conteudoDaResposta.setBorder(new LineBorder(new Color(105, 105, 105)));
		conteudoDaResposta.setLineWrap(true);
		conteudoDaResposta.setWrapStyleWord(true);
		conteudoDaRespostaScrollPane = new JScrollPane(conteudoDaResposta);
		conteudoDaRespostaScrollPane.setBounds(12, 13, 658, 473);
//		panelMiddle.add(conteudoDaResposta);
		panelMiddle.add(conteudoDaRespostaScrollPane);

		conteudoDaRespostaFocusListener();
		responderListener();

		setVisible(true);
	}

	public void conteudoDaRespostaFocusListener() {
		conteudoDaResposta.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (conteudoDaResposta.getText().equals("")) {
					conteudoDaResposta.setText("Inserir texto...");
					conteudoDaResposta.setFont(new Font("Monospaced", Font.PLAIN, 19));
					conteudoDaResposta.setForeground(Color.GRAY);
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				if (conteudoDaResposta.getText().equals("Inserir texto...")) {
					conteudoDaResposta.setText("");
					conteudoDaResposta.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 19));
					conteudoDaResposta.setForeground(Color.BLACK);
				}
			}
		});
	}

	public void responderListener() {
		btnResponder.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!conteudoDaResposta.getText().equals("Inserir texto...")
						&& !conteudoDaResposta.getText().equals("")) {
					int optionChoosed = JOptionPane.showConfirmDialog(null, "Enviar e-mail?",
							"Aviso", JOptionPane.YES_NO_OPTION);
					if (optionChoosed == JOptionPane.YES_OPTION) {
						// FAZER O QUE TEM A FAZER E O DISPOSE FECHA A JANELA NO FIM
						dispose();
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Adicione algum texto ao seu e-mail", "ERRO!",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
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
	
	protected void setDestino(String destino) {
		txtEndercoDestino.setText(destino);
	}
}
