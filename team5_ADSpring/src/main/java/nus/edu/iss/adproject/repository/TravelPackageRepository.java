package nus.edu.iss.adproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import nus.edu.iss.adproject.model.TravelPackage;

public interface TravelPackageRepository extends JpaRepository<TravelPackage, Long> {

//	public TravelPackage findByNumNightsAndNumAttractions(int numNights, int numAttractions);
}
