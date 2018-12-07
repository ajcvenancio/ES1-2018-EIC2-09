package teste;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.*;

import channels.FacebookUser;
import channels.Notification;

public class FacebookUserTest {
	private FacebookUser facebookUser;
	
	@Before
	public void init() {
		facebookUser = new FacebookUser();
	}
	
	@Test
	public void testGetUserLatestPosts() {
		assertEquals("Confirmar se o tamanho da lista é 5", 5, facebookUser.getUserLatestPosts(5).size());
	}
	@Test
	public void testGetUserLatestDaysPosts1() {
		assertTrue("Confirmar se todos os posts são das últimas 24 horas", checkPostsInList24Hours(facebookUser.getUserLatestDaysPosts(1)));
	}
	
	@Test
	public void testPostOnGroup() {
		String strTest = "JUnit Test";
		facebookUser.postOnGroup(strTest);
		assertTrue("Confrimar se post é publicado", facebookUser.getUserLatestPosts(1).get(0).getText().equals(strTest));
	}
	
	public boolean checkPostsInList24Hours(ArrayList<Notification> list24) {
		long DAY = 24 * 60 * 60 * 1000;
		for(Notification n : list24) {
			if(n.getDateObject().getTime() < System.currentTimeMillis() - DAY) { 
				return false;
			}
		}
		return true;
	}
}
