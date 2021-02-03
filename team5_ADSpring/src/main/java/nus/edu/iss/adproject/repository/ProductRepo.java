package nus.edu.iss.adproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import nus.edu.iss.adproject.model.Product;

public interface ProductRepo extends JpaRepository<Product, Long>{
	
}
