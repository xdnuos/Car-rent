package rental.service;

import org.springframework.stereotype.Service;

import rental.repository.UserDAO;

import rental.entity.User;

@Service
public class UserServiceImpl implements UserService{
	private UserDAO userDAO;
	
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
    
    @Override
    public void save(User user) {
        this.userDAO.save(user);
    }
    
    @Override
    public User findByEmail(String email) {
    	return this.userDAO.findByEmail(email);
    }
    
    @Override
    public Boolean checkEmail(String email) {
    	if(this.userDAO.checkEmail(email)==0) {
    		return true;
    	}
    	return false;
    }
    	
}
