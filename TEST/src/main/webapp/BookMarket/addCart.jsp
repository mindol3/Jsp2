<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="market.ver02.dto.Book" %>
<%@ page import="market.ver02.dao.BookRepository" %>

<%
	String id = request.getParameter("id");
	if (id == null || id.trim().equals("")) {
		response.sendRedirect("products.jsp");
		return;
	}
	
	BookRepository dao = BookRepository.getInstance();
	
	Book product = dao.getBookBytId(id);
	if (product == null) {
		response.sendRedirect("exceptionNoProductId.jsp");
	}
	
	/*
	ArrayList<Product> goodsList = dao.getAllProducts();
	Product goods = new Product();
	for (int i = 0; i < goodsList.size(); i++) {
		goods = goodsList.get(i);
		if (goods.getProductId().equals(id)) {
			break;
		}
	}
	*/ // 요청 파라미터 아이디의 상품을 담은 장바구니를 초기화 하도록 작성
	ArrayList<Book> list = (ArrayList<Book>) session.getAttribute("cartlist");
	if (list == null) {
		list = new ArrayList<Book>();
		session.setAttribute("cartlist", list);
	}
	
	int cnt = 0; // 기존 장바구니에 담긴 상품인지 확인하기 위한 용도
	Book goodsQnt = new Book();
	for (int i = 0; i < list.size(); i++) {
		goodsQnt = list.get(i);
		if (goodsQnt.getBooktId().equals(id)) {
			cnt++;
			int orderQuantity = goodsQnt.getQuantity() + 1;
			goodsQnt.setQuantity(orderQuantity);
		}
	}
	
	if (cnt == 0) {
		product.setQuantity(1);
		list.add(product);
	}
	
	response.sendRedirect("book.jsp?id=" + id);
			
%>