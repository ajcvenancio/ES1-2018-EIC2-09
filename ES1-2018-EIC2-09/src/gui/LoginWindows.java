package gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import channels.email;

/**
 * This java file contains class that constructs the Login Windows of the APP.
 * JFrame is extended.
 * 
 * @author ajcvo-iscteiul
 * @category Graphical User Interface (GUI)
 * @since 2018
 * @version 1.2
 */
public class LoginWindows extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField emailField;
	private JPasswordField passwordField;
	private JButton btnLogin;
	private boolean autenticated = false;
	private email mail;

	/**
	 * This constructor makes the Login Windows. All the components are added in
	 * this. The components are: 3 JLabel, 1 JTextfield, 1JPasswordField, 1 JButton.
	 * 
	 * @author ajcvo-iscteiul
	 * @since 2018
	 */
	public LoginWindows() {
		setResizable(false);
		getContentPane().setLayout(null);

		JLabel logo = new JLabel("");
		logo.setBounds(168, 62, 88, 70);
		getContentPane().add(logo);
		logo.setHorizontalAlignment(SwingConstants.CENTER);
		ImageIcon iscteIcon = new ImageIcon(
				"imageIscte2.png");
		Image iscteImage = iscteIcon.getImage();
		Image iscteImageRescaled = iscteImage.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
		ImageIcon iscteIconRescaled = new ImageIcon(iscteImageRescaled);
		logo.setIcon(iscteIconRescaled);

		emailField = new JTextField("");
		emailField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		emailField.setBounds(84, 208, 261, 30);
		getContentPane().add(emailField);
		emailField.setColumns(10);

		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblEmail.setBounds(94, 179, 56, 16);
		getContentPane().add(lblEmail);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblPassword.setBounds(94, 251, 73, 16);
		getContentPane().add(lblPassword);

		passwordField = new JPasswordField("");
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		passwordField.setBounds(84, 280, 261, 30);
		getContentPane().add(passwordField);

		btnLogin = new JButton("LOGIN");
		btnLogin.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		btnLogin.setBounds(145, 343, 134, 58);
		getContentPane().add(btnLogin);
		
		
		loginListener();
		init("Login - Bom dia Academia", 430, 550);

	}

	/**
	 * Defines the actionListner of the "Login" button.
	 * 
	 * @author ajcvo-iscteiul
	 * @since 2018
	 */
	public void loginListener() {
		btnLogin.addActionListener(new ActionListener() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!emailField.getText().equals("") && (passwordField.getPassword().length != 0)) {
					doLogin();
					if (autenticated) {
						dispose();
						MainClass mc = new MainClass();
						mc.setEmail(mail);
						if(mail.getStore().isConnected())
							mc.setOffline(false);
						else
							mc.setOffline(true);
						mc.startMainWindow(mc);
					} else {
						JOptionPane.showMessageDialog(null, "Credenciais inválidas", "ERRO!",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Credenciais inválidas", "ERRO!",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
	}

	public void doLogin() {
		mail = new email(emailField.getText(), String.valueOf(passwordField.getPassword()));
		if(mail.getStore().isConnected()) {
			
			autenticated=true;
			
			System.out.println(autenticated);
		}else {
			if(mail.isRegistered()) {
				autenticated=true;
				JOptionPane.showMessageDialog(getContentPane(),
					    "You are Offline",
					    "Connection Failed", JOptionPane.WARNING_MESSAGE);
			}
		}
	}

	/**
	 * This method sets the title of the JFrame, set the size, centers the frame,
	 * set The default close operation of the frame as (EXIT_ON_CLOSE) and sets the
	 * JFrame visible.
	 * 
	 * @author ajcvo-iscteiul
	 * @since 2018
	 */
	public void init(String title, int width, int height) {
		setTitle(title);
		setSize(new Dimension(width, height));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		LoginWindows lw = new LoginWindows();
//		lw.init("Login - Bom dia Academia", 430, 550);
	}
}
