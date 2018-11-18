import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * 
 * @author jhmsa-iscteiul
 * 
 *         A seguinte classe é utilizada para enviar do email do utilizador para
 *         um email a escolha e mostrar a inbox do email do utilizador
 *
 */

public class email {
	private Store store;
	private static File file = new File("config.xml");
	private static DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

	/**
	 * *******************************************************
	 * ***********************SEARCH**************************
	 * *******************************************************
	 * 
	 * Esta função executa a ligação aos servidores do Outlook, indo após a conecção
	 * buscar todos os emails da pasta Inbox do email do utilizador, colocando-os
	 * num Array É também invocada uma função que imprime informação dos emails na
	 * consola
	 * 
	 * 
	 * @param username
	 *            Este parametro é o endereço de email do utlizador
	 * @param password
	 *            Este parametro é a password do endereço de email do utlizador
	 * @exception NoSuchProviderException
	 * @exception MessagingException
	 */
	private void connect(String username, String password) {
		Properties properties = new Properties();

		properties.put("mail.imap.host", "imap-mail.outlook.com");
		properties.put("mail.imap.port", 993);

		properties.setProperty("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.setProperty("mail.imap.socketFactory.fallback", "false");
		properties.setProperty("mail.imap.socketFactory.port", String.valueOf(993));

		Session session = Session.getDefaultInstance(properties);

		try {
			/* Connect to the message Store */

			store = session.getStore("imap");
			store.connect(username, password);
			System.out.println("Conectou");

			if (!isRegistered(username, password))
				addCredentials(username, password);

		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

	public void searchEmail(String username, String password) {

		try {

			/* open Inbox folder */
			Folder folderInbox = store.getFolder("INBOX");
			folderInbox.open(Folder.READ_ONLY);

			Message[] foundMessages = folderInbox.getMessages();
			if (foundMessages.length == 0) {
				System.out.println("Your Inbox is empty!");
			} else {
				showInbox(foundMessages);
			}

			folderInbox.close(false);
			// store.close();

		} catch (NoSuchProviderException ex) {
			System.out.println("No provider.");
			ex.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

	/**
	 * *******************************************************
	 * ***********************SHOW INBOX *********************
	 * *******************************************************
	 * 
	 * A função seguinte imprime na consola O emissor do email, o Assunto e a data
	 * de envio
	 *
	 * @param foundMessages
	 *            Array com todas as Mensagens da pasta Inbox do utilizador
	 * @exception MessagingException
	 *
	 */
	private void showInbox(Message[] foundMessages) {
		for (int i = foundMessages.length - 1; i >= 0; i--) {
			Message message = foundMessages[i];
			try {
				System.out.println("From: " + message.getFrom()[0]);
				System.out.println("Subject: " + message.getSubject());

				System.out.println("Date: " + message.getSentDate());

				System.out.println("");
				System.out.println("------------: " + i + ":---------------");
			} catch (MessagingException e) {
				e.printStackTrace();
			}

		}

	}

	/**
	 * 
	 * *******************************************************
	 * ***********************SEND****************************
	 * *******************************************************
	 * 
	 * Esta função inicialmente executa a conecção com o servidor do Outlook,
	 * realizando autenticação do Utilizador e posteriormente é criada a mensagem a
	 * enviar e é feito o seu envio.
	 * 
	 * @param receiverEmail
	 *            Endereço email destino
	 * @param username
	 *            Endereço de email do Utilizador
	 * @param password
	 *            Password do endereço de email do Utilizador
	 * @param subject
	 *            Assunto do email a enviar
	 * @param msg
	 *            Corpo da mensagem a enviar no email
	 * @exception MessagingException
	 * 
	 */

	public void send(String receiverEmail, String username, String password, String subject, String msg) {

		// ************************************
		// ********** Properties **************
		// ************************************

		Properties props = new Properties();

		props.put("mail.smtp.host", "smtp-mail.outlook.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");

		// ************************************
		// ********** Authentication **********
		// ************************************
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiverEmail));
			message.setSubject(subject);

			message.setContent(msg, "text/html");

			Transport transport = session.getTransport("smtp");
			transport.connect("smtp-mail.outlook.com", 587, username, password);
			transport.sendMessage(message, message.getAllRecipients());
			System.out.println("Mail sent successfully at: " + receiverEmail);
			transport.close();

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	

	private void addCredentials(String username, String password) {

		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document xmlDoc = builder.parse(file);

			Element root = xmlDoc.getDocumentElement();

			Element credential = xmlDoc.createElement("credential");
			credential.setAttribute("Username", username);
			credential.setAttribute("Password", password);
			root.appendChild(credential);

			DOMSource source = new DOMSource(xmlDoc);

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			StreamResult result = new StreamResult("config.xml");
			transformer.transform(source, result);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}

	}

	private boolean isRegistered(String username, String password) {

		try {

			DocumentBuilder builder = factory.newDocumentBuilder();

			Document xmlDoc = builder.parse(file);

			NodeList credentialList = xmlDoc.getElementsByTagName("credential");
			

			if (credentialList.getLength() == 0)
				return false;

			for (int i = 0; i < credentialList.getLength(); i++) {
				NamedNodeMap nodeAttributtes = credentialList.item(i).getAttributes();
				if (nodeAttributtes.item(1).getNodeValue().equals(username)) {
					if (nodeAttributtes.item(0).getNodeValue().equals(password)) {
						return true;
					}
				}
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	private void deleteCredentials(String username, String password) {
		if(isRegistered(username, password)) {
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document xmlDoc = builder.parse(file);
			
			NodeList credentialList = xmlDoc.getElementsByTagName("credential");
			
			for (int i = 0; i < credentialList.getLength(); i++) {
				NamedNodeMap nodeAttributtes = credentialList.item(i).getAttributes();
				if (nodeAttributtes.item(1).getNodeValue().equals(username)) {
					Element toDelete = (Element) credentialList.item(i);
					toDelete.getParentNode().removeChild(toDelete);
					i = credentialList.getLength();
				}
			}
			DOMSource source = new DOMSource(xmlDoc);

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			StreamResult result = new StreamResult("config.xml");
			transformer.transform(source, result);

		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}

	}

	/**
	 * 
	 * Método main,que cria uma instancia da classe email, e faz utilização das suas
	 * classes send e searchEmail
	 * 
	 */
	

	public static void main(String[] args) {

		String username = "testees@outlook.pt";
		String password = "TESTEteste123";
		// Scanner in = new Scanner(System.in);
		//
		// System.out.println("Email: ");
		// String username = in.nextLine();
		//
		// System.out.println("Pass: ");
		// String password = in.nextLine();

		email email = new email();

//		email.connect(username, password);
//		 email.addCredentials(username, password);
		// email.isRegistred(username, password);
		
		email.deleteCredentials(username, password);
		System.out.println(email.isRegistered(username, password));
	}

}
