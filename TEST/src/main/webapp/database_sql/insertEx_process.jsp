<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="dbconn.jsp"%>
<%
	request.setCharacterEncoding("utf-8");

	String num = request.getParameter("num");
	String depart = request.getParameter("depart");
	String name = request.getParameter("name");
	String address = request.getParameter("address");
	String phone = request.getParameter("phone");
	
	PreparedStatement pstmt = null; // PreparedStatement 참조변수를 null로 초기화
	
	try {
		// member 테이블의 id, passwd, name 필드에 정해지지 않은 값을 삽입하도록 INSERT문을 작성.
		String sql = "insert into student(num, depart, name, address, phone) values(?,?,?,?,?)";
		pstmt = conn.prepareStatement(sql); // prepareStatement 객체를 생성하도록 작성.
		// 폼 페이지에서 전송된 아이디, 비밀번호, 이름을 물음표에 설정하도록 setString() 메소드를 작성.
		pstmt.setString(1, num);
		pstmt.setString(2, depart);
		pstmt.setString(3, name);
		pstmt.setString(4, address);
		pstmt.setString(5, phone);
		pstmt.executeUpdate(); // INSERT 문을 실행하도록 PreparedStatement 객체의 executeUpdate() 메서드를 작성.
		out.println("student 테이블 삽입이 성공했습니다.");
	} catch (SQLException ex) {
		out.println("student 테이블 삽입이 실패했습니다.<br>");
		out.println("SQLException: " + ex.getMessage());
	} finally {
		// 생성한 Statement 객체와 Connection 객체를 해제
		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();
	}
%>