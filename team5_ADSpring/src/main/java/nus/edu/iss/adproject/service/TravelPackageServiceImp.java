package nus.edu.iss.adproject.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nus.edu.iss.adproject.model.TravelPackage;
import nus.edu.iss.adproject.repository.TravelPackageRepository;

@Service
@Transactional
public class TravelPackageServiceImp implements TravelPackageService {

	@Autowired
	private TravelPackageRepository tpRepo;
	
	@Override
	public List<TravelPackage> retrieveAll() {
		return tpRepo.findAll();
	}

	@Override
	public void updatePackage(List<TravelPackage> travelPackages) {
		for (TravelPackage x : travelPackages) {
			tpRepo.save(x);
		}
		return;
	}

}
