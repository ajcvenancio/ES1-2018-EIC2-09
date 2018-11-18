package twitter;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
public final class TwitterMain  {
	//private List<Status> statuses;
	private TwitterFactory tf;
	private Twitter twitter;
	
	public TwitterMain(){
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
					Notification n = new Notification("twitter",s.getCreatedAt().toString(),s.getUser().getName(),"TWITTER NAO TEM SUBJECT",s.getText());
					n.setDate(changeDateFormat(n.getDate()));
					notifications.add(n);
				}
			}
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return notifications;
	}
	
	
	private static String changeDateFormat(String dateInString){
		SimpleDateFormat s = new SimpleDateFormat("dd/MMM/yyyy");
		SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
		String date = dateInString.split(" ")[2] + "-" + dateInString.split(" ")[1] + "-" + dateInString.split(" ")[5] +"  Time: "+ dateInString.split(" ")[3] ;
		return date;
	}

	public static void main(String[] args) {
		TwitterMain t=new TwitterMain();
		for(Notification s : t.getStatuses("ISCTE")){
			System.out.println();
			System.out.println(s.toString());
		}
	
			
	}
}
    
