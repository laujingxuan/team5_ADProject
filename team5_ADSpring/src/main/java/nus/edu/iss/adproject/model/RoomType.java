package nus.edu.iss.adproject.model;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity 
public class RoomType {
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@OneToOne (cascade = CascadeType.REMOVE)
	@JsonIgnore
	private Product product;

	@ManyToOne
	@JsonIgnore
	private Hotel hotel;

	//roomType is "SINGLE", "DOUBLE", "FAMILY" or etc
	private String roomType;
	private String description;
	private String imageURL;


	public RoomType() { }
	
	public RoomType(Product product, Hotel hotel, String roomType, String description, String imageURL) {
		super();
		this.product = product;
		this.hotel = hotel;
		this.roomType = roomType;
		this.description = description;
		this.imageURL = imageURL;
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
	public Hotel getHotel() {
		return hotel;
	}
	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
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
	}

	@Override
	public String toString() {
		return "RoomType [id=" + id + ", roomType=" + roomType
				+ ", description=" + description + ", imageURL=" + imageURL + "]";
	}

	
}
