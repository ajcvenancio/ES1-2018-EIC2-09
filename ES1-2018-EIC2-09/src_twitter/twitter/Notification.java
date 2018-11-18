package twitter;

public class Notification {
	
	private String channel,date,source,subject,text;
	
	public Notification(String channel,String date, String source, String subject, String text){
		this.channel=channel;
		this.date=date;
		this.source=source;
		this.subject=subject;
	}
	public String getChannel() {
		return channel;
	}
	public String getDate() {
		return date;
	}
	public String getSource() {
		return source;
	}
	public String getSubject() {
		return subject;
	}
	public String getText() {
		return text;
	}
	public String toString(){
		return " Channel - "+this.channel+"\n Date-"+this.date+"\n Source-"+this.source+"\n Subject-"+this.subject;
	}
	
	

}
