package channels;

import java.util.Comparator;

public class NotificationComparator implements Comparator<Notification	>{

	@Override
	public int compare(Notification n1, Notification n2) {
		return n2.getDateObject().compareTo(n1.getDateObject());
	}

}
