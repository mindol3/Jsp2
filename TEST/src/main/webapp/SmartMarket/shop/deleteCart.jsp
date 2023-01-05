<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="market.ver01.dto.Product" %>
<%@ page import="market.ver01.dao.ProductRepository" %>

<%
	session.invalidate();

	response.sendRedirect("cart.jsp");
%>