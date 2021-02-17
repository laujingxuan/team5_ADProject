package nus.edu.iss.mobileapp.model;

import java.io.Serializable;

import nus.edu.iss.mobileapp.nonEntityModel.ProductType;

public class Product implements Serializable {
    private Long id;

    private ProductType type;

//    private List<ProductReview> productReview;

    private Attraction attraction;

    private RoomType roomType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

//    public List<ProductReview> getProductReview() {
//        return productReview;
//    }
//
//    public void setProductReview(List<ProductReview> productReview) {
//        this.productReview = productReview;
//    }

    public Attraction getAttraction() {
        return attraction;
    }

    public void setAttraction(Attraction attraction) {
        this.attraction = attraction;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", type=" + type +
                ", attraction=" + attraction +
                ", roomType=" + roomType +
                '}';
    }
}
