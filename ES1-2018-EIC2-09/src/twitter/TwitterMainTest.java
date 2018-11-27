package twitter;

import static org.junit.Assert.*;

import java.awt.List;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class TwitterMainTest {
	TwitterMain t;
	@Before
	public void init() {
		t=new TwitterMain();
	}
	
	@Test
	public void test(){
		ArrayList<Notification> l=(ArrayList<Notification>) t.getStatuses("ISCTE-IUL");
		for(Notification n: l)
		assertEquals("All must be from ISCTE","ISCTE-IUL",n.getSource());
	}
	
	

}
