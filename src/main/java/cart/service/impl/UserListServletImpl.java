package cart.service.impl;

import java.util.List;



import cart.dao.UserListDAO;
import cart.dao.impl.UserListDAOImpl;
import cart.model.dto.UserDTO;
import cart.model.entity.User;
import cart.service.UserListService;

public class UserListServletImpl  implements UserListService {

	private UserListDAO dao = new UserListDAOImpl();
	
	@Override
	public List<UserDTO> findAllUsers(){
		
		List<User>users = dao.findAllUsers();
		
		List<UserDTO> userDTOs = users.stream()
														.map(user ->{
																UserDTO userDTO = new UserDTO();
																userDTO.setId(user.getId());
																userDTO.setUsername(user.getUsername());
																userDTO.setEmail(user.getEmail());
																userDTO.setCompleted(user.getCompleted());
																return userDTO;
														})
														.toList();
	return userDTOs;
	}

}
