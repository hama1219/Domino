package reply;

public abstract class AbstractReply {
	
	//Factoryメソッドでリプライクラスを生成
	public static AbstractReply makeReplyFactory(String replyTexytype){
		return null;
	}
	
	//リプライ生成、具体的な生成は子供に任す
	abstract String makeReply();

}
