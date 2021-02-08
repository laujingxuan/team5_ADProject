package nus.edu.iss.adproject.nonEntityModel;

import java.time.LocalDate;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import nus.edu.iss.adproject.model.Product;
import nus.edu.iss.adproject.model.User;

public class CartForm {
	

	private String startDate;
	
	private String endDate;

	
	
	public CartForm() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CartForm(String startDate, String endDate) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	
	
}
