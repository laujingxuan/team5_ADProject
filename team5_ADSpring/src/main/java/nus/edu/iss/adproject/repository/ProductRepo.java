package nus.edu.iss.adproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import nus.edu.iss.adproject.model.Product;

public interface ProductRepo extends JpaRepository<Product, Long>{

}
