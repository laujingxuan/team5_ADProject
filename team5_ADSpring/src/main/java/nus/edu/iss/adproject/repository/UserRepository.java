package nus.edu.iss.adproject.repository;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import nus.edu.iss.adproject.model.User;
//import nus.edu.iss.adproject.model.RoleType;


public interface UserRepository extends JpaRepository<User, Long> {
	public User findByUserName(String userName);
//	public ArrayList<User> findByRoleType(RoleType roleType);
	
	public User findByResetPasswordToken(String token);
	
	@Query("select u from User u where u.email=?1")
	public User findByEmail(String email);
	
	@Query("select u from User u where u.email=?1")
	public Optional<User> findUserByEmail(String email);
	
	@Query("select u from User u where u.userName=?1")
	public Optional<User> findUserByUserName(String userName);
}
