package cart.service;

import java.util.List;

import cart.model.dto.UserDTO;

public interface UserListService {
	
	List<UserDTO> findAllUsers();
}
