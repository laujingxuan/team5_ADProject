package nus.edu.iss.adproject.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nus.edu.iss.adproject.model.User;
import nus.edu.iss.adproject.service.SessionService;
import nus.edu.iss.adproject.service.SessionServiceImpl;
import nus.edu.iss.adproject.service.UserService;
import nus.edu.iss.adproject.service.UserServiceImpl;

@RestController
@CrossOrigin
@RequestMapping("/api/user")
public class UserApiController {
	@Autowired
	private UserService user_svc;
	@Autowired
	private SessionService session_svc;
	
	@Autowired 
	public void setImplimentation(UserServiceImpl user_svcimpl, SessionServiceImpl session_svcimpl) {
		this.user_svc = user_svcimpl;
		this.session_svc = session_svcimpl;
	}
	
	@GetMapping("/login")
	public ResponseEntity<User> login (){
		User u = new User();
		return new ResponseEntity<User>(u,HttpStatus.OK);
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<User> authenticate(@RequestBody User user){
		if(session_svc.authenticate(user)) 
		{
			User u = user_svc.findByUsername(user.getUserName());
			return new ResponseEntity<User>(u,HttpStatus.OK);
		}
		else
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		
	}
}
