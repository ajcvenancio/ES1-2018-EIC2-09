package teste;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import gui.MainClass;
import gui.MainWindows;

public class MainWindowsTest {

	private MainWindows mainWindows;
	
	@Before
	public void init() {
		mainWindows = new MainWindows(new MainClass());
	}
	
	@Test
	public void testInit() {
		mainWindows.init("Titulo", 1000, 800);
		assertEquals("Titulo inserido", "Titulo", mainWindows.getTitle());
		assertEquals("Largura inserida", 1000, mainWindows.getWidth());
		assertEquals("Altura inserida", 800, mainWindows.getHeight());
	}
	
	@Test
	public void testAddNotification() {
		mainWindows.addNotification("01-12-18", "Facebook", "AE", "Viagem a Madrid",null);
		mainWindows.addNotification("03-12-18", "Twitter", "AE", "Festa de carnaval",null);
		assertEquals("Adding 1 row to timeline", 2, mainWindows.getTimelineTable().getRowCount());
	}
	
	@Test
	public void testRemoveNotification() {
		mainWindows.addNotification("01-12-18", "Facebook", "AE", "Viagem a Madrid",null);
		mainWindows.addNotification("03-12-18", "Twitter", "AE", "Festa de carnaval",null);
		int row = mainWindows.getTimelineTable().getRowCount() - 1;
		mainWindows.removeNotification(row);
		assertEquals("Removing 1 row to timeline", 1, mainWindows.getTimelineTable().getRowCount() );
	}
	
	@Test
	public void testMaximizeWindows() {
		mainWindows.maximizeWindow();
		assertEquals("Está maximizado ", 6, mainWindows.getExtendedState());
	}
}

