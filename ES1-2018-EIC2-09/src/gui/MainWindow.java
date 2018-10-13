package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class MainWindow extends JFrame {
	private JTable table;
	public MainWindow() {
		
		JPanel upPanel = new JPanel();
		getContentPane().add(upPanel, BorderLayout.NORTH);
		upPanel.setLayout(new GridLayout(0, 3, 0, 0));
		
		JButton btnEnviarEmail = new JButton("Enviar e-mail");
		upPanel.add(btnEnviarEmail);
		
		JButton btnFacebookPost = new JButton("Facebook Post");
		upPanel.add(btnFacebookPost);
		
		JButton btnTwitterPost = new JButton("Twitter Post");
		upPanel.add(btnTwitterPost);
		
		JPanel downPanel = new JPanel();
		getContentPane().add(downPanel, BorderLayout.SOUTH);
		
		JButton btnSair = new JButton("Sair");
		downPanel.add(btnSair);
		
		JPanel centerPanel = new JPanel();
		getContentPane().add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new BorderLayout(0, 0));
		
		table = new JTable();
		centerPanel.add(table, BorderLayout.CENTER);
		
		JLabel lblTmeline = new JLabel("TMELINE");
		lblTmeline.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(lblTmeline, BorderLayout.NORTH);
		
		String [] columnNames = {"Data","Canal","Origem","Assunto",""};
	}

}
