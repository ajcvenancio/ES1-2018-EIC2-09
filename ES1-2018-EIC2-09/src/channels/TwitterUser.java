package channels;


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
	
	public void Post(String str){
		try {
			twitter.updateStatus(str);
		} catch (TwitterException e) {
			System.out.println("Unable to update Status");
		}
	}
	
}
    