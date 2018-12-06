package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;


//import com.restfb.types.Notification;

import channels.FacebookUser;
import channels.Notification;
import config.SiteLogin;

/**
 * This java file contains class that constructs the Main Windows of the APP.
 * JFrame is extended. Graphical User Interface (GUI)
 * 
 * @author ajcvo-iscteiul
 * @since 2018
 * @version 1.1
 */
public class MainWindows extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTable timelineTable;
	private JPanel panelCenter;
	private JButton btnResponder;
	private JButton btnElearning;
	private JButton btnFenix;
	private JButton btnRefresh;
	private JButton btnLogoff;
	private JButton btnSair;
	private JComboBox<String> cbFilter;
	private JTextField txtProcurar;
	private JScrollPane itemContentScrollPane;
	private JTextArea itemContent;
	private MainClass mc;
	private List<String> contentList;

	public MainWindows(MainClass mc) {
		contentList=new ArrayList<String>();
//		this.mc = mc;
		setResizable(false);
		init("Bom Dia Academia", 1000, 800);
		makeSidebar();
		makeCenterPanel();
		responderListener();
		elearningListener();
		fenixListener();
		finalSettings();
		procurarFocusListener();
		procurarEnterListener();
		filterListener();
		refreshListener();
		logOffListener();
		sairListener();
		clickListener();
	}

	/**
	 * This method sets the title and the sizer (x,y) of the JFrame.
	 * 
	 * @param
	 * @author ajcvo-iscteiul
	 * @since 2018
	 *
	 */
	public void init(String title, int width, int height) {
		setTitle(title);
		setSize(width, height);
	}
	

	/**
	 * This method center the frame, set The default close operation of the frame as
	 * (EXIT_ON_CLOSE) and uses the JFrame method setVisible(true) to make the frame
	 * visible for the user.
	 * 
	 * @author ajcvo-iscteiul
	 * @since 2018
	 */
	public void finalSettings() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	/**
	 * Constructs the sidebar of the Main Windows using a JLabel. The JLabel is
	 * composed by 5 JButtons and 1 JLabel with an ImageIcon. That JPanel is added
	 * to the JFrame.
	 * 
	 * @author ajcvo-iscteiul
	 * @since 2018
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
		ImageIcon iscteIcon = new ImageIcon("imageIscte2.png");
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

		btnElearning = new JButton("E-Learning");
		panelLeft.add(btnElearning);

		btnFenix = new JButton("F\u00E9nix");
		panelLeft.add(btnFenix);

		cbFilter = new JComboBox<String>();
		cbFilter.setBackground(Color.WHITE);
		addFilterItems();
		panelLeft.add(cbFilter);

		JLabel label = new JLabel("");
		panelLeft.add(label);

//		JLabel label_1 = new JLabel("");
//		panelLeft.add(label_1);

//		JLabel label_2 = new JLabel("");
//		panelLeft.add(label_2);

		btnLogoff = new JButton("Log Off");
		btnLogoff.setBackground(new Color(255, 255, 255));
		btnLogoff.setBorderPainted(true);
		panelLeft.add(btnLogoff);

		btnSair = new JButton("Sair");
		btnSair.setBackground(new Color(255, 255, 255));
		btnSair.setBorderPainted(true);
		panelLeft.add(btnSair);
	}

	/**
	 * Constructs a JPanel with 1 JLabel as title and a JTable surrounded by a
	 * JScrollPanel. The JTable has the timeline of every Facebook posts, Twitter
	 * posts, E-mail received within 24 hours. That JPanel is added to the JFrame.
	 * 
	 * @author ajcvo-iscteiul
	 * @since 2018
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
		// scrollPane.setBorder(new LineBorder(new Color(105, 105, 105)));
		panelCenter.add(timelineTableScrollPane);

		DefaultTableModel tableModel = new DefaultTableModel(null,
				new Object[] { "Data", "Canal", "Origem", "Assunto" });

		timelineTable = new JTable(tableModel) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		// table.setBorder(new LineBorder(SystemColor.controlDkShadow));
		timelineTable.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		timelineTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 15));
		timelineTable.setBackground(new Color(240, 240, 240));
		timelineTableScrollPane.setViewportView(timelineTable);

		itemContent = new JTextArea();
		itemContent.setBounds(46, 319, 779, 378);
//		itemContent.setBorder(new LineBorder(SystemColor.controlDkShadow));
		itemContent.setFont(new Font("Segoe UI", Font.BOLD, 19));
		itemContent.setEditable(false);
		itemContent.setLineWrap(true);
		itemContent.setWrapStyleWord(true);
		itemContentScrollPane = new JScrollPane(itemContent);
		itemContentScrollPane.setBounds(46, 319, 779, 378);
//		panelCenter.add(itemContent);
		panelCenter.add(itemContentScrollPane);

		btnResponder = new JButton("Responder");
		btnResponder.setBounds(358, 710, 138, 42);
		panelCenter.add(btnResponder);

		btnRefresh = new JButton("Refresh");
		btnRefresh.setBounds(728, 13, 97, 25);
		panelCenter.add(btnRefresh);

		txtProcurar = new JTextField();
		txtProcurar.setText("Procurar");
		txtProcurar.setToolTipText("Procurar");
		txtProcurar.setBounds(46, 13, 196, 22);
		panelCenter.add(txtProcurar);
		txtProcurar.setColumns(10);

	//	addNotification("data", "E-mail", "origem", "assunto", null);
	}

	/**
	 * Adds a row to the timeline JTable. Each row is a notification.
	 * 
	 * @author ajcvo-iscteiul
	 * @since 2018
	 */
	public void addNotification(String data, String canal, String origem, String assunto, String conteudo) {
		DefaultTableModel model = (DefaultTableModel) timelineTable.getModel();
		model.addRow(new Object[] { data, canal, origem, assunto });
		panelCenter.repaint();
	}
	
	/**
	 * Adds a Notification List to the timeline JTable. Each row is a notification.
	 * 
	 * @author amsgn-iscteiul
	 * @since 2018
	 */
	public void addNotificationList(ArrayList<Notification> ns){
		DefaultTableModel model = (DefaultTableModel) timelineTable.getModel();
		for(Notification n:ns){
			model.addRow(new Object[] { n.getDate().toString(), n.getChannel(), n.getSource(), n.getSubject() });
			panelCenter.repaint();
			contentList.add(n.getText());
		}
	}

	/**
	 * Removes a row to the timeline JTable. Each row is a notification.
	 * 
	 * @author ajcvo-iscteiul
	 * @since 2018
	 */
	public void removeNotification(int row) {
		DefaultTableModel model = (DefaultTableModel) timelineTable.getModel();
		model.removeRow(row);
		panelCenter.repaint();
	}

	/**
	 * Maximizes MainWindows.
	 * 
	 * @author ajcvo-iscteiul
	 * @since 2018
	 */
	public void maximizeWindow() {
		setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
	}

	/**
	 * Defines the actionListner of the "Responder" button.
	 * 
	 * @author ajcvo-iscteiul
	 * @since 2018
	 */
	public void responderListener() {
		btnResponder.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!timelineTable.getSelectionModel().isSelectionEmpty()) {
					int row = timelineTable.getSelectedRow();
					Object canal = timelineTable.getValueAt(row, 1);
					if (canal.equals("E-mail")) {
						String origem = (String) timelineTable.getValueAt(row, 2);
						NewEmailWindows emailWindows = new NewEmailWindows(origem);
					} else if (canal == "Facebook") {

					} else if (canal == "Twitter") {

					}
				} else {
					JOptionPane.showMessageDialog(null, "N�o est� nada selecionado", "ERRO!",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
	}

	/**
	 * Defines the actionListner of the "elearning" button.
	 * 
	 * @author ajcvo-iscteiul
	 * @since 2018
	 */
	public void elearningListener() {
		btnElearning.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SiteLogin elearninglogin = new SiteLogin("ze", "asfsaas");
				elearninglogin.runElearning();
			}
		});
	}

	public void fenixListener() {
		btnFenix.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SiteLogin fenixlogin = new SiteLogin("ze", "fasddsa");
				fenixlogin.runFenix();
			}
		});
	}

	public void refreshListener() {
		btnRefresh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				contentList.clear();
				addNotificationList(mc.refreshAll());
			}
		});
	}

	public void procurarFocusListener() {
		txtProcurar.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				if (txtProcurar.getText().equals(""))
					txtProcurar.setText("Procurar");
			}

			@Override
			public void focusGained(FocusEvent e) {
				if (txtProcurar.getText().equals("Procurar"))
					txtProcurar.setText("");
			}
		});
	}
	
	public void procurarEnterListener() {
		if(txtProcurar.isCursorSet()) {
			txtProcurar.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					//AO CLICAR NO ENTER, PROCURAR ALGUMA COISA
				}
			});
		}
	}
	
	public void filterListener() {
		cbFilter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(cbFilter.getSelectedItem().equals("No Filter")) {
					//MOSTRAR TUDO
				} else if(cbFilter.getSelectedItem().equals("E-mail")) {
					//MOSTRAR DO E-MAIL
				} else if(cbFilter.getSelectedItem().equals("Facebook")) {
					//MOSTRAR DO FACEBOOK
				} else {
					//MOSTRAR DO TWITTER
				}
			}
		});
	}
	
	public void logOffListener() {
		btnLogoff.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int optionChoosed = JOptionPane.showConfirmDialog(null, "Tem a certeza que pretende fazer log off?", "Aviso", JOptionPane.YES_NO_OPTION);
				if(optionChoosed == JOptionPane.YES_OPTION) {
					dispose();
					LoginWindows loginWindows = new LoginWindows();
				}
			}
		});
	}
	
	public void sairListener() {
		btnSair.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int optionChoosed = JOptionPane.showConfirmDialog(null, "Tem a certeza que pretende sair?", "Aviso", JOptionPane.YES_NO_OPTION);
				if(optionChoosed == JOptionPane.YES_OPTION) {
					dispose();
				}
			}
		});
	}

	/**
	 * Return the JTable of the Timeline.
	 * 
	 * @author ajcvo-iscteiul
	 * @since 2018
	 */
	public JTable getTimelineTable() {
		return timelineTable;
	}	
	
	/**
	 * Listener that allows the user to read the content after selecting an item.
	 * 
	 * @author amsgn-iscteiul
	 * @since 2018
	 */
	public void clickListener(){
		timelineTable.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println(timelineTable.getSelectedRow());
				itemContent.setText(contentList.get(timelineTable.getSelectedRow()));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				System.out.println(timelineTable.getSelectedRow());
				itemContent.setText(contentList.get(timelineTable.getSelectedRow()));
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
	
	}

	public void addFilterItems() {
		cbFilter.addItem("No Filter");
		cbFilter.addItem("E-mail");
		cbFilter.addItem("Facebook");
		cbFilter.addItem("Twitter");
	}
	
	public JButton getBtnRefresh() {
		return btnRefresh;
	}
}
