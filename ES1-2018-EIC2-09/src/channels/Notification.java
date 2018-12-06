package channels;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Notification {
	
	private String channel,source,subject,text;
	private Date date;
	DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

	public Notification(String channel,Date date, String source, String subject, String text){
		this.channel=channel;
		this.date=date;
		this.source=source;
		this.subject=subject;
		this.text = text;
	}
	public String getChannel() {
		return channel;
	}
	public Date getDateObject() {
		return date;
	}
	public String getDate() {
		String testDateString = dateFormat.format(date);
		return testDateString;
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
		return "\n<"+getDate()+" | "+channel+" | "+source+" | "+subject+" | "+text+">";
	}
}
