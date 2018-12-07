package teste;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;

import channels.FacebookUser;
import channels.Notification;
import channels.TwitterUser;

public class TwitterUserTest {
	
	private TwitterUser twitterUser;
	
	@Before
	public void init() {
		twitterUser = new TwitterUser();
	}
	
	@Test
	public void testUserLatestDaysPosts1() {
		assertTrue("Confirmar se todos os posts são das últimas 24 horas", checkPostsInList24Hours(twitterUser.getStatuses("")));
}
	@Test
	public void testPostOnGroup() {
		String strTest = "JUnit Test";
		twitterUser.Post(strTest);
		assertTrue("Confrimar se tweet é publicado", twitterUser.getStatuses("").get(0).getText().equals(strTest));
	}
	
	public boolean checkPostsInList24Hours(List<Notification> list24) {
		long DAY = 24 * 60 * 60 * 1000;
		for(Notification n : list24) {
			if(n.getDateObject().getTime() < System.currentTimeMillis() - DAY) { 
				return false;
			}
		}
		return true;
	}
}
