package nus.edu.iss.adproject.nonEntityModel;

import java.util.ArrayList;
import java.util.List;

import nus.edu.iss.adproject.model.Attraction;

public class AttractionWrapper {
	List<Attraction> attractionList;

	
	
	public AttractionWrapper() {
		super();
		// TODO Auto-generated constructor stub
		attractionList = new ArrayList<Attraction>();
	}

	public AttractionWrapper(List<Attraction> attractionList) {
		super();
		this.attractionList = attractionList;
	}

	public List<Attraction> getAttractionList() {
		return attractionList;
	}

	public void setAttractionList(List<Attraction> attractionList) {
		this.attractionList = attractionList;
	}

	
}
