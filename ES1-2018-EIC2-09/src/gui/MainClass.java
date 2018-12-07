package gui;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import channels.FacebookUser;
import channels.Notification;
import channels.NotificationComparator;
import channels.TwitterUser;

public class MainClass {
	private MainWindows mainWindows;
	private TwitterUser t;
	private FacebookUser fb;
	private char filter; // filter : n - no filter / f - facebook / e - email / t - twitter
	private ArrayList<Notification> ns;
	
	public MainClass(){
		this.filter='n';
		mainWindows = new MainWindows(this);
		ns=new ArrayList<Notification>();
		t=new TwitterUser();;
		//fb=new FacebookUser("EAAHQqdCwezIBAESeUi86ruT1qjga1Htn9lqpCyBTorusTmU1RiH36IHC7cOxZAlYxmj4c1cTx0YdwDy8izJZCYJJLQAH5IcQ7PDMZCnEbizjYy2zwS73cfeyTbfQtvJXuh5tOv3kZCTay2HZAUCryvIKk5CYxolUxnC59pOhZCoq4N6JHCTBnpAvFxSblXURwZD");
		mainWindows.addNotificationList(refreshAll());
	}
	
	public ArrayList<Notification> refreshAll(){
		ns.clear();
		if(filter == 'n'|| filter == 't')
			ns.addAll(t.getStatuses("ISCTE"));
		if(filter == 'n'|| filter == 'f')
			//ns.addAll(fb.getUserLatestDaysPosts(5));
		if(filter == 'n' || filter == 'e')
			//meter addALL do EMAIL
		ns.sort(new NotificationComparator());
		return ns;
	}
	
	public void setFilter(char c){
		this.filter=c;
	}
	

	public static void main(String[] args) {
		new MainClass();

	}
}
