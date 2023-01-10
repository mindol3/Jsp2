<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="market.ver02.dto.Book" %>
<%@ page import="market.ver02.dao.BookRepository" %>

<%

	/*장바구니에서 상품을 개별 삭제*/
	String id = request.getParameter("id");
	if (id == null || id.trim().equals("")) {
		response.sendRedirect("products.jsp");
		return;
	}
	
	BookRepository dao = BookRepository.getInstance();
	
	Book book = dao.getBookBytId(id);
	if (book == null) {
		response.sendRedirect("exceptionNoProductId.jsp");
	}
	
	ArrayList<Book> cartList = (ArrayList<Book>) session.getAttribute("cartlist");
	cartList.remove(book);
	/*Product goodsQnt = new Product();
	for (int i = 0; i <cartList.size(); i++) { // 상품리스트 하나씩 출력하기
		goodsQnt = cartList.get(i);
		if (goodsQnt.getProductId().equals(id)) {
			cartList.remove(goodsQnt);
		}
	}*/
	
	response.sendRedirect("cart.jsp");
%>
