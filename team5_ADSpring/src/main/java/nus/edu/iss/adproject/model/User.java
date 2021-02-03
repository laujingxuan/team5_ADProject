package nus.edu.iss.adproject.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//import nus.edu.iss.adproject.nonEntityModel.UserForm;



@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String userName;
	private String password;
//	private RoleType role;
	private String email;
	private long points; 
	
	public User() { }
	public User(long id, String name, String password, String email, long points) {//, RoleType role
		this.userName = name;
		this.password = password;
//		this.role = role;
		this.email = email;
		this.points = points;
	}
	
	public User(String userName, String password) { //RoleType role
		super();
		this.userName = userName;
		this.password = password;
//		this.role = role;
	}
	
//	public User(UserForm userForm) {
//		this.id=userForm.getId();
//		this.userName=userForm.getUserName();
//		this.password=userForm.getPassword();
//		this.role=userForm.getRole();
//	}
	
	
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
//	public RoleType getRole() {
//		return role;
//	}
//	public void setRole(RoleType role) {
//		this.role = role;
//	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getPoints() {
		return points;
	}
	public void setPoints(long points) {
		this.points = points;
	}
	
	
}
