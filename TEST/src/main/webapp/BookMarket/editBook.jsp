<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="market.ver02.dto.Book" %>
<%-- 추가 부분 --%>
<%@ page import="market.ver02.dao.BookRepository" %>
<jsp:useBean id="bookDAO" class="market.ver02.dao.BookRepository" scope="session"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./resources/css/bootstrap.min.css" />
<title>Insert title here</title>
<script type="text/javascript">
	function deleteConfirm(id) {
		if (confirm("해당 상품을 삭제합니다?") == true)
			location.href = "./deleteBook.jsp?id=" + id;
		else 
			return;
		}
</script>
</head>
<%
	String edit = request.getParameter("edit");
%>
</head>
<body>
	<jsp:include page="menu.jsp"/>
	<div class="jumbotron">
		<div class="container">
			<h1 class="display=3">상품 편집</h1>
		</div>
	</div>
	<div class="container">
		<div class="row" align="center">
			<%@ include file="dbconn.jsp" %>
			<%
				String sql = "select * from book";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while (rs.next()) {
			%>
			<div class="col-md-4">
				<img src="${pageContext.request.contextPath}/resources/images/<%=rs.getString("fileName")%>"
				style="width: 100% "/>
				<h3><%=rs.getString("name") %></h3>
				<p><%=rs.getString("description") %>
				<p><%=rs.getString("UnitPrice") %>원
				<p>
					<%
						if(edit.equals("update")) {
					%>
					<a href="./updateBook.jsp?id=<%=rs.getString("id")%>" class="btn btn-success" role="button"> 
					수정 &raquo;</a>		
					<%
						} else if (edit.equals("delete")) {
					%>
					<a href="" onclick="deleteConfirm('<%=rs.getString("id")%>')" class="btn btn-danger" role="button">
					삭제 &raquo;</a>							
					<%
						}
					%>										
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
		</div>
	</div>
	<jsp:include page="footer.jsp"/>
</body>
</html>