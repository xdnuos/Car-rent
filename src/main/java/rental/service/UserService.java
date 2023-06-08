package rental.service;

import rental.entity.User;

public interface UserService {
	void save(User user);

	User findByEmail(String email);
	
	Boolean checkEmail(String email);
}
