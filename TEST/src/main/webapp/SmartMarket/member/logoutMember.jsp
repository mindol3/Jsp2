<%@ page contentType="text/html; charset=utf-8"%>
<%
   session.removeAttribute("sessionId");
   session.removeAttribute("sessionName");

   session.invalidate();
   response.sendRedirect("../index.jsp");
%>