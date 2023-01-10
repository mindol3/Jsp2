<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="market.ver01.dao.CartDAO" %>
<%
	String orderNo = session.getId();
	CartDAO cartDAO = new CartDAO();
	cartDAO.deleteCartAll(orderNo);
	response.sendRedirect("cart.jsp");
%>
