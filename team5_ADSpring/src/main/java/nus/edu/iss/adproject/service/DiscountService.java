package nus.edu.iss.adproject.service;

import java.util.List;

import nus.edu.iss.adproject.model.Discount;

public interface DiscountService extends IService<Discount> {
	public List<Discount> findDiscountByHotelUserId(Long userId);
	public List<Discount> findDiscountByAttractionUserId(Long userId);
}
