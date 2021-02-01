package nus.edu.iss.adproject.service;

import java.util.List;

// this is a generic interface for creating CRUD service implementations 
public interface IService<T> {
	
	// Create, Update 
	public void save(T x);
	
	// Read 
	public T findById(Long id);
	public List<T> findAll();
	//public T findByName(String name);
	
	// Delete 
	public void delete(T x);
}
