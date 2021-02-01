package nus.edu.iss.adproject.service;

import java.util.List;

import nus.edu.iss.adproject.model.TravelPackage;

public interface TravelPackageService {

	public List<TravelPackage> retrieveAll();
	public void updatePackage(List<TravelPackage> travelPackages);
}
