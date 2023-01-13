package mvc2.controller;

import mvc2.dao.AddressBookDAO;
import mvc2.dto.AddressBookDTO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;


@WebServlet("*.do")
public class Controller extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html; charset=utf-8");
		req.setCharacterEncoding("utf-8");
		
		String action = req.getParameter("action");
		System.out.println(action);
		
		AddressBookDAO dao = new AddressBookDAO();
		AddressBookDTO addressBookDTO = new AddressBookDTO();
		
		// 파라미터에 따른 요청 처리
		// 주소록 목록 요청인 경우
		if(action.equals("list")) {
			ArrayList<AddressBookDTO> datas = dao.getDBList();
			req.setAttribute("datas", datas);
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("./address_book_list.jsp");
			requestDispatcher.forward(req, resp);
		}
	
	// 주소록 등록 요청인 경우
		else if(action.equals("insert")) {
			System.out.println(req.getParameter("name"));
			System.out.println(req.getParameter("comdept"));
			
			addressBookDTO.setName(req.getParameter("name"));
			addressBookDTO.setEmail(req.getParameter("email"));
			addressBookDTO.setTel(req.getParameter("tel"));
			addressBookDTO.setBirth(req.getParameter("birth"));
			addressBookDTO.setComdept(req.getParameter("comdept"));
			addressBookDTO.setMemo(req.getParameter("memo"));
			if(dao.insertDB(addressBookDTO)) {
				RequestDispatcher requestDispatcher = req.getRequestDispatcher("control.do?action=list");
				requestDispatcher.forward(req, resp);
			}
			else {
				try {
					throw new Exception("DB 입력오류");
				} catch (Exception ignored) {
					
				}
			}
		}
		// 주소록 수정 페이지 요청인 경우
		else if (action.equals("edit")) {
			if(!req.getParameter("passwd").equals("1234")) {
				String resStr = "<script>alert('비밀번호가 틀렸습니다.!!');history.go(-1);</script>";
				resp.getWriter().print(resStr); // 응답 페이지에 전송
			}
			else {
				AddressBookDTO dto = dao.getDB(Integer.parseInt(req.getParameter("id")));
				req.setAttribute("dto", dto);
				RequestDispatcher requestDispatcher = req.getRequestDispatcher("address_book_edit_form.jsp");
				requestDispatcher.forward(req, resp);
			}
		}
		
		// 주소록 수정 등록 요청인 경우
		else if(action.equals("update")) {
			System.out.println(req.getParameter("name"));
			System.out.println(req.getParameter("comdept"));
			
			addressBookDTO.setId(Integer.parseInt(req.getParameter("id")));
			addressBookDTO.setName(req.getParameter("name"));
			addressBookDTO.setEmail(req.getParameter("email"));
			addressBookDTO.setTel(req.getParameter("tel"));
			addressBookDTO.setBirth(req.getParameter("birth"));
			addressBookDTO.setComdept(req.getParameter("comdept"));
			addressBookDTO.setMemo(req.getParameter("memo"));
			if(dao.insertDB(addressBookDTO)) {
				resp.sendRedirect("control.do?action=list");
			}
			else {
				try {
					throw new Exception("DB 입력오류");
				} catch (Exception ignored) {
					
				}
			}
		}
	}
}
