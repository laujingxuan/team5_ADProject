package nus.edu.iss.adproject.service;

import java.util.List;

import nus.edu.iss.adproject.model.Product;

public interface ProductService {
	
	public Product findProductById(Long id);
	public List<Product> listAllSearchAttractions(String keyword);
	public List<Product> listAllSearchHotels(String keyword);
	
}
