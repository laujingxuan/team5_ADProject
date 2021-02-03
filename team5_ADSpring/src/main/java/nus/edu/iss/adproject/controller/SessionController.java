package nus.edu.iss.adproject.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import nus.edu.iss.adproject.model.User;
import nus.edu.iss.adproject.service.SessionService;
import nus.edu.iss.adproject.service.UserService;


@Controller
@RequestMapping("/user")
public class SessionController {

	@Autowired
	private UserService usvc;
	
	@Autowired
	private SessionService ssvc;
	
	//everyone can login
	@RequestMapping(path = "/login")
	public String login(Model model) {
		User u = new User();
		model.addAttribute("user", u);
		return "login";
	}
	
	@GetMapping("/logout")
	public ModelAndView logout(HttpSession session) {
		session.invalidate();
		ModelAndView mv = new ModelAndView("redirect:/user/login");
		return mv;
	}
	
	@RequestMapping(path = "/authenticate")
	public String authenticate(@ModelAttribute("user") User user, Model model, HttpSession session) {
		if(ssvc.authenticate(user)) 
		{
			User u = usvc.findByUsername(user.getUserName());
			session.setAttribute("user", u);
			return "redirect:/booking/list";
		}
		else
			return "login";
	}
	
}
