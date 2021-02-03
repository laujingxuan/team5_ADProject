
package nus.edu.iss.adproject.service;

import nus.edu.iss.adproject.model.Attraction;

public interface AttractionService extends IService<Attraction> {

	Attraction findAttractionByProductId(Long id);
	
  public List<Attraction> findByUserId(Long userId);

  
}
