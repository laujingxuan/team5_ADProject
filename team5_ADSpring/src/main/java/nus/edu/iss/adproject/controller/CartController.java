package nus.edu.iss.adproject.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

//import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.minidev.json.JSONObject;
import nus.edu.iss.adproject.model.Cart;
import nus.edu.iss.adproject.model.User;
import nus.edu.iss.adproject.nonEntityModel.CartForm;
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

//	@InitBinder()
//	protected void initBinder(WebDataBinder binder) {
//		
//	}

	@ResponseBody
	@GetMapping("/test")
	public JSONObject getCartitemQuantity( Model model, HttpSession session) {
		 //String s = "{\"status\":\"success\", \"total\": 5}";
		
		  if (session_svc.isNotLoggedIn(session)) return null; User user = (User)
		  session.getAttribute("user"); Long userId = user.getId(); int quantity =
		  cart_svc.getQuantityByUserId(userId); System.out.println(quantity);
		  
		  //Map<String, Object> map=new HashMap<String,Object>(); 
		  //map.put("quantity",quantity);
		  
		  Map<String, Object> map=new HashMap<String,Object>(); 
		  map.put("status", "success");
		  map.put("total", quantity);
		  
		  JSONObject jsonObj=new JSONObject(map);
		  System.out.println("checkpoint B");
		  System.out.println("checkpoint A: " + jsonObj.toJSONString());
		
		//return jsonObj;
		return jsonObj;
	}
	
	@PostMapping("/add/{id}")
	public String AddItemToCart(@ModelAttribute("cartitem") @Valid CartForm cartitem, @PathVariable("id") Long productId, BindingResult bindingResult, Model model, HttpSession session) {
		if (session_svc.isNotLoggedIn(session)) return "redirect:/user/login";
		User user = (User) session.getAttribute("user");
		System.out.println("productId "+ productId);
		Cart newCart = new Cart(cartitem);
		newCart.setUser(user);
//		System.out.println("from page to select room: " + cartitem.getUser().getUserName());
		System.out.println("from page to select room: " + newCart.getEndDate());
		System.out.println("from page to select room: " + newCart.getStartDate());
		int total = cart_svc.add(productId, newCart.getStartDate(), newCart.getEndDate());
		return "forward:/product/list";
	}


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
    
	@GetMapping("/delete/{id}")
	public String deleteMethod(Model model, @PathVariable("id") Long id) {
		Cart cartitem = cart_svc.findById(id);
		cart_svc.delete(cartitem);
		return "forward:/cart/list";
	}
}
    //receive JSON data from Add.js. (When an item is added to the cart from gallery)
	
	/*
	@RequestMapping("/add")
    public JSONObject AddItemToCart([FromBody] Addinput product) {
        
        int total = cart_svc.add(product);
        
        return new JSONObject("{'status':'success', 'total': total}");
    }*/

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
   
/*
@GetMapping("/delete/{id}")
public String deleteMethod1(Model model, @PathVariable("id") Long id) {
	Cart cartitem = cart_svc.findById(id);
	cart_svc.delete(cartitem);
	return "forward:/cart/list";
}*/

    /*
    [HttpPost] 
    public String Checkout() { // checkout is form deletion from Cart and adding to PurchaseHistory

        List<Cart> carts = cart_svc.findByUserId(userId);
        cart_svc.checkout(carts);

        return "/";
    }*/
	
