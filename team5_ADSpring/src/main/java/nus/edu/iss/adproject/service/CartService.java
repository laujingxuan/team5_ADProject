package nus.edu.iss.adproject.service;

import java.util.List;

import nus.edu.iss.adproject.model.Cart;

public interface CartService {

	public List<Cart> retrieveByUserId(Long userId);
	public void deleteCart(Cart cart);
}
