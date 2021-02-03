package nus.edu.iss.adproject.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nus.edu.iss.adproject.model.Cart;
import nus.edu.iss.adproject.repository.CartRepository;

@Service
@Transactional
public class CartServiceImp implements CartService {

	@Autowired
	private CartRepository cartRepo;

	@Override
	public List<Cart> retrieveByUserId(Long userId) {
		return cartRepo.findCartsByUserId(userId);
	}
	
	@Override
	public void deleteCart(Cart cart) {
		cartRepo.delete(cart);
		return;
	}
	
}
