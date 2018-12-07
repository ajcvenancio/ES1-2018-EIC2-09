package gui;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import channels.FacebookUser;
import channels.Notification;
import channels.NotificationComparator;
import channels.TwitterUser;
import channels.email;
import config.xmlConfig; 

public class MainClass {
	private MainWindows mainWindows;
	private TwitterUser t;
	private FacebookUser fb;
	private email mail;
	private char filter; // filter : n - no filter / f - facebook / e - email / t - twitter
	private ArrayList<Notification> ns;
	private boolean offline;
	
	public MainClass(){
		this.filter='n';
//		mainWindows = new MainWindows(this);
		ns=new ArrayList<Notification>();
//		t=new TwitterUser();
//		fb=new FacebookUser();
		
	}
	
	
	public void initializeSocialNetwork() {
		this.t=new TwitterUser();
		this.fb=new FacebookUser();
	}
	
	public void setOffline(boolean state) {
		this.offline=state;
	}
	public void startMainWindow(MainClass mc) {
		System.out.println(getOffline());
		this.mainWindows = new MainWindows(mc);
		
		if(offline)
			mainWindows.addNotificationList(xmlConfig.getPosts());
		else {
			initializeSocialNetwork();
			mainWindows.addNotificationList(refreshAll());
		}
	}
	public void setEmail(email mail) {
		this.mail=mail;
	}
	
	public TwitterUser getTwitterUser(){
		return this.t;
	}
	public email getEmail() {
		return this.mail;
	}
	public FacebookUser getFacebookUser(){
		return this.fb;
	}
	
	public ArrayList<Notification> refreshAll(){
		ns.clear();
		if(filter == 'n'|| filter == 't')
			ns.addAll(t.getStatuses("ISCTE"));
		if(filter == 'n'|| filter == 'f')
			ns.addAll(fb.getUserLatestDaysPosts(5));
		if(filter == 'n' || filter == 'e')
			ns.addAll(mail.searchEmail());
		ns.sort(new NotificationComparator());
		xmlConfig.removeAllPosts();
		xmlConfig.addPost(ns);
		return ns;
	}
	
	public ArrayList<Notification> searchOnNotifications(String str) {
		ArrayList<Notification> list = new ArrayList<>();
		if(ns != null) {
			for(Notification n : ns) {
				if(n.getText().contains(str)) {
					list.add(n);
				}
			}
		}
		return list;
	}
	
	public void setFilter(char c){
		this.filter=c;
	}
	
	public char getFilter() {
		return filter;
	}
	
	public static void main(String[] args) {
		new MainClass();
	}

	public boolean getOffline() {
		return offline;
	}
}
