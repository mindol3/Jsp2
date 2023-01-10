<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="menu.jsp"/>
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3">상품 수정</h1>
		</div>
	</div>
	<%@ include file="dbconn.jsp" %>
	<%
		String booktId = request.getParameter("id");
	
		String sql = "select * from book where id = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, booktId);
		rs = pstmt.executeQuery();
		if (rs.next()) {
	%>
	<div class="container">
	<div class="text-right">
			<img src="${pageContext.request.contextPath}/resources/images/<%=rs.getString("fileName")%>"
				style="width: 100% "/>
		</div>
		<form name="newBook" action="./processUpdateBook.jsp" class="form-horizontal" method="post" enctype="multipart/form-data">
			<input type="hidden" id="booktId" name="booktId" value='<%=rs.getString("id") %>'>
			<div class="form-group row">
				<label class="col-sm-2">도서명</label>
				<div class="col-sm-3">
					<input type="text" name="name" class="form-control"
					value="<%=rs.getString("name")%>">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2">가격</label>
				<div class="col-sm-3">
					<input type="text" name="unitPrice" class="form-control"
					value="<%=rs.getInt("unitPrice")%>">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2">저자</label>
				<div class="col-sm-3">
					<input type="text" name="author" class="form-control"
					value="<%=rs.getString("author")%>">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2">설명</label>
				<div class="col-sm-5">
					<textarea name="description" rows="2" cols="50" class="form-control">
					<%=rs.getString("description")%></textarea>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2">출판사</label>
				<div class="col-sm-3">
					<input type="text" name="publisher" class="form-control"
					value="<%=rs.getString("publisher")%>">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2">분류</label>
				<div class="col-sm-3">
					<input type="text" name="category" class="form-control"
					value="<%=rs.getString("category")%>">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2">재고 수</label>
				<div class="col-sm-3">
					<input type="text" name="unitsInStock" class="form-control"
					value="<%=rs.getLong("unitsInStock")%>">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2">페이지 수</label>
				<div class="col-sm-3">
					<input type="text" name="totalPages" class="form-control"
					value="<%=rs.getLong("totalPages")%>">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2">출판일(월/년)</label>
				<div class="col-sm-3">
					<input type="text" name="releseDate" class="form-control"
					value="<%=rs.getString("releseDate")%>">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2">상태</label>
				<div class="col-sm-5">
					<input type="radio" name="condition" value="New" <% if (rs.getString("condition").equals("New")) out.print(" checked"); %>> 신규 도서
					<input type="radio" name="condition" value="Old" <% if (rs.getString("condition").equals("Old")) out.print(" checked"); %>> 중고 도서
					<input type="radio" name="condition" value="Refurbished" <% if (rs.getString("condition").equals("Refurbished")) out.print(" checked"); %>> E-book
				</div>
			</div>
			<div class="form-group row">
			<label class="col-sm-2">이미지</label>
				<div class="col=sm-5">
					<input type="file" name="bookImage" class="form-control">
				</div>
			</div>
			<div class="form-group row">
				<div class="col-sm-offset-2 col-sm-10">
					<input type="submit" class="btn btn-primary" value="수정" onclick ="CheckAddBook()">
				</div>
			</div>
		</form>
	</div>
	<%
		}
		if (rs != null)
			rs.close();
		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();
	%>
</body>
</html>