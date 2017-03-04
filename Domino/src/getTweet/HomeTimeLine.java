package getTweet;

import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class HomeTimeLine {
	// 変数
	static ResponseList<Status> homeTimelineTweet;
	static long latestTweet;

	/**
	 * ホームタイムラインを取得してStatus型で返す。保持していろいろするのは呼び出し側なのでstaticで
	 * 
	 * @param twitter
	 */
	public static ResponseList<Status> getTimelineTweet(Twitter twitter) {
		// Paging pg = new Paging();
		// pg.setSinceId(latestTweet);
		try {
			homeTimelineTweet = twitter.getHomeTimeline();
		} catch (TwitterException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return homeTimelineTweet;
	}

	public long getLatestTweet() {
		return latestTweet;
	}

	public long setLatestTweet() {
		return latestTweet;
	}
}
