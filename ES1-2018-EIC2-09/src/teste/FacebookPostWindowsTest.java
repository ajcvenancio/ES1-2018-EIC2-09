package teste;


import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import channels.FacebookUser;
import channels.TwitterUser;
import gui.LoginWindows;
import gui.MainClass;
import gui.MainWindows;
import gui.FacebookPostWindow;;

public class FacebookPostWindowsTest {

	private FacebookPostWindow tpw;

	@Before
	public void init() {
		tpw = new FacebookPostWindow(new FacebookUser());
	}
	
	@Test
	public void testOpen() {
		assertEquals("Titulo inserido", "Enviar Post", tpw.getTitle());
		assertEquals("Largura inserida", 700, tpw.getWidth());
		assertEquals("Altura inserida", 700, tpw.getHeight());
	}

}
