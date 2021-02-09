package nus.edu.iss.adproject.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import nus.edu.iss.adproject.model.User;
import nus.edu.iss.adproject.nonEntityModel.RoleType;
import nus.edu.iss.adproject.repository.UserRepository;


@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepo;
	
	public void save(User user) {
		userRepo.save(user);
	}
	
	@Override
	public User findById(Long id) {
		return userRepo.findById(id).get();
	}
	
	
	@Override
	public boolean updateUser(User user) {
		User userCheck = userRepo.findById(user.getId()).get();
		if (userCheck == null) {
			return false;
		}else {
			userCheck.setUserName(user.getUserName());
			userCheck.setPassword(user.getPassword());
			userCheck.setRole(user.getRole());
			userRepo.save(userCheck);
			return true;
		}
	}

//	@Override
//	public ArrayList<User> findByRoleType(RoleType roleType) {
//		ArrayList<User> userList = userRepo.findByRole(roleType);
//		return userList;
//	}

	@Override
	public User findByUsername(String userName) {
		return userRepo.findByUserName(userName);
	}

	@Override
	public List<User> findAll() {
		List<User> users = userRepo.findAll();
		return users;
	}

	@Override
	public void delete(User user) {
		userRepo.delete(user);
		return;
	}
	
	@Override
	public void deleteUsers(String[] users) {
		for(String user: users) {
			User temp = userRepo.findByUserName(user);
			userRepo.delete(temp);
		}
		return;
	}
	
	public void updateResetPasswordToken(String token,String email) throws CustomerNotFoundException {
		User user = userRepo.findByEmail(email);
		if(user!=null) {
			user.setResetPasswordToken(token);
			userRepo.save(user);
		}else {
			throw new CustomerNotFoundException("Could not find any user with email "+ email);
		}
	}
	
	public User get(String resetPasswordToken) {
		return userRepo.findByResetPasswordToken(resetPasswordToken);
	}
	
	public void updatePassword(User user,String newPassword) {
//		BCryptPasswordEncoder passwordEncoder= new BCryptPasswordEncoder();
//		String encodePassword = passwordEncoder.encode(newPassword);
		user.setPassword(newPassword);
		user.setResetPasswordToken(null);
		userRepo.save(user);
	}

	@Override
	public boolean emailExists(String email) {
		return findUserByEmail(email).isPresent();
	}

	private Optional<User> findUserByEmail(String email) {
		return userRepo.findUserByEmail(email);
	}

	@Override
	public boolean userExists(String userName) {
		return findUserByName(userName).isPresent();
	}

	private Optional<User> findUserByName(String userName) {
		return userRepo.findUserByUserName(userName);
	}
	
	
}

