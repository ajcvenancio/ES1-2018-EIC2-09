import java.util.List;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.FacebookClient.AccessToken;
import com.restfb.types.Post;
import com.restfb.types.User;

public class facebook_API_test {

	public static void main(String[] args) {
		String accessToken = "EAAHQqdCwezIBACaESFFsneWDsMSGnFq7LZB4kXDJake2hndZBu0A5Ce7aPnpNIEVMSFIHET2RVKF7ssRrpz8DfztB7ZAEVsYEKlsLO6Vz76ChBmWLUgeO2aT0H8BnZBSFTOUZAgOZAuvoEl0bq44ptpMrgoocOmGMDaYLYzU7izl0WAhQH36LCBcMhaSSto6vBp5I3pwwz3gZDZD";
		
		FacebookClient fbClient = new DefaultFacebookClient(accessToken);

		User me = fbClient.fetchObject("me", User.class);
		
		System.out.println("Facebook:");
		System.out.println("Id: " + me.getId());
		System.out.println("Name: " + me.getName());
		
		
		AccessToken extendedAccessToken = fbClient.obtainExtendedAccessToken("510902745987890","6017355bb03e3543c5ad4689a59bed7c");
		System.out.println("ExtendedAccessToken: "+extendedAccessToken.getAccessToken());
		System.out.println("Expires: " + extendedAccessToken.getExpires());
		
		
		Connection<Post> result = fbClient.fetchConnection("me/feed",Post.class);
		System.out.println("\nPosts:");
		int counter5 = 0;
		int counterTotal = 0;
		for (List<Post> page : result) {
			for (Post aPost : page) {
				// Filters only posts that contain the word "Inform"
				if (aPost.getMessage() != null) {
					System.out.println("---- Post "+ counter5 + " ----");
					System.out.println("Id: "+"fb.com/"+aPost.getId());
					System.out.println("Message: "+aPost.getMessage());
					System.out.println("Created: "+aPost.getCreatedTime());
					counter5++;
				}
				counterTotal++;
			}
		}
		System.out.println("-------------\nNº of Results: " + counter5+"/"+counterTotal);	
	}

}
