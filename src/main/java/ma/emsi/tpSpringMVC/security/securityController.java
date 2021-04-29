package ma.emsi.tpSpringMVC.security;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class securityController {
	
	@GetMapping("/notAuthorized")
	public String errors() {
		return "notAuthorized" ; 
	}
	
	@GetMapping("/login")
	public String login() {
		return "login" ;
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) throws ServletException {
		request.logout();
		return "redirect:/login" ;
	}
	
	

}
