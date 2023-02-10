package sns;

import lombok.Data;

@Data
public class Reply {
	// 원본글 id
	private int mid;
	
	// 답글 시퀀스 id
	private int rid;
	
	// 답글 작성자
	private String uid;
	
	// 답글 내용
	private String rmsg;
	
	// 답글 작성일자
	private String date;
	
}
