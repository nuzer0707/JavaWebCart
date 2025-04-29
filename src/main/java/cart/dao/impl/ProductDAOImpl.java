package cart.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cart.dao.ProductDAO;
import cart.model.entity.Product;
import cart.model.entity.User;

public class ProductDAOImpl extends BaseDao implements ProductDAO {

	@Override
	public List<Product> findAllProducts() {
		List<Product> products = new ArrayList<Product>();

		String sql = "select product_id, product_name, price, qty, image_base64 from product";
		try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
			// rs 裡面存放的是資料表的內容
			// 利用 rs.next() 逐筆取出資料
			while (rs.next()) {
				// 建立 Product 物件來存放資料列每一個欄位的內容
				Product product = new Product();
				product.setProductId(rs.getInt("product_id"));
				product.setProductName(rs.getString("product_name"));
				product.setPrice(rs.getInt("price"));
				product.setQty(rs.getInt("qty"));
				product.setImageBase64(rs.getString("image_base64"));
				// 注入到 products 集合中保存
				products.add(product);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return products;
	}

	@Override
	public void add(Product product) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Integer productId) {
		// TODO Auto-generated method stub

	}

}
