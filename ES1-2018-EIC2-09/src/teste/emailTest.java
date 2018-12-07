package teste;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import channels.TwitterUser;
import channels.email;
import gui.MainClass;
import gui.MainWindows;

public class emailTest {

	private email email;

	@Before
	public void init() {
		email = new email("testees1111@outlook.pt", "mailTESTE123");
	}
	
	@Test
	public void testGetUsername() {
		assertEquals("testees1111@outlook.pt", email.getUsername());
	}
	
	@Test
	public void testGetPassword() {
		assertEquals("mailTESTE123", email.getPassword());
	}
	
	@Test
	public void testGetStore() {
		assertNotNull(email.getStore());
	}
	
}
