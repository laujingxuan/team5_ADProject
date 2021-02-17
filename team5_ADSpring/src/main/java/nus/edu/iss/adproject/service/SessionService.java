package nus.edu.iss.adproject.service;

import javax.servlet.http.HttpSession;

import nus.edu.iss.adproject.model.User;



public interface SessionService {

	public boolean authenticate(User user);

	public boolean isNotLoggedIn(HttpSession session);

	boolean hasHotelPermission(HttpSession session);

	boolean hasPlatformPermission(HttpSession session);

	boolean hasAttractionPermission(HttpSession session);

	public String findName(HttpSession session);
}