package mvc.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import market.ver01.dao.CartDAO;
import market.ver01.dao.ProductDAO;
import market.ver01.dto.CartDTO;
import market.ver01.dto.Product;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(urlPatterns = {"/SmartMarket/shop_db/addCart.jsp", "/SmartMarket/shop_db/cart.jsp"})
public class CartController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String RequestURI = req.getRequestURI(); // 전체 경로를 가져옴.
		String contextPath = req.getContextPath(); // 프로젝트 Path만 가져옴
		String command = RequestURI.substring(contextPath.length()); // 전체경로에서 프로젝트 Path 길이 만큼의 인덱스 이후의 문자열을 가져옴.
		
		resp.setContentType("text/html; charset-utf-8");
		req.setCharacterEncoding("utf-8");
		
		System.out.println("cart command : " +command);
		
		if (command.contains("addCart.jsp")) { //장바구니 담기
			// 파라미터로 넘어온 아이디 값을 확인
			   String productId = req.getParameter("id");
			   if (productId == null || productId.trim().equals("")) {
				   resp.sendRedirect("products.jsp");
			      return;
			   }
			   
			   HttpSession session = req.getSession();
			   String orderNo= session.getId();
			   String memberId = (String) session.getAttribute("sessionId");
			   ProductDAO productDAO = new ProductDAO();
			   CartDAO cartDAO = new CartDAO();
			   
				 Product productDTO = productDAO.getProductById(productId);
			   if (productDTO == null) {
				   resp.sendRedirect("exceptionNoProductId.jsp");
			   }
			   
			   boolean flag = cartDAO.updateCart(productDTO, orderNo, memberId);
			   
			   resp.sendRedirect("product.jsp?id=" + productId);
		}
		else if (command.contains("cart.jsp")) {
			CartDAO cartDAO = new CartDAO();
			
			HttpSession session = req.getSession();
			String orderNo = session.getId();
			
			ArrayList<CartDTO> carts = cartDAO.getCartList(orderNo);
			req.setAttribute("carts", carts);
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/SmartMarket/shop_db/@cart.jsp");
			requestDispatcher.forward(req, resp);
		}
	}

}
