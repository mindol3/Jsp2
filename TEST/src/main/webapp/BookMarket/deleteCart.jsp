<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="market.ver02.dto.Book" %>
<%@ page import="market.ver02.dao.BookRepository" %>

<%
	session.invalidate();

	response.sendRedirect("cart.jsp");
%>