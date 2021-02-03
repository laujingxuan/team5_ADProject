
package nus.edu.iss.adproject.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nus.edu.iss.adproject.model.Attraction;
import nus.edu.iss.adproject.repository.AttractionRepository;


@Service
@Transactional
public class AttractionServiceImpl implements AttractionService{
	@Autowired
	private AttractionRepository arepo;
	
    @Override
	public void save(Attraction x) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Attraction findById(Long id) {
		return arepo.findById(id).get();
	}

	@Override
	public void delete(Attraction x) {
		// TODO Auto-generated method stub
		
	}
	
	@Override 
	public List<Attraction> findAll(){
		return arepo.findAll();
	}
	
	@Override
	public Attraction findAttractionByProductId(Long id) {
		return arepo.findAttractionByProductId(id);
	}
  

	@Override
	public List<Attraction> findByUserId(Long userId) {
		return arepo.findByUserId(userId);
	}


}


