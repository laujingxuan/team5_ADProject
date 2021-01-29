package nus.edu.iss.adproject.model;

public class User {
	private long id;
	private String name;
	private String password;
	private String role;
	private String email;
	private long points;
	
	
	public User() { }
	public User(long id, String name, String password, String role, String email, long points) {
		this.name = name;
		this.password = password;
		this.role = role;
		this.email = email;
		this.points = points;
	}
	
	
	public long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
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
