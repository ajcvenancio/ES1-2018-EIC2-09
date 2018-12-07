package teste;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import gui.LoginWindows;

class LoginWindowsTest {

	private LoginWindows lw = new LoginWindows();

	@Test
	void testInit() {
		lw.init("titulo", 700, 700);
		assertEquals("Titulo inserido", "titulo", lw.getTitle());
		assertEquals("Largura inserida", 700, lw.getWidth());
		assertEquals("Altura inserida", 700, lw.getHeight());
	}

}
