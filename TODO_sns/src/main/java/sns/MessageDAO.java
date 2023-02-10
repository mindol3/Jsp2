package sns;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;
import util.DBManager;

@Log4j2
public class MessageDAO {
	/*
	 신규 메시지 등록
	 @param msg
	 @return
	 * */
	public boolean newMsg(Message msg) throws Exception {
		int flag = 0;
		String sql = "insert into s_message(uid, msg, date) value(?, ?, now())";
		
		@Cleanup Connection connection = DBManager.INSTANCE.getConnection();
       @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);

       preparedStatement.setString(1, msg.getUid());
       preparedStatement.setString(2, msg.getMsg());

       flag = preparedStatement.executeUpdate();
       return flag == 1;
	}
	
	/*
	 메시지 삭제
	 @param mid
	 @return
	 * */
	public boolean delMsg(int mid) throws Exception {
		int flag = 0;
		String sql = "delete from s_message where mid = ?";
		
		@Cleanup Connection connection = DBManager.INSTANCE.getConnection();
       @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
       
       preparedStatement.setInt(1, mid);
       
       flag = preparedStatement.executeUpdate();
       return flag == 1;
	}
	
	/*
	 게시글에 대한 답글 등록, 원 게시물에 대한 mid 필요
	 * */
	public boolean newReply(Reply reply) throws Exception {
		int flag = 0;
		String sql = "insert into s_reply(mid,uid,rmsg,date) values(?,?,?,now())";
		
		@Cleanup Connection connection = DBManager.INSTANCE.getConnection();
       @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
       
       preparedStatement.setInt(1, reply.getMid());
       preparedStatement.setString(2, reply.getUid());
       preparedStatement.setString(3, reply.getRmsg());
       
       flag = preparedStatement.executeUpdate();
       return flag == 1;
	}
	
	/*
	 답글 삭제
	 @param rid
	 @return
	 * */
	public boolean delReply(int rid) throws Exception {
		int flag = 0;
		String sql ="delete from s_reply where rid = ?";
		
		@Cleanup Connection connection = DBManager.INSTANCE.getConnection();
       @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
       
       preparedStatement.setInt(1, rid);
       
       flag = preparedStatement.executeUpdate();
       return flag == 1;
	}
	
	public ArrayList<MessageSet> getAll(int cnt, String suid) throws Exception {
		ArrayList<MessageSet> datas = new ArrayList<MessageSet>();
		String sql;
		
		@Cleanup Connection connection = DBManager.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = null;
        // 전체 게시물인 경우
        if((suid == null) || (suid.equals(""))) {
        	sql = "select * from s_message order by date desc limit 0,?";
        	preparedStatement = connection.prepareStatement(sql);
        	preparedStatement.setInt(1, cnt);
        }
        // 특정 회원 게시물 only 인 경우
        else {
        	sql = "select * from s_message where uid = ? order by date desc limit 0,?";
        	preparedStatement = connection.prepareStatement(sql);
        	preparedStatement.setString(1, suid);
        	preparedStatement.setInt(2, cnt);
        }
        
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()) {
        	MessageSet messageSet = new MessageSet();
        	Message message = new Message();
        	ArrayList<Reply> replies = new ArrayList<>();
        	
        	message.setMid(resultSet.getInt("mid"));
        	message.setMsg(resultSet.getString("msg"));
        	message.setDate(resultSet.getDate("date") + "/" + resultSet.getTime("date"));
			message.setFavcount(resultSet.getInt("favcount"));
			message.setUid(resultSet.getString("uid"));
			
			String sqlReply = "select * from s_reply where mid = ? order by date desc";
        	preparedStatement = connection.prepareStatement(sqlReply);
        	preparedStatement.setInt(1, resultSet.getInt("mid"));
            @Cleanup ResultSet resultSetReply = preparedStatement.executeQuery();
            while(resultSetReply.next()) {
            	Reply reply = new Reply();
            	reply.setRid(resultSetReply.getInt("rid"));
            	reply.setUid(resultSetReply.getString("uid"));
            	reply.setRmsg(resultSetReply.getString("rmsg"));
            	reply.setDate(resultSetReply.getDate("date") + "/" + resultSetReply.getTime("date"));
            	replies.add(reply);
            }
            resultSetReply.last();
            message.setReplycount(resultSetReply.getRow());
//            System.out.println("r count"+rrs.getRow());
            
            messageSet.setMessage(message);
            messageSet.setRlist(replies);
            datas.add(messageSet);
        }
        return datas;
	}
	
	/*
	 좋아요 추가
	 @param mid
	 * */
	public void favorite(int mid) throws Exception {
		// 종아요 추가를 위해 favcount 를 +1 해서 update함
		String sql ="update s_message set favcount=favcount+1 where mid=?";
		
		@Cleanup Connection connection = DBManager.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, mid);
        preparedStatement.executeUpdate();
	}
}
