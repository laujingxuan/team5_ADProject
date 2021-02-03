package nus.edu.iss.adproject.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import nus.edu.iss.adproject.model.User;
//import nus.edu.iss.adproject.model.RoleType;


public interface UserRepository extends JpaRepository<User, Long> {
	public User findByUserName(String userName);
//	public ArrayList<User> findByRoleType(RoleType roleType);
}
