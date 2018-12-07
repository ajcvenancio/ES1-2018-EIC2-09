package channels;


import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;
public final class TwitterUser  {
	//private List<Status> statuses;
	private TwitterFactory tf;
	private Twitter twitter;
	
	public TwitterUser(){
        try {
        	ConfigurationBuilder cb = new ConfigurationBuilder();
        	cb.setDebugEnabled(true)
        	  .setOAuthConsumerKey("FbOzYIk30OzxxdCXvYaoCd4SG")
        	  .setOAuthConsumerSecret("aQhHY0OdETrIcNHxyeBnK5fNY4EtcHZE1y2Yd53NtxZp1Z5eeN")
        	  .setOAuthAccessToken("1050702778362933249-OAr6uVkz4IQDY5GVOWsP3rYqLQz8gm")
        	  .setOAuthAccessTokenSecret("orJTrGTBhYOrIqBydJwvIAaY7fjR0x5FaSHYB8DbXn8EI");
        	tf = new TwitterFactory(cb.build());
        	twitter = tf.getInstance();        		
        } catch (Exception e) { System.out.println(e.getMessage()); }
     }
	
	public List<Notification> getStatuses(String user){
		List<Notification> notifications= new ArrayList<Notification>();
		try {
			for(Status s: twitter.getHomeTimeline()){
				if (s.getUser().getName() != null && s.getUser().getName().contains(user)) {
					Notification n = new Notification("twitter",(Date) s.getCreatedAt(),s.getUser().getName()," - ",s.getText());
					System.out.println(s.getURLEntities());
					notifications.add(n);
				}
			}
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return notifications;
	}
	
	
	private String changeDateFormat(String dateInString){
		SimpleDateFormat s = new SimpleDateFormat("dd/MMM/yyyy");
		SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
		String date = dateInString.split(" ")[2] + "-" + dateInString.split(" ")[1] + "-" + dateInString.split(" ")[5] +"  Time: "+ dateInString.split(" ")[3] ;
		return date;
	}
	

	public static void main(String[] args) {
		TwitterUser t=new TwitterUser();
		for(Notification s : t.getStatuses("ISCTE")){
			System.out.println();
			System.out.println(s.toString());
		}
	}
}
    