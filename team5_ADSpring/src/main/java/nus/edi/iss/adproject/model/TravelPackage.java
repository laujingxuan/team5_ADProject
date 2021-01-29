package nus.edi.iss.adproject.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class TravelPackage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	
	private int numNights;
	
	private int numAttractions;
	
	@NotNull
	private double discountPercent;
	
	public TravelPackage() {
		super();
	}

	public TravelPackage(int numNights, int numAttractions, @NotNull double discountPercent) {
		super();
		this.numNights = numNights;
		this.numAttractions = numAttractions;
		this.discountPercent = discountPercent;
	}

	public int getNumNights() {
		return numNights;
	}

	public void setNumNights(int numNights) {
		this.numNights = numNights;
	}

	public int getNumAttractions() {
		return numAttractions;
	}

	public void setNumAttractions(int numAttractions) {
		this.numAttractions = numAttractions;
	}

	public double getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(double discountPercent) {
		this.discountPercent = discountPercent;
	}

	public long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "TravelPackage [id=" + id + ", numNights=" + numNights + ", numAttractions=" + numAttractions
				+ ", discountPercent=" + discountPercent + "]";
	}
	
}
