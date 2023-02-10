package sns;


import lombok.Data;

@Data
public class Message {
	// 게시글 시퀀스 id
	private int mid;
	
	// 게시글 작성자
	private String uid;
	
	// 게시글 내용
	private String msg;
	
	// 게시글 작성일, 시간
	private String date;
	
	//좋아요 횟수
	private int favcount;
	
	// 댓글 갯수
	private int replycount;
	
	
}
