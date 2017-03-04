import DominoException.DominoException;
import mysql.MySQL;
import userdata.UserData;

public class TestMySQL {
	public static void main(String[] args) {
		MySQL mySql = new MySQL();
		try{
			mySql.getUser(19191919l);
		}catch(DominoException e){
			System.out.println("うまいこと投げれた");
			e.printStackTrace();
		}
		try{
			UserData user =  mySql.getUser(1212121213l);
			System.out.println(user.getLove_value());
			System.out.println(user.getUser_id());
			System.out.println(user.getNickname());
		}catch(DominoException e){
			System.out.println("なんでやねん");
		}
		try{
			 
			mySql.createUserData(1111111111111111111l, "テスト");
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

	}
}
