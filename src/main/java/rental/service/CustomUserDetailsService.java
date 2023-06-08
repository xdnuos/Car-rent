package rental.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import rental.entity.CustomUserDetails;
import rental.entity.User;

public class CustomUserDetailsService implements UserDetailsService{
    @Autowired
    private UserService userService;
     
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        System.out.print("ok ne");
        return new CustomUserDetails(user);
    }
}
