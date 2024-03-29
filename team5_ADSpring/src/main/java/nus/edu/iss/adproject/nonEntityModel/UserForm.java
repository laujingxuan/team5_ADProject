package nus.edu.iss.adproject.nonEntityModel;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import nus.edu.iss.adproject.model.User;



public class UserForm {

    private long id;
	@NotNull
    private String userName;
	@NotNull
	private String email;
	@NotNull
	@Size(min=2,max=30)
	private String password;
	@NotNull
	private String confPassword;
	private RoleType role;
	private boolean subscribe;
	public UserForm() {
		super();
	}
	
	

	public UserForm(long id, @NotNull String userName, @NotNull String email,
			@NotNull @Size(min = 2, max = 30) String password, @NotNull String confPassword, RoleType role,
			boolean subscribe) {
		super();
		this.id = id;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.confPassword = confPassword;
		this.role = role;
		this.subscribe = subscribe;
	}





	public boolean isSubscribe() {
		return subscribe;
	}



	public void setSubscribe(boolean subscribe) {
		this.subscribe = subscribe;
	}



	public UserForm(long id, String userName, String email,String password, String confPassword, RoleType role) {
		super();
		this.id = id;
		this.userName = userName;
		this.email=email;
		this.password = password;
		this.confPassword = confPassword;
		this.role = role;
	}
	
	public UserForm(User user) {
		this.id=user.getId();
		this.userName=user.getUserName();
		this.email=user.getEmail();
		this.password=user.getPassword();
		this.role=user.getRole();
	}
		
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfPassword() {
		return confPassword;
	}
	public void setConfPassword(String confPassword) {
		this.confPassword = confPassword;
	}
	public RoleType getRole() {
		return role;
	}
	public void setRole(RoleType role) {
		this.role = role;
	}



	@Override
	public String toString() {
		return "UserForm [id=" + id + ", userName=" + userName + ", email=" + email + ", password=" + password
				+ ", confPassword=" + confPassword + ", role=" + role + ", subscribe=" + subscribe + "]";
	}

	
}
