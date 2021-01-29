package nus.edi.iss.adproject.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity 
public class RoomType {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private long hotel_id;
	private String roomType;
	private String description;
	private String imageURL;
	public RoomType( long hotel_id, String roomType, String description, String imageURL) {
		this.hotel_id = hotel_id;
		this.roomType = roomType;
		this.description = description;
		this.imageURL = imageURL;
	}
	
	public long getid() {	
		return id;
	}
	
	public long getHotel_id() {
		return hotel_id;
	}
	public void setHotel_id(long hotel_id) {
		this.hotel_id = hotel_id;
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
	
	
	
}
