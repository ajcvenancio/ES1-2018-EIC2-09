package teste;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import gui.MainClass;

public class MainClassTest {

	private MainClass mainClass;
	
	@Before
	public void init() {
		mainClass = new MainClass();
	}

	@Test
	public void testGetFilter() {
		assertEquals('n', mainClass.getFilter());
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
	
}
