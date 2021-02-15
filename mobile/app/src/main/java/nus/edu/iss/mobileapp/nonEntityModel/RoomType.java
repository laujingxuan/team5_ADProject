package nus.edu.iss.mobileapp.nonEntityModel;

import java.io.Serializable;

public class RoomType implements Serializable {
    private long id;

    //roomType is "SINGLE", "DOUBLE", "FAMILY" or etc
    private String roomType;
    private String description;
    private String imageURL;
    private Hotel hotel;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    @Override
    public String toString() {
        return "RoomType{" +
                "id=" + id +
                ", roomType='" + roomType + '\'' +
                ", description='" + description + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", hotel=" + hotel +
                '}';
    }
}
