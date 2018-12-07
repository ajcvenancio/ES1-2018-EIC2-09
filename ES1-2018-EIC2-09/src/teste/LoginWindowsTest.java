package teste;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import channels.Notification;
import gui.LoginWindows;
import gui.MainClass;
import gui.MainWindows;

public class LoginWindowsTest {

	private LoginWindows loginWindow;

	@Before
	public void Init() {
		loginWindow = new LoginWindows();
	}
	
	@Test
	public void testInit() {
		loginWindow.init("titulo", 700, 700);
		assertEquals("Titulo inserido", "titulo", loginWindow.getTitle());
		assertEquals("Largura inserida", 700, loginWindow.getWidth());
		assertEquals("Altura inserida", 700, loginWindow.getHeight());
	}

}
