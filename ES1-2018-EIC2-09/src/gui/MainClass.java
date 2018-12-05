package gui;

import java.util.ArrayList;
import java.util.List;

import channels.FacebookUser;
import channels.Notification;

import channels.TwitterUser;

public class MainClass {
	public TwitterUser t;
	public FacebookUser fb;
	public List<Notification> ns;
	
	public MainClass(){
		ns=new ArrayList<Notification>();
		t=new TwitterUser();;
		//FacebookUser fb=new FacebookUser("EAAHQqdCwezIBAG50YlgZCnhrCxqqBe7J3jkIAfsdsybUltqcPUxFaBZB7KruIsZCQXorxRTLm0eZBZB4f59HntlmDc24PJY31ORchQZBdrpDyQ4gIr0sZAuCpdJANNgg5VHUTN7I0cBXHj8olYJcksLZBeoUZAxIW8UACntwNCeRGUmpVl6TFimSDTNFA7usaZBegZD");
		
	}
	
	public List<Notification> refreshAll(){
		ns.clear();
		ns.addAll(t.getStatuses("ISCTE"));
		//ns.addAll(fb.getUserLatest24hPosts());
		return ns;
		
	}
}
