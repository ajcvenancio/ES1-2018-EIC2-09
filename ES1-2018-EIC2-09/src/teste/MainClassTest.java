package teste;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import channels.NotificationComparator;
import channels.email;
import gui.MainClass;

public class MainClassTest {

	private MainClass mainClass;
	private NotificationComparator comparator;
	
	@Before
	public void init() {
		mainClass = new MainClass();
		mainClass.setEmail(new email("testees1111@outlook.pt", "mailTESTE123"));
		comparator = new NotificationComparator();
	}

	@Test
	public void testGetFilter() {
		assertEquals('n', mainClass.getFilter());
	}
	
	@Test
	public void testGetTwitterUser() {
		assertNotNull(mainClass.getTwitterUser());
	}
	
	@Test
	public void testGetEmail() {
		assertNotNull(mainClass.getEmail());
	}
	
	@Test
	public void testFacebookUser() {
		assertNotNull(mainClass.getFacebookUser());
	}
	
	@Test
	public void testSetEmail() {
		mainClass.setEmail(new email("testees1111@outlook.pt", "mailTESTE123"));
		assertNotNull(mainClass.getEmail());
	}
	
	@Test
	public void testSetFilter() {
		mainClass.setFilter('a');
		assertEquals('a', mainClass.getFilter());
	}
	
	@Test
	public void testRefreshAll() {
		mainClass.setFilter('a');
		System.out.println(mainClass.refreshAll());
		assertEquals(0, mainClass.refreshAll().size());	
	}
	
	@Test
	public void testShortList() {
		mainClass.refreshAll().sort(comparator);
	}
	
}
