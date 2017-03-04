import OAuth.OAuth;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class Tweet {
	// OAuth oa;
	public static void main(String[] args) {
		Twitter twitter = new TwitterFactory().getInstance();

		// 生成したTwitterインスタンスを使ってOAuth認証
		OAuth.OAuthConnect(twitter);

		// つぶやく
		try {
			System.out.println(twitter.updateStatus("みょんみょんみゅ~~~~~~~~~~~~~").getText());

		} catch (TwitterException e) {
			System.out.println("アカン");
			e.printStackTrace();
		}
	}
}
