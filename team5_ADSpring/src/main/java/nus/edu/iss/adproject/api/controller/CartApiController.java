package nus.edu.iss.adproject.api.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nus.edu.iss.adproject.model.Cart;
import nus.edu.iss.adproject.model.User;
import nus.edu.iss.adproject.nonEntityModel.CartForm;
import nus.edu.iss.adproject.service.CartService;
import nus.edu.iss.adproject.service.CartServiceImpl;

@RestController
@CrossOrigin
@RequestMapping("/api/cart")
public class CartApiController {
	
	@Autowired
	private CartService cart_svc;
	
	@Autowired
	public void SetImplimentation(CartServiceImpl cart_svcimpl) {
		this.cart_svc = cart_svcimpl;
	}
	

}
