package nus.edu.iss.adproject.service;

import java.util.List;

import nus.edu.iss.adproject.model.Cart;

public interface CartService extends IService<Cart>  {

	public List<Cart> retrieveByUserId(Long userId);
	public void deleteCart(Cart cart);
	
	List<Cart> findByUserId(long userId);
	Cart findByUserIdAndProductId(long userId, long productId);
}
