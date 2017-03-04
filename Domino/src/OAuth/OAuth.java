package OAuth;

import twitter4j.Twitter;
import twitter4j.auth.AccessToken;

public class OAuth {
	@SuppressWarnings("unused")
	private final static String user_name = "Domino_Chan";
	// APPのコンシューマキーおよびアクセストークン
	private static String access_token = "956582882-7MO8ouLkicMkNBpUM3kBSf119t1rkZo0EbRKJ6aC";
	private static String access_token_secret = "2h2q0zqvAUrCnwzPsRQEI8dFe7ZlWbm13xjU9sZzA";
	private static String consumer = "gVpoT7T1OsfIhC3FKW8A";
	private static String consumerSecret = "CDVQAlDuUbs7CtTBimkXUXDU1sks1AoqbpgdgWUJ6Xs";
	static AccessToken at;

	//OAuth認証
	public static void OAuthConnect(Twitter twitter) {
		// TODO 自動生成されたコンストラクター・スタブ
		at = new AccessToken(access_token, access_token_secret);
		twitter.setOAuthConsumer(consumer, consumerSecret);
		twitter.setOAuthAccessToken(at);

	}
}
