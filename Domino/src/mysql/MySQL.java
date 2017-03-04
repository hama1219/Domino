package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

import DominoException.DominoException;
import userdata.UserData;

public class MySQL {
	Object mysql;
	Connection conn;

	public MySQL() {
		// TODO 自動生成されたコンストラクター・スタブ
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost/domino_test?autoReconnect=true&useSSL=false&characterEncoding=UTF-8&serverTimezone=Japan", "root", "myon");
			System.out.println("いけたで");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * ユーザー情報作成
	 * @param user_id
	 * @param user_name
	 * @throws DominoException
	 */
	public void createUserData(long user_id, String user_name) throws DominoException {
		try {
			conn.setAutoCommit(false);
			Statement stmt = conn.createStatement();
			String sql = "insert into domino_chan (user_id,user_name,nickname) values ('" + user_id + "','" + user_name
					+ "','noname')";
			stmt.executeUpdate(sql);
			conn.commit();
		} catch (SQLIntegrityConstraintViolationException e) {
			// TODO 自動生成された catch ブロック
			try {
				conn.rollback();
				throw new DominoException("008", 
						new String[]{Long.toString(user_id)});
			} catch (SQLException e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
				throw new DominoException(e1);
			}
		}catch(SQLException e){
			try{
				conn.rollback();
				throw new DominoException(e);
			}catch(SQLException e2){
			throw new DominoException(e2);
			}
		}
	}
	/**
	 * テーブルからユーザを取得
	 * @param user_id
	 * @return UserData
	 * @throws DominoException
	 */
	public UserData getUser(long user_id) throws DominoException{
		//ユーザ情報
		UserData userdata = new UserData(user_id);
		int cnt=0;
		String sql = "SELECT * FROM domino_test.domino_chan where user_id = '"+Long.toString(user_id)+"'";
		try {
			//sql接続
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			//取得したレコードだけUserdataにセット（１レコードしか引っかからない想定のため
			//２レコード以上セットしたらDominoExceptionを投げる
			//(もっともPKで取得してるからそもそも複数引っかかった時点でおかしいけど）
			while(rs.next()){
				cnt++;
				//二件以上取得した時点でアウト
				if(cnt > 1)
					throw new DominoException("002",new String[]{Long.toString(user_id)});
				userdata.setScreen_name(rs.getString("user_name"));
				userdata.setNickname(rs.getString("nickname"));
				userdata.setLove_value(Integer.parseInt(rs.getString("love_value")));
			}
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			throw new DominoException(e);
		}
		if(cnt == 0)
			throw new DominoException("001",new String[]{Long.toString(user_id)});
		System.out.println(sql);
		return userdata;
	}
	/**
	 * 好感度更新
	 * @param user_id
	 * @param love_value
	 * @return
	 * @throws DominoException
	 */
	public UserData updateLovevalue(long user_id,int love_value) throws DominoException{
		//ユーザ情報
		UserData userdata = new UserData();
		String updateUserDatasql = "UPDATE  domino_test.domino_chan  set love_value='"+love_value
				+ "' where user_id = '"+Long.toString(user_id)+"'";
		String getUserDatasql = "SELECT * FROM domino_test.domino_chan where user_id = '"+Long.toString(user_id)+"'";
		try {
			//sql接続
			Statement stmt = conn.createStatement();
			conn.setAutoCommit(false);
			int updatedvalue = stmt.executeUpdate(updateUserDatasql);
			if(updatedvalue > 1){
				conn.rollback();
				throw new DominoException("006",new String[]{Long.toString(user_id)});
			}else if(updatedvalue == 0){
				conn.rollback();
				throw new DominoException("007",new String[]{Long.toString(user_id)});
			}else if(updatedvalue==1){
				conn.commit();
			}
		}catch (SQLException e) {
			// TODO: handle exception
			System.out.println("user情報の更新中に例外が発生しました。（SQLException");
			try {
				conn.rollback();
				throw new DominoException(e);
			} catch (SQLException e1) {
				// TODO 自動生成された catch ブロック
				throw new DominoException(e1);
			}
		}
		try{
			Statement stmt = conn.createStatement();
			ResultSet rs2 = stmt.executeQuery(getUserDatasql);
			while(rs2.next()){
				//
				userdata.setScreen_name(rs2.getString("user_name"));
				userdata.setNickname(rs2.getString("nickname"));
			}
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			System.out.println("更新後のユーザ情報の取得じに例外が発生しました。");
			throw new DominoException(e);
		}
		return userdata;
	}

}
