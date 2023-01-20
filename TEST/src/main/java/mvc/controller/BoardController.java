package mvc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import mvc.model.BoardDAO;
import mvc.model.BoardDTO;
import mvc.model.RippleDAO;
import mvc.model.RippleDTO;

@WebServlet("*.do")
//@WebServlet("/boardController")
public class BoardController extends HttpServlet {
    static final int LISTCOUNT = 5;   //페이지 당 게시물 수
    private String boardName = "Board";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);   //get 방식으로 넘어온 것을 post로 넘기는 것
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String RequestURI = req.getRequestURI();   //전체 경로 가져오기 (프로젝트명 + 주소)
        String contextPath = req.getContextPath();   //프로젝트의 path만 가져오기 (프로젝트명)
        String command = RequestURI.substring(contextPath.length());

        resp.setContentType("text/html; charset=utf-8");
        req.setCharacterEncoding("utf-8");

        System.out.println(command);   //디버깅 방법 중에 하나. 밑에 찍히는 걸로 확인

        //url 패턴을 확인해서 /BoardListAction.do 얘가 맞으면 메소드 실행
        if (command.contains("/BoardListAction.do")) {   //등록된 글 목록 페이지 출력하기
            requestBoardList(req);   //메소드 실행
            RequestDispatcher rd = req.getRequestDispatcher("../board/list.jsp");
            //RequestDispatcher rd객체가 가지고 있는 path에 해당하는 페이지로 이동
            //객체를 생성후 forward() 메소드를 이용하여 이동
            rd.forward(req, resp);

        } else if (command.contains("/BoardWriteForm.do")) {   //글 등록 페이지 출력하기
            //requestLoginName(req);   //메소드 실행
            RequestDispatcher rd = req.getRequestDispatcher("../board/writeForm.jsp");
            rd.forward(req, resp);
        } else if (command.contains("/BoardWriteAction.do")) { //새로운 글 등록하기
            requestBoardWrite(req);
            RequestDispatcher rd = req.getRequestDispatcher("../board/BoardListAction.do");
            rd.forward(req, resp);
        }

        else if (command.contains("/BoardViewAction.do")) { // 선택된 글 상세 페이지 가져오기
            requestBoardView(req);
            requestRippleList(req);
            RequestDispatcher rd = req.getRequestDispatcher("../board/BoardView.do");
            rd.forward(req, resp);
        }
        else if (command.contains("/BoardView.do")) { // 글 상세 페이지 출력하기
            RequestDispatcher rd = req.getRequestDispatcher("../board/view.jsp");
            rd.forward(req, resp);
        }

        else if(command.contains("/BoardUpdateAction.do")){ //글 수정하기
            requestBoardUpdate(req);
            RequestDispatcher rd = req.getRequestDispatcher("../board/BoardListAction.do");
            rd.forward(req, resp);
        }

        else if(command.contains("/BoardDeleteAction.do")) {//선택된 글 삭제하기
            requestBoardDelete(req);
            RequestDispatcher rd = req.getRequestDispatcher("../board/BoardListAction.do");
            rd.forward(req, resp);
        }

        else if(command.contains("/BoardUpdateForm.do")){ //글 수정폼 출력
            //
            requestBoardView(req);
            RequestDispatcher rd = req.getRequestDispatcher("../board/updateForm.jsp");
            rd.forward(req, resp);
        }

        else if (command.contains("/BoardRippleWriteAction.do")) {//리플쓰기
            requestBoardRippleWrite(req);
            String num = req.getParameter("num");
            String pageNum = req.getParameter("pageNum");
            resp.sendRedirect("BoardViewAction.do?num=" + num + "&pageNum=" + pageNum);
        }
        else if(command.contains("/BoardRippleDeleteAction.do")){
            //리플삭제
            requestBoardRippleDelete(req);
            String num = req.getParameter("num");
            String pageNum = req.getParameter("pageNum");
            resp.sendRedirect("BoardViewAction.do?num=" + num + "&pageNum=" + pageNum);
        }
        else if (command.contains("RippleDeleteAction.do")) {
        	requestRippleDelete(req, resp);
        }
        else if (command.contains("RippleWriteAction.do")) {
        	requestRippleWrite(req, resp);
        }
        else if (command.contains("RippleListAction.do")) {
        	requestRippleList(req, resp);
        }
        else {
        	System.out.println("out : " + command);
        	// 결과 화면을 출력 스트림을 통해 출력
            PrintWriter out = resp.getWriter();
            out.append("<html><body><h2>잘못된 경로 입니다.("+ command + "</h2><hr>");
        }
    }


		//등록된 글 목록 가져오기
        public void requestBoardList (HttpServletRequest req){

            BoardDAO dao = BoardDAO.getInstance();
            List<BoardDTO> boardlist = new ArrayList<BoardDTO>();

            int pageNum = 1;   //페이지 번호가 전달이 안 되면 1페이지
            int limit = LISTCOUNT;   //페이지 당 게시물 수

            if (req.getParameter("pageNum") != null) //페이지 번호가 전달된 경우
                pageNum = Integer.parseInt(req.getParameter("pageNum"));

            String items = req.getParameter("items");   //검색 필드
            String text = req.getParameter("text");      //검색어

            int total_record = dao.getListCount(items, text);   //전체 게시물 수(건수라서 int)
            boardlist = dao.getBoardList(pageNum, limit, items, text);   //현재 페이지에 해당하는 목록 데이터 들고 오기

            int total_page;      //전체 페이지

            if (total_record % limit == 0) {   //전체 게시물이 limit의 배수일 때 페이지 수
                total_page = total_record / limit;
                Math.floor(total_page);
            } else {   //나머지 남을 때 페이지 수
                total_page = total_record / limit;
                Math.floor(total_page);
                total_page = total_page + 1;
            }

            //view에 보여줄 거 정리하는 역할?
            req.setAttribute("limit", limit);
            req.setAttribute("pageNum", pageNum);         //페이지 번호
            req.setAttribute("total_page", total_page);      //전체 페이지 수
            req.setAttribute("total_record", total_record);   //전체 게시물 수
            req.setAttribute("boardlist", boardlist);      //현재 페이지에 해당하는 목록 데이터
        }

        //인증된 사용자명 가져오기
