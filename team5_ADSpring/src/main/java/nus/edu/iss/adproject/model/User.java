package nus.edu.iss.adproject.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import nus.edu.iss.adproject.nonEntityModel.RoleType;
import nus.edu.iss.adproject.nonEntityModel.UserForm;


@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String userName;
	private String password;
	private RoleType role;
	private String email;
	private boolean subscribe;
	
	public User(long id, String userName, String password, RoleType role, String email,boolean subscribe) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.role = role;
		this.email = email;
		this.subscribe = subscribe;

	}

	@OneToMany(mappedBy="user")
	private List<Cart> carts;

	@OneToMany(mappedBy = "user")
	private List<Attraction> attraction;
	
	@OneToMany(mappedBy = "user")
	private List<Hotel> hotel;

	@Column(name="reset_password_token")
	private String resetPasswordToken;

	@OneToMany(mappedBy = "user")
	private List<ProductReview> productReview;

	
	public User() { }

	public User(String name, String password, RoleType role, String email) {

		this.userName = name;
		this.password = password;
		this.role = role;
		this.email = email;
		this.carts = new ArrayList<Cart>();
	}
	
	public User(String userName, String password,RoleType role) {
		super();
		this.userName = userName;
		this.password = password;
		this.role = role;
		this.carts = new ArrayList<Cart>();
	}
	
	public User(UserForm userForm) {
		this.id=userForm.getId();
		this.userName=userForm.getUserName();
		this.email = userForm.getEmail();
		this.password=userForm.getPassword();
		this.role=userForm.getRole();
		this.carts = new ArrayList<Cart>();
		this.subscribe=userForm.isSubscribe();
	}
	
	public boolean isSubscribe() {
		return subscribe;
	}

	public void setSubscribe(boolean subscribe) {
		this.subscribe = subscribe;
	}

	public List<ProductReview> getProductReview() {
		return productReview;
	}

	public void setProductReview(List<ProductReview> productReview) {
		this.productReview = productReview;
	}
	
	public RoleType getRole() {
		return role;
	}

	public void setRole(RoleType role) {
		this.role = role;
	}

	public List<Cart> getCarts() {
		return carts;
	}

	public void setCarts(List<Cart> carts) {
		this.carts = carts;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public List<Attraction> getAttraction() {
		return attraction;
	}
	public void setAttraction(List<Attraction> attraction) {
		this.attraction = attraction;
	}
	public List<Hotel> getHotel() {
		return hotel;
	}
	public void setHotel(List<Hotel> hotel) {
		this.hotel = hotel;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", password=" + password + ", role=" + role + ", email="
				+ email + ", subscribe=" + subscribe + "]";
	}

	public String getResetPasswordToken() {
		return resetPasswordToken;
	}

	public void setResetPasswordToken(String resetPasswordToken) {
		this.resetPasswordToken = resetPasswordToken;
	}
	
	
}
