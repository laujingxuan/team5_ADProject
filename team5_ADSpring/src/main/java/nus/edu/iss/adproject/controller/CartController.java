package nus.edu.iss.adproject.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

//import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import nus.edu.iss.adproject.model.Cart;
import nus.edu.iss.adproject.service.CartService;
import nus.edu.iss.adproject.service.CartServiceImpl;
import nus.edu.iss.adproject.service.SessionService;
import nus.edu.iss.adproject.service.SessionServiceImpl;

@Controller
@RequestMapping("/cart")
public class CartController {
	
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

	@InitBinder()
	protected void initBinder(WebDataBinder binder) {
		
	}
	
    //receive JSON data from Add.js. (When an item is added to the cart from gallery)
	
	/*
	@RequestMapping("/add")
    public JSONObject AddItemToCart([FromBody] Addinput product) {
        
        int total = cart_svc.add(product);
        
        return new JSONObject("{'status':'success', 'total': total}");
    }*/

    @GetMapping("/list")
    public String ListCartItems(Model model){    
    	//long userId = session_svc.getUserId();
    	long userId = 1;
          List<Cart> carts = cart_svc.findByUserId(userId);  
        
        System.out.println(carts.size());
        
        for (Cart c : carts) {
        	System.out.println(c);
        	System.out.println(c.toString());
        }
        	
        model.addAttribute("carts", carts);
        return "Cart_chloe";
    }

    /*
    @PostMapping("/save")
    public void ChangeCartItemQuantity([FromBody] ChangeInput change) { //receive JSON object from Cart.js when the number in the cart is changed
        
        Cart item = cart_svc.findByUserIdAndProductId(userId, change.ProductId);
        item.setQuantity(new_qty);
        cart_svc.save(item);
        
        return null;
    }*/

    /*
    [HttpPost]
    public JSONObject DeleteCartItem([FromBody] Addinput item) {
        
        Cart item = cart_svc.findByUserIdAndProductId(userId, change.ProductId);
        cart_svc.delete(item);

        return new JSONObject("{'status':'success'}");
        }
        */
    
	@GetMapping("/delete/{id}")
	public String deleteMethod(Model model, @PathVariable("id") Long id) {
		Cart cartitem = cart_svc.findById(id);
		cart_svc.delete(cartitem);
		return "forward:/cart/list";
	}

    /*
    [HttpPost] 
    public String Checkout() { // checkout is form deletion from Cart and adding to PurchaseHistory

        List<Cart> carts = cart_svc.findByUserId(userId);
        cart_svc.checkout(carts);

        return "/";
    }*/
	
}