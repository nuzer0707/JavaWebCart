package cart.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import cart.model.dto.ProductDTO;
import cart.service.ProductService;
import cart.service.impl.ProductServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

//將商品加入購物車中暫存
//將所有訂購商品暫時存入 session

@WebServlet("/product/order/add/cart")
public class OrderAddCartServlet extends HttpServlet {

	private ProductService productService = new ProductServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();

		List<ProductDTO> cart = null;

		if (session.getAttribute("cart") == null) {
			cart = new ArrayList<>();
		} else {
			cart = (List<ProductDTO>) session.getAttribute("cart");
		}

		Integer productId = Integer.parseInt(req.getParameter("productId"));

		Optional<ProductDTO> optProductDTO = productService.findAllProducts()
				.stream()
				.filter(dto -> dto.getProductId().equals(productId)).findFirst();

		if (optProductDTO.isPresent()) {
			cart.add(optProductDTO.get());
			session.setAttribute("cart", cart);
		}

		resp.sendRedirect("/JavaWebCart/product/order");
		
		//System.out.println(session.getAttribute("cart"));
	}

}
