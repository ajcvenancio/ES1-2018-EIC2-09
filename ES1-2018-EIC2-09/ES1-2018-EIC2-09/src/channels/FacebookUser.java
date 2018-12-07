package channels;

/**
 * This java file contains all functionalities of a Facebook user
 * 
 * @author dgsos-iscteiul
 * @since 2018
 * @version 1.2
 */
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.text.Position;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.FacebookClient.AccessToken;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import com.restfb.types.GraphResponse;
import com.restfb.types.Post;
import com.restfb.types.User;

public class FacebookUser {

	private FacebookClient fbClient;
	private User me;

	public FacebookUser() {
		fbClient = new DefaultFacebookClient("EAAHQqdCwezIBAJChvIIe5VPLXJXG6q4nRLMnEvSHvbewzeN5wJQxKTZB0OZChxgVLDiZAYGOuABZCsCpKfDVUV0tuNBfpZCrQZAeLA1n2PSkR263sixRgiOMUI0xZAZC0wsC2liKH7MKeADnLX3HShqqAhULmfYH9o8ZD\r\n");
		me = fbClient.fetchObject("me", User.class);
	}

	/**
	 * This receive a number and return a List with the number of posts
	 * 
	 * @param number of posts to read
	 * @return list with a specified number of posts
	 * @author dgsos-iscteiul
	 * @since 2018
	 */
	public ArrayList<Notification> getUserLatestPosts(int number) {
		ArrayList<Notification> finalList = new ArrayList<>();
		Connection<Post> postFeed = fbClient.fetchConnection("301124660499343/feed",Post.class, Parameter.with("limit", number), Parameter.with("fields","message,created_time,id,permalink_url"));
		List<Post> postsList = postFeed.getData();
		for(int i = 0; i != postsList.size(); i++) {
			Post aPost = postsList.get(i);
			if (aPost != null) {
				finalList.add(new Notification("Facebook", aPost.getCreatedTime(), aPost.getName(), "", aPost.getMessage()));
			}
		}
		return finalList;
	}

	/**
	 * Return a List of last 24 hours
	 * 
	 * @return list with posts of last 24 hours
	 * @author dgsos-iscteiul
	 * @since 2018
	 */
	public ArrayList<Notification> getUserLatestDaysPosts(int days) {
		ArrayList<Notification> finalList = new ArrayList<Notification>();
		Connection<Post> postFeed = fbClient.fetchConnection("301124660499343/feed",Post.class, Parameter.with("since", (System.currentTimeMillis() / 1000L)-(86400*days)), Parameter.with("fields","message,created_time,id,permalink_url"));
		List<Post> potsList = postFeed.getData();
		for(int i = 0; i != potsList.size(); i++) {
			Post aPost = potsList.get(i);
			if (aPost != null) {
				finalList.add(new Notification("Facebook", aPost.getCreatedTime(), "", "", aPost.getMessage()));
			}
		}
		return finalList;
	}

	/**
	 * Receive a String on publish this string on a Facebook group
	 * 
	 * @return url of the new post
	 * @author dgsos-iscteiul
	 * @since 2018
	 * 
	 * 
	 */
	public void postOnGroup(String str) {
		fbClient.publish("301124660499343/feed", FacebookType.class, Parameter.with("message", str));
	}

}