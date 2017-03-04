package Follow;

import DominoException.DominoException;
import mysql.MySQL;
import twitter4j.Friendship;
import twitter4j.ResponseList;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class FollowUser {
	//リターンコード定義： 0:フォローする。1:相手がフォローしてない。2:フォロー済み
	public static int follow(long user_id,Twitter twitter){
		if(isFollow(twitter, user_id)){
			return 0;
		}
		return 8;
	}
	
	public static boolean isFollow(Twitter twitter,long user_id){
		ResponseList<Friendship> FollowCheck = null;
		try {
			FollowCheck =twitter.lookupFriendships(user_id);
			System.out.println("debugstep");
		} catch (TwitterException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return FollowCheck.get(0).isFollowedBy();
	}
	
}
