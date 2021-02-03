package nus.edu.iss.adproject.service;

import javax.transaction.Transactional;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nus.edu.iss.adproject.model.User;
import nus.edu.iss.adproject.nonEntityModel.RoleType;
import nus.edu.iss.adproject.repository.UserRepository;



@Service
@Transactional
public class SessionServiceImpl implements SessionService {

	@Autowired
	UserRepository urepo;
	
	public boolean authenticate(User user) {
		User dbuser = urepo.findByUserName(user.getUserName());
		if (dbuser==null) {
			return false;
		}else if(dbuser.getUserName().equals(user.getUserName()) && dbuser.getPassword().equals(user.getPassword())){
			return true;
		}else {
			return false;
		}
	}
	
	
	public boolean isNotLoggedIn(HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user == null)
			return true;
		else 
			return false;
	}
	
	public boolean hasNoPermission(HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user.getRole() != RoleType.PLATFORMMANAGER)
			return true;
		else 
			return false;
	}
	
	public boolean hasPermission(HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user.getRole() == RoleType.PLATFORMMANAGER)
			return true;
		else 
			return false;
	}
}

