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
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class InfoWindows extends JFrame {
	private JTextField textField;
	private JTextField textField_1;
	public InfoWindows() {
		setType(Type.POPUP);
		setTitle("Info");
		setSize(new Dimension(700, 700));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 20));
		
		JPanel panelUp = new JPanel();
		getContentPane().add(panelUp, BorderLayout.NORTH);
		panelUp.setLayout(new GridLayout(2, 2, -420, 5));
		
		JLabel lblEndereoDestino = new JLabel("Endere\u00E7o Destino:");
		lblEndereoDestino.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		panelUp.add(lblEndereoDestino);
		
		textField = new JTextField();
		panelUp.add(textField);
		textField.setColumns(10);
		
		JLabel lblAssunto = new JLabel("Assunto:");
		lblAssunto.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		panelUp.add(lblAssunto);
		
		textField_1 = new JTextField();
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
		
		JTextArea conteudoDaInfo = new JTextArea();
		conteudoDaInfo.setBounds(12, 13, 658, 312);
		conteudoDaInfo.setEditable(false);
		conteudoDaInfo.setBorder(new LineBorder(new Color(105, 105, 105)));
		panelMiddle.add(conteudoDaInfo);
		
		JTextArea conteudoDaResposta = new JTextArea();
		conteudoDaResposta.setForeground(Color.GRAY);
		conteudoDaResposta.setFont(new Font("Monospaced", Font.ITALIC, 13));
		conteudoDaResposta.setText("Responder...");
		conteudoDaResposta.setBounds(12, 338, 658, 165);
		conteudoDaResposta.setBorder(new LineBorder(new Color(105, 105, 105)));
		panelMiddle.add(conteudoDaResposta);
	}
}
