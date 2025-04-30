package cart.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cart.dao.OrderDAO;
import cart.model.entity.Order;
import cart.model.entity.OrderItem;

public class OrderDAOImpl extends BaseDao implements OrderDAO {

	@Override
	public Integer addOrder(Integer userId) {

		String sql = "insert into `order` (User_id) values(?)";
		Integer orderId = null;
		// 因為後續要取得新增後自動生成的 order_id 所以要加上 Statement.RETURN_GENERATED_KEYS 參數設定
		try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			pstmt.setInt(1, userId);
			pstmt.executeUpdate();

			ResultSet generateKeys = pstmt.getGeneratedKeys();
			if (generateKeys.next()) {
				orderId = generateKeys.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return orderId;
	}

	@Override
	public void addOrderItem(Integer orderId, Integer productId, Integer quantity) {
		String sql = "insert into order_item(order_id,product_id , quantity )values(?,?,?,?)";
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			
			pstmt.setInt(1, orderId);
			pstmt.setInt(2, productId);
			pstmt.setInt(3, quantity);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
		
		sql = "updete product set qty = qty - ? where product_id = ?";
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			
			pstmt.setInt(1, quantity);
			pstmt.setInt(2, productId);
		
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Order> findAllOrderByUserId(Integer userId) {
		
		List<Order> orders = new ArrayList<Order>();
		String sql ="select order_id ,user_id,order_date from `order` where user_id = ?";
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			
			pstmt.setInt(1, userId);
			
			try (ResultSet rs=pstmt.executeQuery()){
				while (rs.next()) {
					Order order = new Order();
					order.setOrderId(rs.getInt("order_id"));
					order.setUserId(rs.getInt("user_id"));
					order.setOderDate(rs.getDate("order_date"));
					
					orders.add(order);
				}		
			} 			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orders;
	}

	@Override
	public List<OrderItem> findAllOrderByOrderId(Integer orderId) {
		
		List<OrderItem> items = new ArrayList<OrderItem>();
		String sql = "select item_id, order_id, product_id, quantity from order_item where order_id = ?";
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			
			pstmt.setInt(1, orderId);
			
			try (ResultSet rs=pstmt.executeQuery()){
				while (rs.next()) {
					OrderItem item = new OrderItem();
					item.setItemId(rs.getInt("item_id"));
					item.setOrderId(rs.getInt("order_id"));
					item.setProductId(rs.getInt("product_id"));
					item.setQuantity(rs.getInt("quantity"));
					
					items.add(item);
				}		
			} 			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return items;
	}



}
