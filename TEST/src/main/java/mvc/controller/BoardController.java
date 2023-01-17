package mvc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.model.BoardDTO;
import mvc.model.BoardDAO;

@WebServlet("*.do")
public class BoardController extends HttpServlet{
	static final int LISTCOUNT = 5; // 페이지당 게시물 수
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp); // get으로 넘어온 것을 post로 넘김.
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String RequestURI = req.getRequestURI(); // 전체 경로를 가져옴.
		String contextPath = req.getContextPath(); // 프로젝트 Path만 가져옴
		String command = RequestURI.substring(contextPath.length()); // 전체경로에서 프로젝트 Path 길이 만큼의 인덱스 이후의 문자열을 가져옴.
		
		resp.setContentType("text/html; charset-utf-8");
		req.setCharacterEncoding("utf-8");
		System.out.println("RequestURI: "+RequestURI);
		System.out.println("contextPath: "+contextPath);
		System.out.println("command: "+command);
		
		
		if (command.contains("/BoardListAction.do")) { // 등록된 글 목록 페이지 출력하기
			requestBoardList(req);
			RequestDispatcher rd = req.getRequestDispatcher("../board/list.jsp");
			rd.forward(req, resp);
		}
		else if (command.contains("/BoardWriteForm.do")) { // 글 등록 페이지 출력하기
//			requestLoginName(req);
			RequestDispatcher rd = req.getRequestDispatcher("../board/writeForm.jsp");
			rd.forward(req, resp);
		}
		else if (command.contains("/BoardWriteAction.do")) { // 새로운 글 등록하기
			requestBoardWrite(req);
			RequestDispatcher rd = req.getRequestDispatcher("../board/BoardListAction.do");
			rd.forward(req, resp);
		}
		else if (command.contains("/BoardViewAction.do")) { // 선택된 글 상세 페이지 가져오기
			requestBoardWrite(req);
			RequestDispatcher rd = req.getRequestDispatcher("../board/BoardView.do");
			rd.forward(req, resp);
		}
		else if (command.contains("/BoardView.do")) { // 글 상세 페이지 출력하기
			requestBoardView(req);
			RequestDispatcher rd = req.getRequestDispatcher("../board/view.jsp");
			rd.forward(req, resp);
		}
		else if (command.contains("/BoardUpdateForm.do")) { // 글 수정 페이지 출력하기
			requestBoardView(req);
			RequestDispatcher rd = req.getRequestDispatcher("../board/updateForm.jsp");
			rd.forward(req, resp);
		}
		else if (command.contains("/BoardUpdateAction.do")) { // 글 수정하기
			requestBoardUpdate(req);
			RequestDispatcher rd = req.getRequestDispatcher("../board/BoardListAction.do");
			rd.forward(req, resp);
		}
		else if (command.contains("/BoardDeleteAction.do")) { // 선택된 글 삭제하기
			requestBoardDelete(req);
			RequestDispatcher rd = req.getRequestDispatcher("../board/BoardListAction.do");
			rd.forward(req, resp);
		}
	}


	// 등록된 글 목록 가져오기
	private void requestBoardList(HttpServletRequest req) {
		
		BoardDAO dao = BoardDAO.getInstance();
		List<BoardDTO> boardlist = new ArrayList<BoardDTO>();
		
		int pageNum=1;
		int limit=LISTCOUNT;
		
		if(req.getParameter("pageNum")!=null)
			pageNum=Integer.parseInt(req.getParameter("pageNum"));
		
		String items =req.getParameter("items"); // 검색 필드
		String text =req.getParameter("text"); // 검색어
		
		int total_record= dao.getListCount(items, text); // 전체 게시물 수
		boardlist = dao.getBoardList(pageNum, limit, items, text); // 현재 페이지에 해당하는 목록 데이터 가져오기
		
		int total_page; // 전체 페이지
		
		if (total_record % limit == 0) { // 전체 게시물이 limit의 배수일 때
			total_page = total_record/limit;
			Math.floor(total_page);
		}
		else {
			total_page = total_record/limit;
			Math.floor(total_page);
			total_page = total_page + 1;
		}
		
		req.setAttribute("pageNum", pageNum); // 페이지 번호
		req.setAttribute("total_page", total_page); // 전체 페이지 수
		req.setAttribute("total_record", total_record); // 전체 게시물 수
		req.setAttribute("boardlist", boardlist); // 현재 페이지에 해당하는 목록 데이터
				
		
	}
	// 인증된 사용자명 가져오기
//	private void requestLoginName(HttpServletRequest req) {
//		
//		String id = req.getParameter("id");
//		
//		BoardDAO dao = BoardDAO.getInstance();
//		
//		String name = dao.getLoginNameById(id);
//		
//		req.setAttribute("name", name);
//				
//	}
	
	// 새로운 글 등록하기
	private void requestBoardWrite(HttpServletRequest req) {
		
		BoardDAO dao = BoardDAO.getInstance();
		
		BoardDTO board = new BoardDTO();
		board.setId(req.getParameter("id"));
		board.setName(req.getParameter("name"));
		board.setSubject(req.getParameter("subject"));
		board.setContent(req.getParameter("content"));
		
		System.out.println(req.getParameter("name"));
		System.out.println(req.getParameter("subject"));
		System.out.println(req.getParameter("content"));
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy/MM/dd(HH:mm:ss)");
		String regist_day = formatter.format(new java.util.Date());
		
		board.setHit(0);
		board.setRegist_day(regist_day);
		board.setIp(req.getRemoteAddr());
		
		dao.insertBoard(board);
		
	}
	
	// 글 상세 페이지 출력하기
	private void requestBoardView(HttpServletRequest req) {
		
		BoardDAO dao = BoardDAO.getInstance();
		int num = Integer.parseInt(req.getParameter("num"));
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		
		BoardDTO board = new BoardDTO();
		board = dao.getBoardByNum(num, pageNum);
		
		req.setAttribute("num", num);
		req.setAttribute("page", pageNum);
		req.setAttribute("board", board);
	}
	
	// 글 수정
	private void requestBoardUpdate(HttpServletRequest req) {
		
		int num = Integer.parseInt(req.getParameter("num"));
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		
		BoardDAO dao = BoardDAO.getInstance();

		BoardDTO board = new BoardDTO();
		board.setNum(num);
		board.setName(req.getParameter("name"));
		board.setSubject(req.getParameter("subject"));
		board.setContent(req.getParameter("content"));
		
		dao.updateBoard(board);
	}
	
	// 선택된 글 삭제하기
	private void requestBoardDelete(HttpServletRequest req) {
		
		int num = Integer.parseInt(req.getParameter("num"));
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		
		BoardDAO dao = BoardDAO.getInstance();
		dao.deleteBoard(num);
		
	}
}
