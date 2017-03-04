package profile;

import java.io.File;

import twitter4j.TwitterException;
import twitter4j.api.UsersResources;

public class UpdateProfileBanner {
	private UsersResources user;
	public UpdateProfileBanner(UsersResources user){
		this.user=user;
	}
	public void updateProfileBanner(File image){
		try {
			user.updateProfileBanner(image);
		} catch (TwitterException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
	
}
