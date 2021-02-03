
package nus.edu.iss.adproject.nonEntityModel;


import java.util.List;

import nus.edu.iss.adproject.model.TravelPackage;

public class TravelPackageWrapper {

	private List<TravelPackage> travelPackages;
	
	public TravelPackageWrapper(List<TravelPackage> travelPackages) {
		super();
		this.travelPackages = travelPackages;
	}

	public List<TravelPackage> getTravelPackages() {
		return travelPackages;
	}

	public void setTravelPackages(List<TravelPackage> travelPackages) {
		this.travelPackages = travelPackages;
	}

	@Override
	public String toString() {
		return "TravelPackageWrapper [travelPackages=" + travelPackages + "]";
	}
	
}