//        public void requestLoginName(HttpServletRequest req){
//
//            String id = req.getParameter("id");   //이렇게 하는 건 보안 상 안 좋고 session으로 받아야 함
//
//            BoardDAO dao = BoardDAO.getInstance();
//
//            String name = dao.getLoginNameById(id); //이름으로 된 문자열 반환하도록 만들어보기
//
//            req.setAttribute("name", name);
//        }

        //새로운 글 등록하기
        public void requestBoardWrite (HttpServletRequest req){

            BoardDAO dao = BoardDAO.getInstance();

            BoardDTO board = new BoardDTO();

            board.setId(req.getParameter("id"));
            board.setName(req.getParameter("name"));
            board.setSubject(req.getParameter("subject"));
            board.setContent(req.getParameter("content"));

            System.out.println(req.getParameter("name"));
            System.out.println(req.getParameter("subject"));
            System.out.println(req.getParameter("content"));
//            java.text.simpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy/MM/dd(HH:mm:ss)");
//            String regist_day = formatter.format(new java.util.Date());


            board.setHit(0);
//            board.setRegist_day(regist_day);
            board.setIp(req.getRemoteAddr());

            dao.insertBoard(board);
        }


    // 선택된 글 상세 페이지 가져오기
    public void requestBoardView(HttpServletRequest request) {
        BoardDAO dao = BoardDAO.getInstance();
        int num = Integer.parseInt(request.getParameter("num"));
        int pageNum = Integer.parseInt(request.getParameter("pageNum"));

        BoardDTO board = new BoardDTO();
        board = dao.getBoardByNum(num, pageNum);

        request.setAttribute("num", num);
        request.setAttribute("page", pageNum);
        request.setAttribute("board", board);
    }

    //선택된 글 내용 수정하기
    public void requestBoardUpdate(HttpServletRequest req) {

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
    //선택된 글 삭제하기
    public void requestBoardDelete(HttpServletRequest req){
        int num = Integer.parseInt(req.getParameter("num"));
        int pageNum = Integer.parseInt(req.getParameter("pageNum"));

        BoardDAO dao = BoardDAO.getInstance();
        dao.deleteBoard(num);
    }

    public void requestBoardRippleWrite(HttpServletRequest request) throws UnsupportedEncodingException {
        /* 댓글 등록 */
        int num = Integer.parseInt(request.getParameter("num"));
        RippleDAO dao = RippleDAO.getInstance();
        RippleDTO ripple = new RippleDTO();
        request.setCharacterEncoding ("utf-8");
        HttpSession session = request.getSession();
        ripple.setBoardName(this.boardName);
        ripple.setBoardNum(num);
        ripple.setMemberId((String) session.getAttribute("sessionId"));
        ripple.setName(request.getParameter("name"));
        ripple.setContent(request.getParameter("content"));

        ripple.setIp(request.getRemoteAddr());
        dao.insertRipple(ripple);
    }
    public void requestRippleList(HttpServletRequest req){
        //댓글 목록 가져오기
        RippleDAO dao =RippleDAO.getInstance();
        List<RippleDTO> rippleList = new ArrayList<>();
        int num = Integer.parseInt(req.getParameter("num"));

        rippleList = dao.getRippleList(this.boardName, num);
        req.setAttribute("rippleList", rippleList);
    }

    public void requestBoardRippleDelete(HttpServletRequest req) {
        //댓글 삭제
        int rippleId = Integer.parseInt(req.getParameter("rippleId"));

        RippleDAO dao = RippleDAO.getInstance();
        RippleDTO ripple = new RippleDTO();
        ripple.setRippleId(rippleId);
        dao.deleteRipple(ripple);
    }
    
    public void requestRippleDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    	
    	int rippleId = Integer.parseInt(req.getParameter("rippleId"));
        RippleDAO dao = RippleDAO.getInstance();
        RippleDTO ripple = new RippleDTO();
        ripple.setRippleId(rippleId);
        
        String result = "{\"result\" :";
        if (dao.deleteRipple(ripple)) {
        	result += "\"true\"}";
        }
        else {
        	result += "\"false\"}";
        }
        // 결과 화면을 출력 스트림을 통해 출력
        PrintWriter out = resp.getWriter();
        out.append(result);
	}
    
    private void requestRippleWrite(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    	RippleDAO dao = RippleDAO.getInstance();
        RippleDTO ripple = new RippleDTO();
        HttpSession session = req.getSession();
        
    	req.setCharacterEncoding("UTF-8");
    	ripple.setBoardName(req.getParameter("boardName"));
    	ripple.setBoardNum(Integer.parseInt(req.getParameter("num")));
    	ripple.setMemberId((String) session.getAttribute("sessionId"));
    	ripple.setName(req.getParameter("name"));
    	ripple.setContent(req.getParameter("content"));
    	ripple.setIp(req.getRemoteAddr());
    	System.out.println(req.getParameter("boardName"));
    	
    	String result = "{\"result\" :";
        if (dao.insertRipple(ripple)) {
        	result += "\"true\"}";
        }
        else {
        	result += "\"false\"}";
        }
        // 결과 화면을 출력 스트림을 통해 출력
        PrintWriter out = resp.getWriter();
        out.append(result);
	}
    
 public void requestRippleList(HttpServletRequest req, HttpServletResponse resp) throws IOException{
    	
    	req.setCharacterEncoding("UTF-8");
    	
        HttpSession session = req.getSession();
        String sessionId = (String) session.getAttribute("sessionId");
        
        String boardName = req.getParameter("boardName");
        int num = Integer.parseInt(req.getParameter("num"));
        
    	RippleDAO dao = RippleDAO.getInstance();
    	ArrayList<RippleDTO> list = dao.getRippleList(boardName, num);
    	
    	StringBuilder result = new StringBuilder("{\"listData\":  [");
    	int i = 0;
    	for (RippleDTO dto : list) {
    		boolean flag = sessionId != null && sessionId.equals(dto.getMemberId()) ? true : false;
    		result.append("{\"rippleId\" : \"")
			.append(dto.getRippleId())
			.append("\", \"name\" : \"")
			.append(dto.getName())
    		.append("\", \"content\" : \"")
			.append(dto.getContent())
			.append("\", \"isWriter\" : \"")
			.append(flag)
			.append("\" }");
    		// value가 배열 형태로 들어가서 마지막 요소의 경우에는 콤마가 나오면 안됨.
    		
    		if (i++ < list.size() - 1)
    			result.append(", ");
    	}
    	result.append("]}");
    	
    	// 결과 화면을 출력 스트림을 통해 출력
        PrintWriter out = resp.getWriter();
        out.append(result.toString());
    }
    
}


