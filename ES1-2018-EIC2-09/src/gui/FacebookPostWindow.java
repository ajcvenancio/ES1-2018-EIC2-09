package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import channels.FacebookUser;

public class FacebookPostWindow extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTextArea conteudoDaResposta;
	private JButton btnEnviarFacebook;
	private FacebookUser facebook;

	public FacebookPostWindow(FacebookUser facebook) {
		this.facebook=facebook;
		open(700, 700, "Enviar Post");
		close();
		conteudoDaRespostaFocusListener();
		btnListener();
	}
	
	private void open(int width, int height, String title) {
		setType(Type.POPUP);
		setTitle(title);
		setSize(new Dimension(width, height));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
		getContentPane().setLayout(null);
		
		conteudoDaResposta = new JTextArea();
		conteudoDaResposta.setBounds(12, 13, 658, 547);
		conteudoDaResposta.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 19));
		conteudoDaResposta.setBorder(new LineBorder(new Color(105, 105, 105)));
		conteudoDaResposta.setLineWrap(true);
		conteudoDaResposta.setWrapStyleWord(true);
		conteudoDaResposta.setEditable(true);
		getContentPane().add(conteudoDaResposta);
		
		btnEnviarFacebook = new JButton("Postar Facebook");
		btnEnviarFacebook.setBounds(267, 573, 125, 51);
		getContentPane().add(btnEnviarFacebook);
	}
	
	private void close() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	private void conteudoDaRespostaFocusListener() {
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
	
	private void btnListener() {
		btnEnviarFacebook.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				facebook.postOnGroup(conteudoDaResposta.getText());
				conteudoDaResposta.setText("");
				dispose();			}
		});
	}
	
	public static void main(String[] args) {
		FacebookUser fb=new FacebookUser();
		FacebookPostWindow twp = new FacebookPostWindow(fb);
	}
}
