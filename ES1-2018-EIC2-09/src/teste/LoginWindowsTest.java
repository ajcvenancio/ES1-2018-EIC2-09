package teste;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
