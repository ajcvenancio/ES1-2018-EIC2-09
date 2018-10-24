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


public class email {
	

	/**
	 * *******************************************************
	 * ***********************SEARCH**************************
	 * *******************************************************
	 */

	public void searchEmail(String username, String password) {
		Properties properties = new Properties();

		properties.put("mail.imap.host", "imap-mail.outlook.com");
		properties.put("mail.imap.port", 993);

		properties.setProperty("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.setProperty("mail.imap.socketFactory.fallback", "false");
		properties.setProperty("mail.imap.socketFactory.port", String.valueOf(993));

		Session session = Session.getDefaultInstance(properties);

		try {

			/* Connect to the message Store */
			Store store = session.getStore("imap");
			store.connect(username, password);

			/* open Inbox folder */
			Folder folderInbox = store.getFolder("INBOX");
			folderInbox.open(Folder.READ_ONLY);

			Message[] foundMessages = folderInbox.getMessages();
			if (foundMessages.length == 0) {
				System.out.println("Your Inbox is empty!");
			}
			for (int i = foundMessages.length - 1; i >= 0; i--) {
				Message message = foundMessages[i];
				System.out.println("From: " + message.getFrom()[0]);
				System.out.println("Subject: " + message.getSubject());
				

				System.out.println("Date: " + message.getSentDate());

				System.out.println("");
				System.out.println("------------: " + i + ":---------------");

			}

			folderInbox.close(false);
			store.close();

		} catch (NoSuchProviderException ex) {
			System.out.println("No provider.");
			ex.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * *******************************************************
	 * ***********************SEND**************************
	 * *******************************************************
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

	public static void main(String[] args) {

		String username = "testees@outlook.pt";
		String password = "TESTEteste123";
		email email = new email();

		email.send(username, username, password, "Subject", "CHEGOU");
		email.searchEmail(username, password);

	}

}
