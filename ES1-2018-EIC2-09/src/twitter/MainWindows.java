package twitter;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.SystemColor;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextArea;

/**
 *	@author ajcvo-iscteiul
 *	@category Graphical User Interface (GUI)
 *	@description This java file contains class that constructs the Main Windows of the APP. JFrame is extended.
 *	@since 2018
 *	@version 1.0
 */
public class MainWindows extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTable timelineTable;
	private JPanel panelCenter;

	public MainWindows() {
		setResizable(false);
		init("Bom Dia Academia", 1000, 800);
		makeSidebar();
		makeCenterPanel();
		finalSettings();
	}

	/**
	 * @param title - Uses the JFrame.setTitle(title).
	 * @param width	- Uses the JFrame.setSize(width, height).
	 * @param height	- Uses the JFrame.setSize(width, height).
	 * @author ajcvo-iscteiul
	 * @description This method sets the title and the sizer (x,y) of the JFrame.
	 * @since 2018
	 *
	 */
	public void init(String title, int width, int height) {
		setTitle(title);
		setSize(width, height);
	}

	/**
	 *	@author ajcvo-iscteiul
	 *	@since 2018
	 *	@description This method set The default close operation of the frame as (EXIT_ON_CLOSE) and
	 *	uses the JFrame method setVisible(true) to make the frame visible for the user.
	 */
	public void finalSettings() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	/**
	 * 	@author ajcvo-iscteiul
	 * 	@description Constructs the sidebar of the Main Windows using a JLabel. 
	 * 	The JLabel is composed by 5 JButtons and 1 JLabel with an ImageIcon.
	 * 	That JPanel is added to the JFrame.
	 * 	@since 2018
	 */
	public void makeSidebar() {
		JPanel panelLeft = new JPanel();
		panelLeft.setBorder(new LineBorder(new Color(105, 105, 105)));
		panelLeft.setBackground(SystemColor.control);
		getContentPane().add(panelLeft, BorderLayout.WEST);
		panelLeft.setLayout(new GridLayout(10, 1, 0, 0));

		JLabel iscteLabel = new JLabel();
		panelLeft.add(iscteLabel);
		iscteLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ImageIcon iscteIcon = new ImageIcon(
				"C:\\Users\\User\\Desktop\\ISCTE\\3A_1S\\ES1\\git\\ES1-2018-EIC2-09\\ES1-2018-EIC2-09\\imageIscte2.png");
		Image iscteImage = iscteIcon.getImage();
		Image iscteImageRescaled = iscteImage.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
		ImageIcon iscteIconRescaled = new ImageIcon(iscteImageRescaled);
		iscteLabel.setIcon(iscteIconRescaled);

		JButton btnEnviarEmail = new JButton("Enviar e-mail");
		panelLeft.add(btnEnviarEmail);

		JButton btnTwiiterPost = new JButton("Twiiter Post");
		panelLeft.add(btnTwiiterPost);

		JButton btnFacebookPost = new JButton("Facebook Post");
		panelLeft.add(btnFacebookPost);

		JButton btnElearning = new JButton("E-Learning");
		btnElearning.setBackground(new Color(224, 255, 255));
		panelLeft.add(btnElearning);

		JButton btnFnix = new JButton("F\u00E9nix");
		panelLeft.add(btnFnix);

		JLabel label = new JLabel("");
		panelLeft.add(label);

		JLabel label_1 = new JLabel("");
		panelLeft.add(label_1);

		JLabel label_2 = new JLabel("");
		panelLeft.add(label_2);

		JButton btnSair = new JButton("Sair");
		panelLeft.add(btnSair);
	}

	/**
	 * 	@author ajcvo-iscteiul
	 * 	@description Constructs a JPanel with 1 JLabel as title and a JTable surrounded by a JScrollPanel.
	 * 	The JTable has the timeline of every Facebook posts, Twitter posts, E-mail received within 24 hours.
	 * 	That JPanel is added to the JFrame.
	 * 	@since 2018
	 */
	public void makeCenterPanel() {
		panelCenter = new JPanel();
		panelCenter.setBorder(new LineBorder(SystemColor.controlDkShadow));
		panelCenter.setBackground(new Color(240, 255, 255));
		getContentPane().add(panelCenter, BorderLayout.CENTER);
		panelCenter.setLayout(null);

		JLabel labelTimeline = new JLabel("TIMELINE");
		labelTimeline.setBounds(403, 20, 61, 20);
		labelTimeline.setFont(new Font("Segoe UI", Font.BOLD, 14));
		labelTimeline.setHorizontalAlignment(SwingConstants.CENTER);
		panelCenter.add(labelTimeline);

		JScrollPane timelineTableScrollPane = new JScrollPane();
		timelineTableScrollPane.setBounds(46, 53, 779, 253);
		//scrollPane.setBorder(new LineBorder(new Color(105, 105, 105)));
		panelCenter.add(timelineTableScrollPane);

		timelineTable = new JTable(new DefaultTableModel(null, new Object[]{"Data", "Canal", "Origem", "Assunto"}));
		//table.setBorder(new LineBorder(SystemColor.controlDkShadow));
		timelineTable.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		timelineTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 15));
		timelineTable.setBackground(new Color(240, 240, 240));
		timelineTableScrollPane.setViewportView(timelineTable);
		
		JTextArea itemContent = new JTextArea();
		itemContent.setBounds(46, 319, 779, 378);
		itemContent.setBorder(new LineBorder(SystemColor.controlDkShadow));
		panelCenter.add(itemContent);
		
		JButton btnResponder = new JButton("Responder");
		btnResponder.setBounds(358, 710, 138, 42);
		panelCenter.add(btnResponder);
	}
	/**
	 * 	@author ajcvo-iscteiul
	 * 	@description Adds a row to the timeline JTable. Each row is a notification.
	 * 	@since 2018
	 */
	public void addNotification(String data, String canal, String origem, String assunto ){
		DefaultTableModel model = (DefaultTableModel) timelineTable.getModel();
		model.addRow(new Object[]{data,canal,origem,assunto});
		panelCenter.repaint();
	}
	
	/**
	 * 	@author ajcvo-iscteiul
	 * 	@description Maximizes MainWindows.
	 * 	@since 2018
	 */
	public void maximizeWindow() {
		setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
	}
	
	public static void main(String[] args) {
		MainWindows m = new MainWindows();
	}
}
