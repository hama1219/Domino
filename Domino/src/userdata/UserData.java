package userdata;

public class UserData {
	private final long user_id;
	private String screen_name;
	private String nickname;
	private int love_value;
	private String birthday;

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public UserData() {
		user_id = 0;
		this.screen_name="";
		this.nickname="";
		this.love_value=0;
	}

	public UserData(long user_id) {
		this.user_id = user_id;
		this.screen_name="";
		this.nickname="";
		this.love_value=0;
	}

	public String getScreen_name() {
		return screen_name;
	}

	public void setScreen_name(String screen_name) {
		this.screen_name = screen_name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getLove_value() {
		return love_value;
	}

	public void setLove_value(int love_value) {
		this.love_value = love_value;
	}

	public long getUser_id() {
		return user_id;
	}
}
