package teste;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import channels.Notification;

public class NotificationTest {

	private Notification notification;
	
	@Before
	public void Init() {
		notification = new Notification("canal", new Date(2000, 1, 3, 4, 5, 6), "origem", "assunto", "texto");
	}
	
	@Test
	public void testGetChannel() {
		assertEquals("canal", notification.getChannel());
	}
	
	@Test
	public void testGetDataObject() {
		assertNotNull(notification.getDateObject());
	}
	
	@Test
	public void testGetDate() {
		assertEquals("03-02-3900 04:05:06", notification.getDate());
	}
	
	@Test
	public void testGetSource() {
		assertEquals("origem", notification.getSource());
	}
	
	@Test
	public void testGetSubject() {
		assertEquals("assunto", notification.getSubject());
	}
	
	@Test
	public void testGetText() {
		assertEquals("texto", notification.getText());
	}
}

