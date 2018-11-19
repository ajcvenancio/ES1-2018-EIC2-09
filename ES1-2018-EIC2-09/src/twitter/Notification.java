package twitter;

/**
 * This java file contains a class that is used to combine all the different types of posts from each branch into one object type.
 * Used by all branches.
 * @author amsgn-iscteiul
 * @since 2018
 * @version 1.0
 */

public class Notification {
	
	private String channel,date,source,subject,text;
	
	/**
	 * The constructor method initializes all the variables
	 * 
	 * @author amsgn-iscteiul
	 * */
	public Notification(String channel,String date, String source, String subject, String text){
		this.channel=channel;
		this.date=date;
		this.source=source;
		this.subject=subject;
	}
	
	/**
	 * This method returns the channel.
	 * 
	 * @author amsgn-iscteiul
	 * */
	public String getChannel() {
		return channel;
	}
	/**
	 * This method returns the date.
	 * 
	 * @author amsgn-iscteiul
	 * */
	public String getDate() {
		return date;
	}
	/**
	 * This method sets the date.
	 * 
	 * @author amsgn-iscteiul
	 * */
	public void setDate(String date){
		this.date=date;
	}
	/**
	 * This method returns the source.
	 * 
	 * @author amsgn-iscteiul
	 * */
	public String getSource() {
		return source;
	}
	/**
	 * This method returns the subject.
	 * 
	 * @author amsgn-iscteiul
	 * */
	public String getSubject() {
		return subject;
	}
	/**
	 * This method returns the Text.
	 * 
	 * @author amsgn-iscteiul
	 * */
	public String getText() {
		return text;
	}
	/**
	 * This method uses translates this object into a string.
	 * 
	 * @author amsgn-iscteiul
	 * */
	public String toString(){
		return " Channel - "+this.channel+"\n Date-"+this.date+"\n Source-"+this.source+"\n Subject-"+this.subject;
	}
	
	

}
