package rental.controller;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.util.WebUtils;

import rental.entity.User;
import rental.service.UserService;

@Controller
public class Login {
	private UserService userService;
	
	public Login(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
	    model.addAttribute("user", new User());
	    model.addAttribute("message","");
	    return "signUp";
	}
	@PostMapping("/process_register")
	public String processRegister(User user, SessionStatus status, Model model) {
	    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	    String encodedPassword = passwordEncoder.encode(user.getPassword());
	    user.setPassword(encodedPassword);
	    if(userService.checkEmail(user.getEmail())) {
	    	userService.save(user);
	    	status.setComplete();
	    } else {
	    	model.addAttribute("message", "Email đã tồn tại, vui lòng sử dụng email khác");
	    	return "signUp";
	    }
	    
	    return "register_success";
	}
	
	@GetMapping("/login")
	public String showLoginForm(Model model) {
	    return "login";
	}
	
	@GetMapping(value = "/403")
	public String accessDenied(Model model, Principal principal) {

		if (principal != null) {

			String message = "You do not have permission to access this page!";
			model.addAttribute("message", message);

		}

		return "403Page";
	}
}
