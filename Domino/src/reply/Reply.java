package reply;

import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class Reply {
	public static String reply(Twitter twitter, Status st, String replyText) {
		String text = "@" + st.getUser().getScreenName() + replyText;
		StatusUpdate reply = new StatusUpdate(text);
		reply.setInReplyToStatusId(st.getId());
		try {
			twitter.updateStatus(reply);
		} catch (TwitterException e) {
			// TODO 自動生成された catch ブロック
			if (e.getErrorMessage().equals("Status is a duplicate.")) {
				return "Status is a duplicate.";
			} else {
				System.out.println(e.getStatusCode());
				System.out.println(e.getErrorMessage());
				return "Other probrem";
			}
		}
		return "OKEND";
	}
}
