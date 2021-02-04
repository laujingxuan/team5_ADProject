package nus.edu.iss.adproject.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nus.edu.iss.adproject.model.Hotel;
import nus.edu.iss.adproject.model.Product;
import nus.edu.iss.adproject.repository.ProductRepo;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	ProductRepo prepo;
	
	
	
	@Transactional
	public Product findProductById(Long id) {
		return prepo.findById(id).get();
	}
		
	
	@Transactional
	public List<Product> listAllSearchAttractions(String keyword){
		System.out.println(keyword);
		if(keyword!=null) {
			return prepo.search1(keyword);
		}
		return prepo.findAll();
	}
	
	@Transactional
	public List<Product> listAllSearchHotels(String keyword){
		System.out.println(keyword);
		if(keyword!=null) {
			return prepo.search2(keyword);
		}
		return prepo.findAll();
	}
	
	
}