package teste;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import gui.MainWindows;

class MainWindowsTest {

	private MainWindows mw = new MainWindows();

	@Test
	void testAddNotification() {
		mw.addNotification("01-12-18", "Facebook", "AE", "Viagem a Madrid",null);
		mw.addNotification("03-12-18", "Twitter", "AE", "Festa de carnaval",null);
		assertEquals("Adding 1 row to timeline", 2, mw.getTimelineTable().getRowCount() );
	}
	
	@Test
	void testRemoveNotification() {
		mw.addNotification("01-12-18", "Facebook", "AE", "Viagem a Madrid",null);
		mw.addNotification("03-12-18", "Twitter", "AE", "Festa de carnaval",null);
		int row = mw.getTimelineTable().getRowCount() - 1;
		mw.removeNotification(row);
		assertEquals("Removing 1 row to timeline", 1, mw.getTimelineTable().getRowCount() );
	}
	
	@Test
	void testInit() {
		mw.init("Titulo", 1000, 800);
		assertEquals("Titulo inserido", "Titulo", mw.getTitle());
		assertEquals("Largura inserida", 1000, mw.getWidth());
		assertEquals("Altura inserida", 800, mw.getHeight());
	}

}
