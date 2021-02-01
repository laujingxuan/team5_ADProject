package nus.edu.iss.adproject.service;


import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nus.edu.iss.adproject.model.RoleType;
import nus.edu.iss.adproject.model.User;
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
	
}

