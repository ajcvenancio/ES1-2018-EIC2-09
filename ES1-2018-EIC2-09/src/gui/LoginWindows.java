package gui;

import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *	@author ajcvo-iscteiul
 *	@category Graphical User Interface (GUI)
 *	@description This java file contains class that constructs the Login Windows of the APP. JFrame is extended.
 *	@since 2018
 *	@version 1.0
 */
public class LoginWindows extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * 	@author ajcvo-iscteiul
	 * 	@description This constructor makes the Login Windows. All the components are added in this. The components are:
	 *  3 JLabel, 1 JTextfield, 1JPasswordField, 1 JButton.
	 * 	@since 2018
	 */
	public LoginWindows() {
		setTitle("Bom Dia ISCTE");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel logo = new JLabel("");
		logo.setBounds(168, 62, 88, 70);
		getContentPane().add(logo);
		logo.setHorizontalAlignment(SwingConstants.CENTER);
		ImageIcon iscteIcon = new ImageIcon(
				"C:\\Users\\User\\Desktop\\ISCTE\\3A_1S\\ES1\\git\\ES1-2018-EIC2-09\\ES1-2018-EIC2-09\\imageIscte2.png");
		Image iscteImage = iscteIcon.getImage();
		Image iscteImageRescaled = iscteImage.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
		ImageIcon iscteIconRescaled = new ImageIcon(iscteImageRescaled);
		logo.setIcon(iscteIconRescaled);
		
		textField = new JTextField();
		textField.setBounds(84, 208, 261, 22);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblEmail.setBounds(94, 179, 56, 16);
		getContentPane().add(lblEmail);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblPassword.setBounds(94, 247, 73, 16);
		getContentPane().add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(84, 280, 261, 22);
		getContentPane().add(passwordField);
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		btnLogin.setBounds(145, 343, 134, 58);
		getContentPane().add(btnLogin);
		
	}
	
	
	public void init(String title, int width, int height) {
		
	}
}
