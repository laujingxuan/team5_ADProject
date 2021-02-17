package nus.edu.iss.adproject.service;

import java.util.ArrayList;
import java.util.Optional;

import nus.edu.iss.adproject.model.User;
import nus.edu.iss.adproject.nonEntityModel.RoleType;

public interface UserService extends IService<User> {
//	public ArrayList<User> findByRoleType(RoleType roleType);
	public User findByUsername(String userName);
	public void deleteUsers(String[] users);
	boolean updateUser(User user);
	public void updateResetPasswordToken(String token,String email) throws CustomerNotFoundException;
	public User get(String resetPasswordToken);
	public void updatePassword(User user,String newPassword);
	public boolean userExists(String userName);
	public boolean emailExists(String userName);
	public Optional<User> findUserByEmail(String email);
	public Optional<User> findUserByName(String userName);
}
