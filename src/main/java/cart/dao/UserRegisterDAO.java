package cart.dao;

import cart.model.entity.User;

public interface UserRegisterDAO {
	
	int addUser(User user);
	
	int emailConfirmOK(String username);
	
}

