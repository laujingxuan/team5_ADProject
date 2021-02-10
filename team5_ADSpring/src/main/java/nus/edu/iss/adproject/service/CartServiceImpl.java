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

//	@Autowired
//	private CartRepository cartRepo;

//	@Autowired
//	private BookingService booking_svc;

	@Autowired
	private UserServiceImpl user_svcimpl;

	@Autowired
	private ProductServiceImpl product_svcimpl;

	@Autowired
	private SessionService session_svc;

	@Override
	public List<Cart> retrieveByUserId(Long userId) {
		// return cartRepo.findCartsByUserId(userId);
		return crepo.findCartsByUserId(userId);
	}

	@Override
	public int getQuantityByUserId(long userId) {
		// return cartRepo.findCartsByUserId(userId);
		return crepo.getQuantityByUserId(userId);
		// System.out.println(retrieveByUserId(userId).size());
	}


	 @Override 
	 public int getProductQuantity(long productId,long userId, LocalDate startDate) {
	 //return cartRepo.findCartsByUserId(userId); 
	 return crepo.getProductQuantity(productId, userId, startDate); 
	 //System.out.print();
	 }
	 

	@Override
	public void deleteCart(Cart cart) {
		// cartRepo.delete(cart);
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

	/*
	 * public void checkout(List<Cart> carts) { // Cart(long id, Product product,
	 * int quantity, User user) // Booking(User user, int amountPaid, Date
	 * bookingDate, int travelPackageDiscount) // BookingDetails(long id, int
	 * numOfGuest, int price)
	 * 
	 * for (Cart item : carts) { Booking booking = new Booking( item.getUser(),
	 * this.amountPaid = amountPaid this.bookingDate = bookingDate;
	 * this.travelPackageDiscount = travelPackageDiscount; );
	 * booking_svc.save(booking);
	 * 
	 * BookingDetails booking_details = new BookingDetails( item.getUser(),
	 * this.amountPaid = amountPaid this.bookingDate = bookingDate;
	 * this.travelPackageDiscount = travelPackageDiscount; );
	 * 
	 * bookingdetails_svc.save(booking_details);
	 * 
	 * delete(item); } }
	 */

}