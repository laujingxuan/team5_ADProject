package nus.edu.iss.adproject.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity 
public class RoomType {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@OneToOne
	private Product product;
	
//	@ManyToOne
	//private Hotel hotel;
	private String htel;
	
	private String roomType;
	private String description;
	private String imageURL;
	private double price;



	public RoomType(String htel,  String roomType, String description, String imageURL, double price) {
		super();
	
	//	this.product = product;
		this.htel = htel;
		this.roomType = roomType;
		this.description = description;
		this.imageURL = imageURL;
		this.price=price;
	}
	public RoomType() {
		super();
		// TODO Auto-generated constructor stub
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
//	public Hotel getHotel() {
//		return hotel;
//	}
//	public void setHotel(Hotel hotel) {
//		this.hotel = hotel;
//	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}public String getHtel() {
		return htel;
	}
	public void setHtel(String htel) {
		this.htel = htel;
	}
	public double getprice() {
		return price;
	}
	public void setprice(double price) {
		this.price = price;
	}
	
	
	
}
