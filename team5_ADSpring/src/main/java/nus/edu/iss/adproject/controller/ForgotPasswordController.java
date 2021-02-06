package nus.edu.iss.adproject.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import net.bytebuddy.utility.RandomString;
import nus.edu.iss.adproject.service.CustomerNotFoundException;
import nus.edu.iss.adproject.service.UserService;

@Controller
public class ForgotPasswordController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/forgot_password")
	public String showForgotPasswordForm(Model model) {
		model.addAttribute("pageTitle","Forgot Password");
		return "forgot-password-form";
	}
	
	@PostMapping("/forgot_password")
	public String processForgotPasswordForm(HttpServletRequest request,Model model) {
		String email  =request.getParameter("email");
		String token = RandomString.make(45);
		try {
			userService.updateResetPasswordToken(token, email);
		} catch (CustomerNotFoundException ex) {
			model.addAttribute("error",ex.getMessage());
		}
		
		return "forgot-password-form";
	}
}
