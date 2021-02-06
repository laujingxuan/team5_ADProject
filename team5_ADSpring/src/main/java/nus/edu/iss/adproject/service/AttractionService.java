
package nus.edu.iss.adproject.service;

import java.util.List;

import nus.edu.iss.adproject.model.Attraction;

public interface AttractionService extends IService<Attraction> {

	public Attraction findAttractionByProductId(Long id);
	
	public List<Attraction> findByUserId(Long userId);

	public List<Attraction> findAll();
	public void delete(Attraction A);
	public void save(Attraction a);

  
}
