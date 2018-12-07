package teste;


import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import channels.TwitterUser;
import gui.LoginWindows;
import gui.MainClass;
import gui.MainWindows;
import gui.TwitterPostWindow;

public class TwitterPostWindowsTest {

	private TwitterPostWindow tpw;

	@Before
	public void init() {
		tpw = new TwitterPostWindow(new TwitterUser());
	}
	
	@Test
	public void testOpen() {
		assertEquals("Titulo inserido", "Enviar Tweet", tpw.getTitle());
		assertEquals("Largura inserida", 700, tpw.getWidth());
		assertEquals("Altura inserida", 700, tpw.getHeight());
	}

}
