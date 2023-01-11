<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="mvc2.dto.AddressBookDTO" %>
<%@ page import="mvc2.dao.AddressBookDAO" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>주소록:수정화면</title>
	<link rel="stylesheet" href="./address_book.css" type="text/css" media="screen" />
	<script type="text/javascript">
		function delcheck(ID) {
			result = confirm("정말로 삭제하시겠습니까?");
			
			if(result === true){
				document.frmUpdate.action.value="delete";
				document.frmUpdate.submit();
			}
		}
	</script>
</head>
<body>
	<%
		AddressBookDTO addressBookDTO = (AddressBookDTO) request.getAttribute("dto");
	%>
	<div align="center">
		<h2>주소록:수정화면</h2>
		<hr>
		[<a href="./address_book_control.jsp?action=list">주소록 목록으로</a>] <br>
		<form action="./address_book_control.jsp" name="frmUpdate" method="post">
		<input type="hidden" name="id" value="<%=addressBookDTO.getId()%>">
		<input type="hidden" name="action" value="update">
		
		<table border="1">
			<tr>
				<th>이 름</th>
				<td><input type="text" name="name" value="<%=addressBookDTO.getName()%>"></td>
			</tr>
			<tr>
				<th>email</th>
				<td><input type="email" name="email" value="<%=addressBookDTO.getEmail()%>"></td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td><input type="text" name="tel" value="<%=addressBookDTO.getTel()%>"></td>
			</tr>
			<tr>
				<th>생 일</th>
				<td><input type="date" name="birth" value="<%=addressBookDTO.getBirth()%>"></td>
			</tr>
			<tr>
				<th>회 사</th>
				<td><input type="text" name="comdept" value="<%=addressBookDTO.getComdept()%>"></td>
			</tr>
			<tr>
				<th>메 모</th>
				<td><input type="text" name="memo" value="<%=addressBookDTO.getMemo()%>"></td>
			</tr>
			<tr>
				<td colspan=2 align=center>
					<input type=submit value="저장">
					<input type=reset value="취소">
					<input type=button value="삭제" onClick="delcheck()">
				</td>
			</tr>
		</table>
		</form>
	</div>
</body>
</html>