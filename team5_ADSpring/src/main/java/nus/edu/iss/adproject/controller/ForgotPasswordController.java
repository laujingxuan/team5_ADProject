package nus.edu.iss.adproject.controller;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import net.bytebuddy.utility.RandomString;
import nus.edu.iss.adproject.nonEntityModel.Utility;
import nus.edu.iss.adproject.service.CustomerNotFoundException;
import nus.edu.iss.adproject.service.UserService;

@Controller
public class ForgotPasswordController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JavaMailSender mailSender;
	
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
			String resetPasswordLink = Utility.getSiteURL(request)+"/reset_password?token="+token;
			sendEmail(email,resetPasswordLink);
		} catch (CustomerNotFoundException ex) {
			model.addAttribute("error",ex.getMessage());
		} catch (UnsupportedEncodingException | MessagingException e) {
			model.addAttribute("error","Error while sending email");
		} 
		
		return "forgot-password-form";
	}
	
	private void sendEmail(String email,String resetPasswordLink) throws UnsupportedEncodingException, MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		
		helper.setFrom("contact@shopme.com","Shopme Support");
		helper.setTo(email);
		
		String subject = "Here's the link to reset your password";
		
		String content = "<p>Hello,<p>"
				+"<p>You have requested to reset your password.<p>"
				+"<p>Click the link below to change your password:<p>"
				+"<p><b><a href=\""+resetPasswordLink+"\">Change my password</a></b><p>"
				+"<p>Ignore this email if you do remember your password, or you have not made the request.<p>";
		
		helper.setSubject(subject);
		helper.setText(content,true);
		
		mailSender.send(message);
		
	}
}
