<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="mvc.model.BoardDTO"%> 
<%
	BoardDTO board = (BoardDTO) request.getAttribute("board");
	int num = ((Integer) request.getAttribute("num")).intValue();
	int nowpage = ((Integer) request.getAttribute("page")).intValue();
%>
<html>
<head>
<link rel="stylesheet" href="../resources/css/bootstrap.min.css" />

<title>Board</title>
</head>
<body>
	<jsp:include page="../inc/menu.jsp" />
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3">게시판</h1>
		</div>
	</div>
	
	<div class="container">
			<div class="form-group row">
				<label class="col-sm-2 control-label">성명</label>
				<div class="col-sm-3"><%=board.getName()%>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 control-label">제목</label>
				<div class="col-sm-5"><%=board.getSubject()%>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 control-label">내용</label>
				<div class="col-sm-8" style="word-break: break-all;">
					<%=board.getContent()%>
				</div>
			</div>
			<div class="form-group row">
				<div class="col-sm-offset-2 col-sm-10">
					<c:set var="userId" value="<%=board.getId() %>" />
					<c:if test="${sessionId==userId}">
						<p>
							<span class="btn btn-danger" onclick="goDelete()">삭제</span>
							<span class="btn btn-success" onclick="goUpdate()">수정</span>
					</c:if>
					<a href="./BoardListAction.do?pageNum=<%=nowpage%>" class="btn btn-primary">목록</a>
				</div>
			</div>
		<hr>
	</div>
	<form name="frmUpdate" method="post">
		<input type="hidden" name="num" value="<%=num%>">
		<input type="hidden" name="pageNum" value="<%=nowpage%>">
	</form>
	<script type="text/javascript">
	
	let goUpdate = function(){
		const frm = document.frmUpdate;
		frm.action="../board/BoardUpdateForm.do";
		frm.submit();

	}
	let  goDelete = function(){
		if (confirm("정말로 삭제 하시겠습니까?")){
		const frm = document.frmUpdate;
		frm.action="../board/BoardDeleteAction.do";
		frm.submit();
		}
	}
	</script>
	<jsp:include page="../inc/footer.jsp" />
</body>
</html>





