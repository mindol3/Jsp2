<%@page import="org.apache.jasper.tagplugins.jstl.core.If"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../inc/dbconn.jsp"%>

<%
	request.setCharacterEncoding("UTF-8");

	String id = (String) session.getAttribute("sessionAdminId");
	String password = request.getParameter("password");
	String name = request.getParameter("name");
	
	String sql = "UPDATE ADMIN SET PASSWORD=?, NAME=? WHERE ID=?";
	pstmt = conn.prepareStatement(sql);
	pstmt.setString(1, password);
	pstmt.setString(2, name);
	pstmt.setString(3, id);
	pstmt.executeUpdate();
	int result = pstmt.executeUpdate();
	if (result == 1){
		session.setAttribute("sessionAdminName", name);
		response.sendRedirect("index.jsp");
	}
	else {
		response.sendRedirect("updateAdmin.jsp");
	}
	
%>
<%--
<sql:setDataSource var="dataSource" 
	url = "jdbc:mariadb://localhost:5000/WebMarketDB"
	driver="org.mariadb.jdbc.Driver" user="root" password="1234"/>
	
<sql:update dataSource ="${dataSource}" var = "resultSet">
	UPDATE MEMBER SET PASSWORD=?, NAME=? WHERE ID=?
	<sql:param value="<%=password %>" />
	<sql:param value="<%=name%>" />
	<sql:param value="<%=id%>" />
</sql:update>
<c:if test="${resultSet>=1}">
	<c:redirect url="resultMember.jsp?msg=0" />
</c:if>  --%>
