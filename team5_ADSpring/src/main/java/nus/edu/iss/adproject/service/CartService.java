package nus.edu.iss.adproject.service;

import java.time.LocalDate;
import java.util.List;

import nus.edu.iss.adproject.model.Cart;

public interface CartService extends IService<Cart>  {

	public List<Cart> retrieveByUserId(Long userId);
	public void deleteCart(Cart cart);
	
	public int getQuantityByUserId(long userId);
	public int getProductQuantity(long productId,long userId, LocalDate startDate);
	
	List<Cart> findByUserId(long userId);
	Cart findByUserIdAndProductId(long userId, long productId);
	
}
