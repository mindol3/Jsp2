<%@page import="org.apache.jasper.tagplugins.jstl.core.If"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%
	request.setCharacterEncoding("UTF-8");

	String id = request.getParameter("id");
	String password = request.getParameter("password");
%>

<sql:setDataSource var="dataSource" 
	url = "jdbc:mariadb://localhost:5000/WebMarketDB"
	driver="org.mariadb.jdbc.Driver" user="root" password="1234"/>
	
<sql:query dataSource ="${dataSource}" var = "resultSet">
	SELECT * FROM MEMBER WHERE ID=? and password=?
	<sql:param value="<%=id%>" />
	<sql:param value="<%=password %>" />

</sql:query>

<c:forEach var="row" items="$(resultSet.rows)">
	<%
		session.setAttribute("sessionId", id);
	%>
	<c:redirect url="resultMember.jsp?msg=2" />
</c:forEach>
	<c:redirect url="LogintMember.jsp?error=1" />



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>