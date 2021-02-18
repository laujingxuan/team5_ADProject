package nus.edu.iss.adproject.service;

import javax.transaction.Transactional;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nus.edu.iss.adproject.model.User;
import nus.edu.iss.adproject.nonEntityModel.RoleType;
import nus.edu.iss.adproject.repository.UserRepository;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Service
@Transactional
public class SessionServiceImpl implements SessionService {

	@Autowired
	UserRepository urepo;
	
	@Autowired
	UserService user_svc;
	
	@Autowired
	HttpSession session;
	
	@Autowired
	public void SetImplimentation(UserServiceImpl user_svcimpl) {
		this.user_svc = user_svcimpl;
	}
	
	@Override
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
	
	@Override
	public boolean isNotLoggedIn(HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user == null)
			return true;
		else 
			return false;
	}
	
	@Override
	public boolean hasPlatformPermission(HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user.getRole() == RoleType.PLATFORMMANAGER)
			return true;
		else 
			return false;
	}
	public String findName(HttpSession session)
	{
		String realname;
		User user = (User) session.getAttribute("user");
		if (user == null)
			return realname="Visitor";
		else 
			return realname=user.getUserName();
	}
	
	@Override
	public boolean hasHotelPermission(HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user.getRole() == RoleType.HOTELMANAGER)
			return true;
		else 
			return false;
	}
	
	@Override
	public boolean hasAttractionPermission(HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user.getRole() == RoleType.ATTRACTIONMANAGER)
			return true;
		else 
			return false;
	}
}

