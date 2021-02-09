package nus.edu.iss.adproject.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nus.edu.iss.adproject.model.Discount;
import nus.edu.iss.adproject.repository.DiscountRepository;

@Service
@Transactional
public class DiscountServiceImpl implements DiscountService {
	@Autowired
	private DiscountRepository discount_repo;

	@Override
	public void save(Discount x) {
		discount_repo.save(x);
		
	}

	@Override
	public Discount findById(Long id) {
		return discount_repo.findById(id).get();
	}

	@Override
	public List<Discount> findAll() {
		// TODO Auto-generated method stub
		return discount_repo.findAll();
	}

	@Override
	public void delete(Discount x) {
		discount_repo.delete(x);
		
	}

	@Override
	public List<Discount> findDiscountByHotelUserId(Long userId) {
		return discount_repo.findHotelByUserId(userId);
	}

	@Override
	public List<Discount> findDiscountByAttractionUserId(Long userId) {
		return discount_repo.findAttractionByUserId(userId);
	}
	
	@Override
	public List<Discount> findDiscountByHotelId(Long hotelId){
		return discount_repo.findDiscountByHotelId(hotelId);
	}

	@Override
	public List<Discount> findDiscountByAttractionId(Long attractionId){
		return discount_repo.findDiscountByAttractionId(attractionId);
	}
}
