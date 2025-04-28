package cart.dao;

import java.util.List;

import cart.model.entity.User;


public interface UserListDAO {
	
	List<User> findAllUsers();
	
}
