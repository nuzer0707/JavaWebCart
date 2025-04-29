package cart.service.impl;

import java.util.List;

import cart.dao.ProductDAO;
import cart.dao.impl.ProductDAOImpl;
import cart.model.dto.ProductDTO;
import cart.model.dto.UserDTO;
import cart.model.entity.Product;
import cart.model.entity.User;
import cart.service.ProductService;

public class ProductServiceImpl implements ProductService {
	
	private ProductDAO productDAO = new ProductDAOImpl();
	
	@Override
	public List<ProductDTO> findAllProducts() {
		// 1.從 userListDAO 取得 List<User>
				List<Product> products = productDAO.findAllProducts();
				
				// 2.將 List<User> 每一個元素由 User 轉成 UserDTO 使之變成 List<UserDTO>
				List<ProductDTO> productDTOs = products.stream()
											  .map(product -> {
												  ProductDTO productDTO = new ProductDTO();
												  productDTO.setProductId(product.getProductId());
												  productDTO.setProductName(product.getProductName());
												  productDTO.setPrice(product.getPrice());
												  productDTO.setQty(product.getQty());
												  productDTO.setImageBase64(product.getImageBase64());
												  productDTO.setTotal(product.getPrice()*product.getQty());
												  return productDTO;
											  })
											  .toList();
				return productDTOs;
	}

	@Override
	public void add(String productName, String price, String qty, String productImageBase64) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer productId) {
		// TODO Auto-generated method stub
		
	}

	
	
}
