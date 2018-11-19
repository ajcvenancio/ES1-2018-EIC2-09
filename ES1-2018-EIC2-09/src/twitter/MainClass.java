package twitter;

import java.util.List;

import twitter.Notification;

import twitter.TwitterMain;

public class MainClass {
	public static void main(String[] args) {
		MainWindows mw=new MainWindows();
		TwitterMain t=new TwitterMain();
		add(t.getStatuses("ISCTE"),mw);
	}
	public static void add(List<Notification> list,MainWindows mw){
		for(Notification n: list)
			mw.addNotification(n.getDate(), n.getChannel(), n.getSource(), n.getSubject());
	}
}
