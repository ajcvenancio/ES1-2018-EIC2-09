package channels;

/**
 * This java file contains all functionalities of a Facebook user
 * 
 * @author dgsos-iscteiul
 * @since 2018
 * @version 1.1
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

	public FacebookUser(String accessTokenString) {
		fbClient = new DefaultFacebookClient(accessTokenString);
		me = fbClient.fetchObject("me", User.class);
	}

	/**
	 * This method return a String with a name of user
	 * 
	 * @return the name of FacebookUser
	 * @author dgsos-iscteiul
	 * @since 2018
	 */
	public String getUserName() {
		return me.getName();
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
//				System.out.println("  >>  "+aPost);
				finalList.add(new Notification("Facebook", aPost.getCreatedTime(), aPost.getName(), "", aPost.getMessage(), aPost.getPermalinkUrl()));
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
	public ArrayList<Notification> getUserLatest24hPosts() {
		ArrayList<Notification> finalList = new ArrayList<>();
		Connection<Post> postFeed = fbClient.fetchConnection("301124660499343/feed",Post.class, Parameter.with("since", (System.currentTimeMillis() / 1000L)-86400), Parameter.with("fields","message,created_time,id,permalink_url"));
		List<Post> potsList = postFeed.getData();
		long DAY = 24 * 60 * 60 * 1000;
		for(int i = 0; i != potsList.size(); i++) {
			Post aPost = potsList.get(i);
			if (aPost != null) {
				if(aPost.getCreatedTime().getTime() > System.currentTimeMillis() - DAY) {
					finalList.add(new Notification("Facebook", aPost.getCreatedTime(), aPost.getName(), "", aPost.getMessage(), aPost.getPermalinkUrl()));
				}
				else {
					break;
				}
			}
		}
		return finalList;
	}

	/**
	 * Receive a String on publish this string on a Facebook group,
	 * 		return the url of this new publication
	 * 
	 * @param a string to post
	 * @return url of the new post
	 * @author dgsos-iscteiul
	 * @since 2018
	 */
	public String postOnGroup(String str) {
		FacebookType response = fbClient.publish("301124660499343/feed", FacebookType.class, Parameter.with("message", str));
		return "fb.com/" + response.getId();
	}

	public static void main(String[] args) {
		FacebookUser fbuser = new FacebookUser("EAAHQqdCwezIBAJeUhTyQg5TORTFQspQsah8nxkSrmE4XBrTE2NYLpGasVodRYZCdB7VAd77hZANHmbhGPVhQRamw0PswYoExdAi17kUUWZCZA817WZBzJyUVyZApNPv3utZBhUhIm591YN2UyUl3uilrqfqijzJZC9IVP3NjCxRz7w0jWioJZCxCpS9ICuZBUxJuBdMjZAy3RhyCAZDZD");
		System.out.println(fbuser.getUserLatestPosts(1));
		System.out.println(fbuser.getUserLatest24hPosts());
//		System.out.println(fbuser.getUserName());
//		System.out.println(fbuser.postOnGroup("By eclipse"));
	}

}