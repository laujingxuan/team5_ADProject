package nus.edu.iss.adproject.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		
	
}