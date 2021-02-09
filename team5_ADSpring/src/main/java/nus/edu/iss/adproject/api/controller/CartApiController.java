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
	
	@PostMapping("/add/{id}")
	public ResponseEntity<Cart> AdditemToCart(@PathVariable("id") Long productId,CartForm cartitem,HttpSession session){
		User user = (User) session.getAttribute("user");
		Cart newCart = new Cart(cartitem);
		newCart.setUser(user);
//		System.out.println("from page to select room: " + cartitem.getUser().getUserName());
		System.out.println("from page to select room: " + newCart.getEndDate());
		System.out.println("from page to select room: " + newCart.getStartDate());
		int total = cart_svc.add(productId, newCart.getStartDate(), newCart.getEndDate());
		return new ResponseEntity<Cart>(HttpStatus.OK);
	}
	// wrapper required 
	 @GetMapping("/list")
	 public ResponseEntity<Cart> listCart(HttpSession session){
		 User user = (User) session.getAttribute("user");
		 List<Cart> carts = cart_svc.findByUserId(user.getId());  
		 return new ResponseEntity<Cart>((Cart) carts,HttpStatus.OK);
	 }
	 @GetMapping("/delete/{id}")
	 ResponseEntity<Cart> RemoveCart(@PathVariable("id") Long id){
			Cart cartitem = cart_svc.findById(id);
			cart_svc.delete(cartitem);
			return new ResponseEntity<Cart>(HttpStatus.OK);
	 }
}
