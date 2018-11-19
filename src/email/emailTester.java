package email;

import static org.junit.jupiter.api.Assertions.assertEquals;


import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Store;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class emailTester {

	private static email email;
	private static String username, password, subject, msg, username2, password2;
	private Store store;
	private Message testMsg;

	@BeforeAll
	public static void setUp() {
		email = new email();
		username = "testees@outlook.pt";
		password = "TESTEteste123";
		subject = "testSubject";
		msg = "testMsg";
		username2 = username + System.currentTimeMillis();
		password2 = password + System.currentTimeMillis();
		email.addCredentials(username, password);
		// email.connect(username, password);
	}

	@Test
	public void test1Registered/* IfRegistred */() {
		boolean test = email.isRegistered(username, password);
		assertEquals(true, test);
	}

	@Test
	public void test2AddCrentials/* AddCredentials */() {

		email.addCredentials(username2, password2);
		boolean test = email.isRegistered(username2, password2);
		assertEquals(true, test);
	}

	@Test
	public void test3DeleteCredentials/* DeleteCredentials */() {

		email.deleteCredentials(username2, password2);
		boolean test = email.isRegistered(username2, password2);
		assertEquals(false, test);
	}

	@Test
	public void testSend() {

		email.connect(username, password);
		email.send(username, username, password, subject, msg);

		try {
			store = email.getStore();
			Folder folderSent = store.getFolder("SENT");
			folderSent.open(Folder.READ_ONLY);
			Message[] foundMessages = folderSent.getMessages();
			testMsg = foundMessages[foundMessages.length - 1];
			assertEquals(username, testMsg.getFrom()[0].toString());
			assertEquals(username, testMsg.getAllRecipients()[0].toString());
			assertEquals(subject, testMsg.getSubject());

		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void testReceive() {

		email.connect(username, password);
		
		try {
			store = email.getStore();
			Folder folderInbox = store.getFolder("INBOX");
			folderInbox.open(Folder.READ_ONLY);
			int numUnreadMessage = folderInbox.getUnreadMessageCount();
			email.send(username, username, password, subject, msg);
			Thread.sleep(15000);
			assertEquals(true, folderInbox.getUnreadMessageCount() > numUnreadMessage);

		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		Result result = JUnitCore.runClasses(emailTester.class);

		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}

		System.out.println(result.wasSuccessful());
	}

}
