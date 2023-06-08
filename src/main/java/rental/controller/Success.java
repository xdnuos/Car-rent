package rental.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Success {
	@RequestMapping(value = "success")
	public String BookSuccess() {
		return "success";
	}
}
