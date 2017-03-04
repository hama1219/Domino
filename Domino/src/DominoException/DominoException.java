package DominoException;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.Exception;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
public class DominoException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Propertyファイルのパス　このクラスを起点に指定
	private final String PROPERTY_FILE_PATH = DominoException.class.getResource("DominoException.class")
			.getPath().replaceAll("bin/DominoException/DominoException.class", "properties/ErrorMessage.properties");
	
	//Dominoの例外スロー（埋め込み文字無し）
	public DominoException(String messageId) {
		InputStream is;
		Properties properties = new Properties();
		try {
			 is = new FileInputStream(PROPERTY_FILE_PATH);
			 properties.load(is);
			 System.out.println(properties.getProperty(messageId));
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			System.out.println("プロパティファイルの取得に失敗しました");
			e.printStackTrace();
		}
		
	} 
	
	/**
	 * @例外メッセージ生成処理（埋め込み文字有りパターン）
	 * @メッセージIDと埋め込み文字を引数に、エラーメッセージ生成
	 * @Params: messegeID:メッセージID（Propertyファイルからメッセージを取ってくるキー）
	 * @Params: propertytext:埋め込み文字　複数あるパターンに対応して配列で渡す。
	 */
	public DominoException(String messageId,String[] propertytext) {
		//propertyファイルのストリーム格納
		InputStream is;
		//property操作クラス
		Properties properties = new Properties();
		try {
			//propertiesファイルのストリーム実体化
			 is = new FileInputStream(PROPERTY_FILE_PATH);
			 //propertyクラスにプロパティファイルセット
			 properties.load(is);
			 //エラーメッセージを、メッセージIDと埋め込み文字を使って生成　messageid:メッセージID（キー）　propertytext:埋め込み文字（配列）
			 String errorMessage =  new MessageFormat(properties.getProperty(messageId)).format(propertytext);
			 //エラーメッセージを標準出力へ出力（そのうちログ書き出しとかにする）
			 System.out.println(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date())+" "+errorMessage);
		//propertiesファイルの読み込みで何か置きた時
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			System.out.println(DateFormat.getTimeInstance(DateFormat.MEDIUM).toString()+" プロパティファイルの取得に失敗しました");	
			e.printStackTrace();
		}
	} 
	public DominoException(Exception e){
		System.out.println("予想外の例外発生");
		e.printStackTrace();
	}
	


}
