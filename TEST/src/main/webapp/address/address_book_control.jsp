<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="mvc2.dto.AddressBookDTO" %>
<%@ page import="mvc2.dao.AddressBookDAO" %>
<%
	request.setCharacterEncoding("utf-8");

	// 컨트롤러 요청 파라미터
	String action = request.getParameter("action");
	
	AddressBookDAO dao = new AddressBookDAO();
	AddressBookDTO addressBookDTO = new AddressBookDTO();
	
	// 파라미터에 따른 요청 처리
	// 주소록 목록 요청인 경우
	if(action.equals("list")) {
		ArrayList<AddressBookDTO> datas = dao.getDBList();
		System.out.println(datas);
		request.setAttribute("datas", datas);
		pageContext.forward("address_book_list.jsp");
	}
	// 주소록 등록 요청인 경우
	else if(action.equals("insert")) {
		addressBookDTO.setName(request.getParameter("name"));
		addressBookDTO.setEmail(request.getParameter("email"));
		addressBookDTO.setTel(request.getParameter("tel"));
		addressBookDTO.setBirth(request.getParameter("birth"));
		addressBookDTO.setComdept(request.getParameter("comdept"));
		addressBookDTO.setMemo(request.getParameter("memo"));
		if(dao.insertDB(addressBookDTO)) {
			response.sendRedirect("address_book_control.jsp?action=list");
		}
		else {
			throw new Exception ("DB 입력오류;");
		}
	}
	// 주소록 수정 페이지 요청인 경우
	else if (action.equals("edit")) {
		if(!request.getParameter("passwd").equals("1234")) {
			out.println("<script>alert('비밀번호가 틀렸습니다.!!');history.go(-1);</script>");
		}
		else {
			AddressBookDTO dto = dao.getDB(Integer.parseInt(request.getParameter("id")));
			//out.println(dto.getMemo());
			request.setAttribute("dto", dto);
			pageContext.forward("address_book_edit_form.jsp");
		}
	}
	// 주소록 수정 등록 요청인 경우
	else if(action.equals("update")) {
		addressBookDTO.setId(Integer.parseInt(request.getParameter("id")));
		addressBookDTO.setName(request.getParameter("name"));
		addressBookDTO.setEmail(request.getParameter("email"));
		addressBookDTO.setTel(request.getParameter("tel"));
		addressBookDTO.setBirth(request.getParameter("birth"));
		addressBookDTO.setComdept(request.getParameter("comdept"));
		addressBookDTO.setMemo(request.getParameter("memo"));
		if(dao.updateDB(addressBookDTO)) {
			response.sendRedirect("address_book_control.jsp?action=list");
		}
		else {
			throw new Exception("DB 갱신오류");
		}
	}
	// 주소록 삭제 요청인 경우
	else if(action.equals("delete")) {
		int id = Integer.parseInt(request.getParameter("id"));
		if(dao.deleteDB(id)) {
			response.sendRedirect("address_book_control.jsp?action=list");
		}
		else {
			throw new Exception("DB 삭제 오류");
		}
	}
	/*
	else {
		out.println("<script>alert('action 파라미터를 확인해주세요!!!')</script>");
	}
	*/
	
	// 검색 기능
	else if(action.equals("search")) {
		String part = request.getParameter("part");
		String keyword = request.getParameter("keyword");
		ArrayList<AddressBookDTO> datas = dao.getDBListSearch(part, keyword);
		request.setAttribute("datas", datas);
		request.setAttribute("part", part);
		request.setAttribute("keyword", keyword);
		pageContext.forward("address_book_list.jsp");
	}
	else {
		out.println("<script>alert('action 파라미터를 확인해주세요!!!')</script>");
	}
		
%>




<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>