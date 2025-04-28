package cart.service.impl;

import cart.dao.UserRegisterDAO;
import cart.dao.impl.UserRegisterDAOImpl;
import cart.model.entity.User;
import cart.service.UserRegisterService;
import cart.util.HashUtil;

public class UserRegisterServiceImpl implements UserRegisterService {

	private UserRegisterDAO userRegisterDAO = new UserRegisterDAOImpl() ;

	@Override
	public void addUser(String username, String password, String email) {
		try {
			
			String hashSalt =HashUtil.generateSalt();
			String hashPassword = HashUtil.hashPassword(password, hashSalt);
			
			
			User user = new User();
			user.setUsername(username);
			user.setHashPassword(hashPassword);
			user.setHashSalt(hashSalt);
			user.setEmail(email);
			
			int rowcount = userRegisterDAO.addUser(user);
			if (rowcount < 1) {
					System.out.println("user 新增失敗");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void emailConfirmOK(String username) {
		if(username == null) {
			return;
		}
		
		userRegisterDAO.emailConfirmOK(username);
	}
		
		
	
	
}
