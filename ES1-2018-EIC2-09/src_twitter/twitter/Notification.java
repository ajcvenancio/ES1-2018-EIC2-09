package twitter;

public class Notification {
	String channel,date,source,subject;
	public Notification(String channel,String date, String source, String subject){
		this.channel=channel;
		this.date=date;
		this.source=source;
		this.subject=subject;
	}
	public String toString(){
		return " Channel - "+this.channel+"\n Date-"+this.date+"\n Source-"+this.source+"\n Subject-"+this.subject;
	}
}
