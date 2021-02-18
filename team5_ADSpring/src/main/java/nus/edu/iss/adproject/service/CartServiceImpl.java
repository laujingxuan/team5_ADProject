package nus.edu.iss.adproject.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nus.edu.iss.adproject.model.Booking;
import nus.edu.iss.adproject.model.BookingDetails;
import nus.edu.iss.adproject.model.Cart;
import nus.edu.iss.adproject.model.Product;
import nus.edu.iss.adproject.model.User;
import nus.edu.iss.adproject.repository.CartRepository;

@Service
@Transactional
public class CartServiceImpl implements CartService {
	@Autowired
	private CartRepository crepo;

	@Autowired
	private UserServiceImpl user_svcimpl;

	@Autowired
	private ProductServiceImpl product_svcimpl;

	@Autowired
	private SessionService session_svc;

	@Override
	public List<Cart> retrieveByUserId(Long userId) {
		return crepo.findCartsByUserId(userId);
	}

	@Override
	public int getQuantityByUserId(long userId) {
		return crepo.getQuantityByUserId(userId);
	}


	 @Override 
	 public int getProductQuantity(long productId,long userId, LocalDate startDate) {
	 return crepo.getProductQuantity(productId, userId, startDate); 
	 }
	 

	@Override
	public void deleteCart(Cart cart) {
		crepo.delete(cart);
		return;

	}

	@Override
	public void save(Cart x) {
		crepo.save(x);
	}

	@Override
	public List<Cart> findAll() {
		return crepo.findAll();
	}

	@Override
	public Cart findById(Long id) {
		return crepo.findById(id).get();
	}

	@Override
	public List<Cart> findByUserId(long userId) {
		return crepo.findCartsByUserId(userId);
	} 

	@Override
	public Cart findByUserIdAndProductId(long userId, long productId) {
		return crepo.findByUserIdAndProductId(userId, productId);
	}

	@Override
	public void delete(Cart x) {
		crepo.delete(x);
	}
}