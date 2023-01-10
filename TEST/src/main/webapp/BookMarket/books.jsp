<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="market.ver02.dto.Book" %>
<%-- 추가 부분 --%>
<%@ page import="market.ver02.dao.BookRepository" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<title>상품목록</title>
</head>
<body>
	<jsp:include page="menu.jsp"/>
	<div class="jumbotron">
		<div class="container">
			<h1>상품목록</h1>
		</div>
	</div>
	<%
	
		BookRepository dao = BookRepository.getInstance();
		ArrayList<Book> listOfbooks = dao.getAllBooks();
	%>
	
	<div class="container">
		<div class="row" align="center">
			<%@ include file="dbconn.jsp" %>
				<%
					String sql = "select * from book";
					pstmt = conn.prepareStatement(sql);
					rs = pstmt.executeQuery();
					while (rs.next()) {
				%>
			<div>
				<img src="${pageContext.request.contextPath}/resources/images/<%=rs.getString("fileName")%>"
				style = "width: 30%" alt="">
				<h3>[<%=rs.getString("category") %>] <%=rs.getString("name") %></h3>
				<p><%=rs.getString("description") %>
				<br>
				<p><%=rs.getString("author") %> | <%=rs.getString("publisher") %> | <%=rs.getString("unitsInStock") %>원 
				<p><a href="./book.jsp?id=<%=rs.getString("id") %>" class="btn btn-secondary" role="button">
				상세 정보 &raquo;</a>
				<hr>
			</div>
		
			<%
				}
					
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
			%>
		</div>
		<hr>
	</div>
	<jsp:include page="footer.jsp"/>
</body>
</html>