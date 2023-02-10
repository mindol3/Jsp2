<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="now" class="java.util.Date"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mysns:404-error</title>
</head>
<body>
	<div style="text-aling: center">
	<h2>MySNS:404_error 발생!!</h2>
	<hr>
	<table>
		<tr>
			<td style="background: orangered">
			요청하신 파일을 찾을수 없습니다.<br>
			URL 주소를 다시한번 확인해 주세요!!.</td>
		</tr>
		<tr>
			<td>
			${now} <br>
			요청 실패 URI : ${pageContext.errorData.requestURI} <br>
			상태코드 : ${pageContext.errorData.statusCode} <br>
		</tr>
	</table>
	</div>
</body>
</html>