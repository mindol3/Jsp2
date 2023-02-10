package mvc.controller;

import market.ver01.dao.CartDAO;
import market.ver01.dto.CartDTO;
import mvc.model.OrderDAO;
import mvc.model.OrderDataDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/web_jsp/market/order/*")
public class OrderController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String RequestURI = req.getRequestURI();
        String contextPath = req.getContextPath();
        String command = RequestURI.substring(contextPath.length());

        resp.setContentType("text/html; charset=utf-8");
        req.setCharacterEncoding("utf-8");

        System.out.println("command : " + command);

        if (command.contains("/form.do")) {//주문서 / 배송 정보 입력 페이지
            setOrderData(req);
            //상단에 장바구니 출력
//            ArrayList<OrderDataDTO> datas = getOrderData(getOrderNo(req));
//            req.setAttribute("datas", datas);
//            req.getRequestDispatcher("/WEB-INF/order/form.jsp").forward(req, resp);
        }
    }


    private String getOrderNo(HttpServletRequest req) {
        /*주문 번호 반환
         * 1. 주문번호 사용 때문에 코드 반복이 되어서
         * 2. 주문번호 체계가 변할 경우를 대비해 메서드화*/
        HttpSession session = req.getSession();
        return session.getId();
    }

    private void setOrderData(HttpServletRequest request) {
        /* 장바구니에 있는 상품을 주문데이터에 복사
        결제 금액을 장바구니가 아니라 주문데이터 기준으로 계산.
        */
        OrderDAO dao = OrderDAO.getInstance();
        // 주문 번호 가져오기
        String orderNo = getOrderNo(request);
        System.out.println("orderNo = " + orderNo);
        // 1. 중복을 막기 위해 주문번호로 저장된 데이터 삭제
        dao.clearOrderData(orderNo);
        // 2. 주문번호 기준으로 장바구니에 있는 상품을 가지고 옴
        CartDAO cartDAO = new CartDAO();
        ArrayList<CartDTO> carts = cartDAO.getCartList(orderNo);
        System.out.println("carts = " + carts);
        // 3. CartList를 OrderData List로 변경
        ArrayList<OrderDataDTO> dtos= changeCartData(carts, orderNo);
        System.out.println("dtos = " + dtos);

        // 4. OrderData List를 데이터 베이스에 저장
        for(OrderDataDTO dto : dtos) {
            dao.insertOrderData(dto);
        }
    }

    private ArrayList<OrderDataDTO> changeCartData(ArrayList<CartDTO> carts, String orderNo) {
        ArrayList<OrderDataDTO> datas = new ArrayList<>();
        for(CartDTO cart : carts){
            OrderDataDTO dto = new OrderDataDTO();
            dto.setOrderNo(orderNo);
            dto.setCartId(cart.getP_cartId());
            dto.setProductId(cart.getP_productId());
            dto.setProductName(cart.getP_name());
            dto.setUnitPrice(cart.getP_unitPrice());
            dto.setCnt(cart.getP_cnt());
            dto.setSumPrice(cart.getP_unitPrice() * cart.getP_cnt());
            datas.add(dto);
        }
        return datas;
    }

//    private ArrayList<OrderDataDTO> getOrderData(String orderNo) {
//    }

}
