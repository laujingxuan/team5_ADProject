package nus.edu.iss.adproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import nus.edu.iss.adproject.service.CartService;
import nus.edu.iss.adproject.service.CartServiceImpl;
import nus.edu.iss.adproject.service.SessionService;
import nus.edu.iss.adproject.service.SessionServiceImpl;

	
@Controller
@RequestMapping("/cart")
public class CartController1 {
	
	@Autowired
	private CartService cart_svc;
	
	@Autowired
	private SessionService session_svc;
	//private long userId = session_svc.getUserId();
	
	@Autowired
	public void SetImplimentation(CartServiceImpl cart_svcimpl, SessionServiceImpl session_svcimpl) {
		this.cart_svc = cart_svcimpl;
		this.session_svc = session_svcimpl;
	}
	
	
	
}	

