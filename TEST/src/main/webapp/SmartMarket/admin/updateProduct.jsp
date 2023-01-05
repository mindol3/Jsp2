<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<script type="text/javascript" src="./resources/js/validation.js" defer></script>
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="../inc/menu.jsp"/>
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3">상품 수정</h1>
		</div>
	</div>
	<%@ include file="../inc/dbconn.jsp" %>
	<%
		String productId = request.getParameter("id");
	
		String sql = "select * from product where p_id = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, productId);
		rs = pstmt.executeQuery();
		if (rs.next()) {
	%>
			
	<div class="container">
		<div class="text-right">
			<img src="${pageContext.request.contextPath}/resources/images/<%=rs.getString("p_fileName")%>"
				style="width: 100% "/>
		</div>
		<div class="col-md-7">
		<form name="newProduct" action="./processUpdateProduct.jsp" class="form-horizontal" method="post"
		enctype="multipart/form-data">
			<input type="hidden" id="productId" name="productId" value='<%=rs.getString("p_id") %>'>
			<div class="form-group row">
				<label class="col-sm-2">상품명</label>
				<div class="col-sm-3">
					<input type="text" name="pname" id="pname" class="form-control"
						value="<%=rs.getString("p_name")%>">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2">가격</label>
				<div class="col-sm-3">
					<input type="text" name="unitPrice" id="unitPrice" class="form-control"
						value="<%=rs.getInt("p_UnitPrice")%>">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2">상세 정보</label>
				<div class="col-sm-5">
					<textarea name="description" rows="2" cols="50" class="form-control"
					><%=rs.getString("p_description")%></textarea>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2">제조사</label>
				<div class="col-sm-3">
					<input type="text" name="manufacturer" class="form-control"
					value="<%=rs.getString("p_manufacturer")%>">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2">분류</label>
				<div class="col-sm-3">
					<input type="text" name="category" class="form-control"
					value="<%=rs.getString("p_category")%>">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2">재고 수</label>
				<div class="col-sm-3">
					<input type="text" name="unitsInStock" id="unitsInStock" class="form-control"
					value="<%=rs.getLong("p_unitsInStock")%>">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2">상태</label>
				<div class="col-sm-5">
					<input type="radio" name="condition" value="New" <% if (rs.getString("p_condition").equals("New")) out.print(" checked"); %>> 신규 제품
					<input type="radio" name="condition" value="Old"<% if (rs.getString("p_condition").equals("Old")) out.print(" checked"); %>> 중고 제품
					<input type="radio" name="condition" value="Refurbished"<% if (rs.getString("p_condition").equals("Refurbished")) out.print(" checked"); %>> 재생 제품
				</div>
			</div>
			<div class="form-group row">
			<label class="col-sm-2">이미지</label>
				<div class="col=sm-5">
					<input type="file" name="productImage" class="form-control">
				</div>
			</div>
			<div class="form-group row">
				<div class="col-sm-offset-2 col-sm-10">
					<input type="button" class="btn btn-primary" value="수정" onclick ="CheckAddProduct()">
				</div>
			</div>
		</form>
		</div>
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