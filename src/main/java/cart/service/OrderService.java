package cart.service;

import java.util.List;

import cart.model.dto.OrderDTO;
import cart.model.dto.ProductDTO;

public interface OrderService {

	void addOrder(Integer userId,List<ProductDTO> cart);
	
	List<OrderDTO> findAllOrderByUserId(Integer userId);
	
}
