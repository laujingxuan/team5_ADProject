package nus.edu.iss.adproject.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nus.edu.iss.adproject.model.Attraction;
import nus.edu.iss.adproject.repository.AttractionRepository;

@Service
@Transactional
public class AttractionServiceImpl implements AttractionService {
	@Autowired
	private AttractionRepository attraction_repo;

	
	@Override
	public void save(Attraction x) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Attraction findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Attraction> findAll() {
		return attraction_repo.findAll();
	}

	@Override
	public void delete(Attraction x) {
		// TODO Auto-generated method stub
		
	}

}
