package nus.edu.iss.adproject.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nus.edu.iss.adproject.model.Product;
import nus.edu.iss.adproject.repository.ProductRepo;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	ProductRepo prepo;
	
	@Override
	public void save(Product x) {
		prepo.save(x);
	}
	
	@Override
	public Product findProductById(Long id) {
		return prepo.findById(id).get();
	}
		
	
	@Override
	public List<Product> listAllSearchAttractions(String keyword){
			return prepo.search1(keyword);
	}
	
	@Override
	public List<Product> listAllSearchHotels(String keyword){
			return prepo.search2(keyword);
	}

	@Override
	public void deleteProduct(Product room) {
		prepo.delete(room);
	}

	@Override
	public Product findById(Long id) {
		return prepo.findById(id).get();
	}

	@Override
	public List<Product> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Product x) {
		// TODO Auto-generated method stub
		
	}
	
	
}