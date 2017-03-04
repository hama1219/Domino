package Follow;

import DominoException.DominoException;
import mysql.MySQL;
import twitter4j.Friendship;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import userdata.UserData;

public class ForwardFollowCheck {
	
	/**
	 * ユーザーがスパム（肉じゃない方）かどうかチェック
	 * 判断基準：過去20件のツイートの内、15件以上URLが存在すればスパム
	 * @param userId
	 * @param twitter
	 * @return
	 */
	public  boolean isSpam(long userId, Twitter twitter) {
		boolean isSpam = false;
		/*
		 * チェックするユーザーのツイートを20件取得し、URLが存在する比率を検証する
		 */
		int spamcount = 0;
		try {
			ResponseList<Status> checkUsersStatus = twitter.getUserTimeline(userId);
			for (int i = 0; i < checkUsersStatus.size(); i++) {
				if (checkUsersStatus.get(i).getText().indexOf("https://t.co") != -1) {
					spamcount++;
				}
			}
			if (spamcount > 14) {
				isSpam = true;
			}
		} catch (TwitterException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return isSpam;
	}
	
	/**
	 * ユーザーがフォローしているかチェック
	 * @param twitter
	 * @param user_id
	 * @return
	 */
	public  boolean isFollowed(Twitter twitter,long user_id){
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
	
	/**
	 * ユーザーをDominoちゃんがフォローしているかチェック
	 * @param twitter
	 * @param user_id
	 * @return
	 */
	public  boolean isFollow(Twitter twitter, long user_id){
		ResponseList<Friendship> FollowCheck = null;
		try {
			FollowCheck =twitter.lookupFriendships(user_id);
			System.out.println("debugstep");
		} catch (TwitterException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return FollowCheck.get(0).isFollowing();
	}
	
	/**
	 * 過去にフォローしていたユーザーかチェック
	 * 判断基準：DBに値が残存しているか
	 * @param user_id
	 * @return
	 */
	public  boolean followedAgo(long user_id){
		boolean isFollowedAgo = false;
		try{
			@SuppressWarnings("unused")
			UserData userdata = new MySQL().getUser(user_id);
			isFollowedAgo = true;
		}catch(DominoException e){

		}
		return isFollowedAgo;
	}
}
