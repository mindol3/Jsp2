<%@ page contentType="text/html; charset=utf-8"%>
<%

	session.removeAttribute("sessionAdminId");
	session.removeAttribute("sessionAdminName");
	session.removeAttribute("sessionAdminDay");
	
   response.sendRedirect("loginAdmin.jsp");
%>