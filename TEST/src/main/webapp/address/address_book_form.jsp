<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주소록:작성화면</title>
<link rel="stylesheet" href="./address_book.css" type="text/css" media="screen" />
</head>
<body>
	<div align="center">
		<h2>주소록:작성화면</h2>
		<hr>
		[<a href="./address_book_control.jsp?action=list">주소록 목록으로</a>] <br>
		<div>
			<form action="./controller.do" name="frmSearch" method="post">
				<input type="hidden" name="action" value="search">
				<select name="part">
					<option value="name">이름</option>
					<option value="tel">전화번호</option>
					<option value="comdept">회사</option>
					<option value="memo">메모</option>
				</select>
				<input type="search" name="keyword" placeholder="이름 검색어">
				<input type="submit" value="검색">
				<input type="button" value="취소" onclick="document.loaction.href='/'">
			</form>
		</div>
		<form action="./address_book_control.jsp" name="frmInsert" method="post">
		<input type="hidden" name="action" value="insert">
		<table border="1">
			<tr>
				<th>이 름</th>
				<td><input type="text" name="name" maxlength="15"></td>
			</tr>
			<tr>
				<th>email</th>
				<td><input type="email" name="email" maxlength="50"></td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td><input type="text" name="tel" maxlength="20"></td>
			</tr>
			<tr>
				<th>생 일</th>
				<td><input type="date" name="birth"></td>
			</tr>
			<tr>
				<th>회 사</th>
				<td><input type="text" name="comdept" maxlength="20"></td>
			</tr>
			<tr>
				<th>메 모</th>
				<td><input type="text" name="memo"></td>
			</tr>
			<tr>
				<td colspan=2 align=center>
					<input type=submit value="저장">
					<input type=reset value="취소">
				</td>
			</tr>
		</table>
		</form>
	</div>
</body>
</html>