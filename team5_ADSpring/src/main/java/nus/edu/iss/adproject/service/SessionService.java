package nus.edu.iss.adproject.service;

import javax.servlet.http.HttpSession;

import nus.edu.iss.adproject.model.User;



public interface SessionService {

	public boolean authenticate(User user);

	public boolean isNotLoggedIn(HttpSession session);
	public boolean hasNoPermission(HttpSession session);
	public boolean hasPermission(HttpSession session);
	
	public long getUserId();
	public User getUser();
}