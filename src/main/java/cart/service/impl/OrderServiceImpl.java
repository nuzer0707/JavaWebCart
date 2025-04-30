package cart.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cart.dao.OrderDAO;
import cart.dao.impl.OrderDAOImpl;
import cart.model.dto.OrderDTO;
import cart.model.dto.OrderItemDTO;
import cart.model.dto.ProductDTO;
import cart.model.entity.Order;
import cart.model.entity.OrderItem;
import cart.service.OrderService;

public class OrderServiceImpl implements OrderService{

	private OrderDAO orderDAO = new OrderDAOImpl();
	
	@Override
	public void addOrder(Integer userId, List<ProductDTO> cart) {
		Integer quantity =1;
		
		Integer orderId = orderDAO.addOrder(userId);
		
		for (ProductDTO productDTO : cart) {
			orderDAO.addOrderItem(orderId, productDTO.getProductId(), quantity);
		}
		
	}

	@Override
	public List<OrderDTO> findAllOrderByUserId(Integer userId) {
		
		List<OrderDTO> orderDTOs = new ArrayList<OrderDTO>();
		List<Order> orders = orderDAO.findAllOrderByUserId(userId);
		
		for(Order order : orders) {
			
			OrderDTO orderDTO = new OrderDTO();
			orderDTO.setOrderId(order.getOrderId());
			orderDTO.setUserId(order.getUserId());
			orderDTO.setOrderDate(order.getOderDate());
			
			for (OrderItem item : orderDAO.findAllOrderByOrderId(order.getOrderId())) {
			
				OrderItemDTO itemDTO = new OrderItemDTO();
				
				itemDTO.setItemId(item.getItemId());
				itemDTO.setOrderId(item.getOrderId());
				itemDTO.setProductId(item.getProductId());
				itemDTO.setQuantity(item.getQuantity());
				
				orderDTO.getItems().add(itemDTO);
			}
		}
		
		return orderDTOs;
	}

	
}
