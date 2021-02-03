package nus.edu.iss.adproject.service;

import java.util.ArrayList;

import nus.edu.iss.adproject.model.User;
import nus.edu.iss.adproject.nonEntityModel.RoleType;



public interface UserService extends IService<User> {
//	public ArrayList<User> findByRoleType(RoleType roleType);
	public User findByUsername(String userName);
	public void deleteUsers(String[] users);
	boolean updateUser(User user);
}
