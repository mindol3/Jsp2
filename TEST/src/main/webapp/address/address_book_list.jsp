<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="mvc2.dto.AddressBookDTO"%>
<%@ page import="mvc2.dao.AddressBookDAO"%> 
<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>주소록:목록화면</title>
	<link rel="stylesheet" href="./address_book.css" type="text/css" media="screen" />
	<script type="text/javascript">
		function check(ID) {
			pwd = prompt('수정/삭제 하려면 비밀번호를 입력하세요');
			document.location.href="controller.do?action=edit&id=" + ID + "&passwd=" + pwd;
		}
	</script>
</head>
<body>
	<div align="center">
		<h2>주소록:목록화면</h2>
		<hr>
		<div>
		<%
			String part = (String)request.getAttribute("part");
		if(part == null){
			part ="";
		}
		String keyword = (String)request.getAttribute("keyword");
		if(keyword == null){
			keyword ="";
		}
		%>
			<form action="./address_book_control.jsp" name="frmSearch" method="post">
				<input type="hidden" name="action" value="search">
				<select name="part">
					<option value="name"<% if(part.equals("name")) {out.print("selected");}%>>이름</option>
					<option value="tel"<% if(part.equals("tel")) {out.print("selected");}%>>전화번호</option>
					<option value="comdept"<% if(part.equals("comdept")) {out.print("selected");}%>>회사</option>
					<option value="memo"<% if(part.equals("memo")) {out.print("selected");}%>>메모</option>
				</select>
				<input type="search" name="keyword" placeholder="검색어" value="<%=keyword%>">
				<input type="submit" value="검색">
				<input type="button" value="취소" onclick="document.location.href='index.jsp'">
			</form>
		</div>
		<form>
		<a href="./address_book_form.jsp">주소록 등록</a><br>

		<table border="1">
			<tr>
				<th>번호</th>
				<th>이 름</th>
				<th>전화번호</th>
				<th>생 일</th>
				<th>회 사</th>
				<th>메 모</th></tr>
			<%
				ArrayList<AddressBookDTO> datas = (ArrayList<AddressBookDTO>)request.getAttribute("datas");
				for(AddressBookDTO addressBookDTO : datas) {
			%>
			<tr>
				<td><a href="javascript:check(<%=addressBookDTO.getId()%>)"><%=addressBookDTO.getId() %></a></td>
				<td><%=addressBookDTO.getName()%></td>
				<td><%=addressBookDTO.getTel()%></td>
				<td><%=addressBookDTO.getBirth()%></td>
				<td><%=addressBookDTO.getComdept()%></td>
				<td><%=addressBookDTO.getMemo()%></td></tr>
			 <%
				}
			 %>
		</table>
		</form>
	</div>
</body>
</html>