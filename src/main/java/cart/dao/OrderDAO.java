package cart.dao;

import java.util.List;

import cart.model.entity.Order;
import cart.model.entity.OrderItem;

public interface OrderDAO {
		
		//建立訂單 回傳訂單編號
		Integer addOrder(Integer userId);
		
		void addOrderItem(Integer orderId,Integer productId ,Integer quantity );
		
		List<Order> findAllOrderByUserId(Integer userId);
		
		List<OrderItem> findAllOrderByOrderId(Integer orderId);
}
