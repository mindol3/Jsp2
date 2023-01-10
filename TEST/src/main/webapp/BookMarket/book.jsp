<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="market.ver02.dto.Book" %>
<%@ page import="market.ver02.dao.BookRepository" %>
<jsp:useBean id="bookDAO" class="market.ver02.dao.BookRepository" scope="session"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<title>Insert title here</title>
<script type="text/javascript">
	function addToCart() {
		if (confirm("상품을 장바구니에 추가하시겠습니까?")) {
			document.addForm.submit();
		} else {
			document.addForm.reset();
		}
	}
</script>
</head>
<body>
	<jsp:include page="menu.jsp"/>
	<div class="jumbotron">
		<div class="container">
			<h1 class="display=3">상품목록</h1>
		</div>
	</div>
	<%
		String id = request.getParameter("id");
		Book book = bookDAO.getBookBytId(id);
		BookRepository dao = BookRepository.getInstance();

	%>
		<div class="container">
		<div class="row">
			<div class="col-md-5">
				<img src="${pageContext.request.contextPath}/resources/images/<%=book.getFilename()%>"
				style="width: 100% "/>
			</div>
			<div class="col-md-6">
				<h3><%=book.getName() %></h3>
				<p><b>저자</b> :<%=book.getAuthor() %>
				<p><b>설명</b> :<%=book.getDescription() %>
				<p><b>상품 코드 : </b><span class="badge badge-danger"> <%=book.getBooktId() %></span>
				<p><b>출판사</b> : <%=book.getPublisher() %>
				<p><b>분류</b> : <%=book.getCategory() %>
				<p><b>재고 수</b> :  <%=book.getUnitsInStock() %>
				<p><b>페이지 수</b> :  <%=book.getTotalPages() %>
				<p><b>출판일</b> :  <%=book.getReleseDate() %>
				<h4><%=book.getUnitPrice() %>원</h4>
				<p>
					<form name="addForm" action="./addCart.jsp?id=<%=book.getBooktId()%>" method="post">
					<a href="#" class="btn btn-info" onclick="addToCart()"> 상품 주문 &raquo;</a>
					<a href="./cart.jsp" class="btn btn-warning"> 장바구니 &raquo;</a>
					<a href="./books.jsp" class="btn btn-secondary"> 상품 목록 &raquo;</a>
					</form>
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp"/>
</body>
</html>