<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ include file="../inc/dbconn.jsp"%>
<html>
<head>
<link rel="stylesheet" href ="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">


<title>정보 수정</title>
</head>
<body>
   <jsp:include page="../inc/menu.jsp" />
   <div class="jumbotron">
      <div class="container">
         <h1 class="display-3"> </h1>
      </div>
   </div>
   <%
   String sessionAdminId = (String) session.getAttribute("sessionAdminId");
%>
<%
	String sql = "SELECT * FROM admin WHERE id = ?";
	pstmt = conn.prepareStatement(sql);
	pstmt.setString(1, sessionAdminId);
	rs = pstmt.executeQuery();
	if (rs.next()){
		
	
%>
<div class="container">
      <form name="newMember" class="form-horizontal"  action="processUpdateAdmin.jsp" method="post" onsubmit="return checkForm()">
         <div class="form-group  row">
            <label class="col-sm-2 ">아이디</label>
            <div class="col-sm-3">
            	<%=rs.getString("id") %>
            </div>
         </div>
         <div class="form-group  row">
            <label class="col-sm-2">비밀번호</label>
            <div class="col-sm-3">
               <input name="password" type="text" class="form-control" placeholder="password" 
               value="<%=rs.getString("password")%>">
            </div>
         </div>
         <div class="form-group  row">
            <label class="col-sm-2">비밀번호확인</label>
            <div class="col-sm-3">
               <input name="password_confirm" type="text" class="form-control" placeholder="password confirm" >
            </div>
         </div>
         <div class="form-group  row">
            <label class="col-sm-2">성명</label>
            <div class="col-sm-3">
               <input name="name" type="text" class="form-control" placeholder="name"
                value="<%=rs.getString("name")%>">
            </div>
         </div>
         <div class="form-group  row">
            <div class="col-sm-offset-2 col-sm-10 ">
               <input type="submit" class="btn btn-primary " value="회원수정 " > 
               <a href="deleteMember.jsp" class="btn btn-primary">회원탈퇴</a>
            </div>
         </div>
      </form>
   </div>
   <jsp:include page="../inc/footer.jsp"/>
   <%
	}
   %>
</head>
</html>
<script type="text/javascript">
   function checkForm() {
      if (!document.newMember.password.value) {
         alert("비밀번호를 입력하세요.");
         return false;
      }
      if (document.newMember.password.value != document.newMember.password_confirm.value) {
         alert("비밀번호를 동일하게 입력하세요.");
         return false;
      }
   }
</script>