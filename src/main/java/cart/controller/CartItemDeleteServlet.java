package cart.controller;

import java.io.IOException;
import java.util.List;

import cart.model.dto.OrderDTO;
import cart.service.OrderService;
import cart.service.impl.OrderServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/product/cart/item/delete")
public class CartItemDeleteServlet extends HttpServlet {



	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int index = Integer.parseInt(req.getParameter("index"));
		HttpSession session = req.getSession();
		
		if (session.getAttribute("cart") != null) {
			List<OrderDTO> cart = (List<OrderDTO>) session.getAttribute("cart");
			cart.remove(index);
			
			session.setAttribute("cart", cart);
		}
		resp.sendRedirect("/JavaWebCart/product/cart");
	}

}
