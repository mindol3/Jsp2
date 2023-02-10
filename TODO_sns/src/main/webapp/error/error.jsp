<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="now" class="java.util.Date"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mysns error</title>
</head>
<body>
	<div style="text-aling: center">
	<h2>MySNS 오류!!</h2>
	<hr>
	<table>
		<tr>
			<td style="background: orangered">
			관리자에게 문의해 주세요..<br>
			빠른시일내 복구하겠습니다.</td>
		</tr>
		<tr>
			<td>
			${now} <br>
			요청 실패 URI : ${pageContext.errorData.requestURI} <br>
			상태코드 : ${pageContext.errorData.statusCode} <br>
			예외유형 : ${pageContext.errorData.throwable}</td>
		</tr>
	</table>
	</div>
</body>
</html>