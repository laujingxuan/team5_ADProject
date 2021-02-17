package nus.edu.iss.adproject.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import nus.edu.iss.adproject.model.User;
import nus.edu.iss.adproject.nonEntityModel.RoleType;
import nus.edu.iss.adproject.nonEntityModel.UserForm;
import nus.edu.iss.adproject.service.SessionService;
import nus.edu.iss.adproject.service.SessionServiceImpl;
import nus.edu.iss.adproject.service.UserService;
import nus.edu.iss.adproject.service.UserServiceImpl;
import nus.edu.iss.adproject.validator.UserFormValidator;


@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService user_svc;
	
	@Autowired
	private SessionService session_svc;
	
	@Autowired 
	public void setImplimentation(UserServiceImpl user_svcimpl, SessionServiceImpl session_svcimpl) {
		this.user_svc = user_svcimpl;
		this.session_svc = session_svcimpl;
	}
	
	@Autowired
	private UserFormValidator userFormValidator;
	
	@InitBinder("userForm")
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(userFormValidator);
	}
	
//	@GetMapping("/add")
//	public String addUser(Model model, HttpSession session) {
//		if (session_svc.isNotLoggedIn(session)) return "redirect:/user/login";
//		if (session_svc.hasNoPermission(session)) return "nopermission";
//		
//		model.addAttribute("roleType", RoleType.values());
//		model.addAttribute("path", "/user/validate");
//		model.addAttribute("userForm", new UserForm());
//		return "editUser";
//	}
	
	//everyone can login
	@RequestMapping(path = "/login")
	public String login(Model model) {
		User u = new User();
		model.addAttribute("user", u);
		return "login";
	}
	
	@GetMapping("/logout")
	public ModelAndView logout(HttpSession session) {
		session.invalidate();
		ModelAndView mv = new ModelAndView("redirect:/user/login");
		return mv;
	}
	
	@RequestMapping(path = "/authenticate")
	public String authenticate(@ModelAttribute("user") User user, Model model, HttpSession session) {
		if(session_svc.authenticate(user)) 
		{
			User u = user_svc.findByUsername(user.getUserName());
			session.setAttribute("user", u);
			return "redirect:/";
		}
		model.addAttribute("invalidCredentials", true);
		return "login";	
	}
	
	@GetMapping("/signup")
	public String signUp(Model model, HttpSession session) {
		model.addAttribute("path", "/user/validate");
		UserForm userform=new UserForm();
		userform.setRole(RoleType.CUSTOMER);
		model.addAttribute("userForm", userform);
		return "signUpForm";
	}
	
	@GetMapping("/merchant_signup")
	public String merchantSignUp(Model model, HttpSession session) {
		model.addAttribute("path", "/user/validate");
		UserForm userform = new UserForm();
		model.addAttribute("userForm", userform);
		return "merchant-signup-form";
	}
	
	@PostMapping("/validate")
	public String addUser(@ModelAttribute("userForm") @Valid UserForm userForm, BindingResult bindingResult, HttpSession session, Model model) {
		model.addAttribute("path", "/user/validate");
		if(user_svc.userExists(userForm.getUserName())) {
			bindingResult.addError(new FieldError("userForm","userName","User name is already in use"));
		}
		if(user_svc.emailExists(userForm.getEmail())) {
			bindingResult.addError(new FieldError("userForm","email","email is already in use"));
		}
		if (bindingResult.hasErrors()) {
			for (Object object : bindingResult.getAllErrors()) {
			    if(object instanceof FieldError) {
			        FieldError fieldError = (FieldError) object;

			        System.out.println(fieldError.getCode());
			    }

			    if(object instanceof ObjectError) {
			        ObjectError objectError = (ObjectError) object;

			        System.out.println(objectError.getCode());
			    }
			}
			List<FieldError> errors = bindingResult.getFieldErrors();
		    for (FieldError error : errors ) {
		        System.out.println (error.getObjectName() + " - " + error.getDefaultMessage());
		    }
		    if(userForm.getRole()==RoleType.CUSTOMER) {
			return "signUpForm";
			}else if(userForm.getRole()==RoleType.HOTELMANAGER||userForm.getRole()==RoleType.ATTRACTIONMANAGER) {
				return "merchant-signup-form";
			}
		    
		}
		User user = new User(userForm);
		user_svc.save(user);
		return "signup_success";
	}
	
//	//only admin can retrieve the list
//	@GetMapping("/users")
//	public String viewUser(Model model, HttpSession session) {
//		if (session_svc.isNotLoggedIn(session)) return "redirect:/user/login";
//		if (session_svc.hasNoPermission(session)) return "nopermission";
//
//		// retrieval was done with js 
//		return "UserList";
//	}
		
	
//	//only admin can edit
//	@RequestMapping(value = "/edit/{id}")
//	public String editUser(@PathVariable("id") long id, Model model, HttpSession session) {
//		if (session_svc.isNotLoggedIn(session)) return "redirect:/user/login";
//		if (session_svc.hasNoPermission(session)) return "nopermission";
//		
//		User toChange = user_svc.findById(id);
//		model.addAttribute("roleType", RoleType.values());
//		UserForm userForm = new UserForm(toChange);
//		model.addAttribute("userForm", userForm);
//		model.addAttribute("path", "/user/save");
//		return "editUser";
//	}
//	
//	@PostMapping("/save")
//	public String editUser(@ModelAttribute("userForm") @Valid UserForm userForm, BindingResult bindingResult, HttpSession session, Model model) {
//		if (session_svc.isNotLoggedIn(session)) return "redirect:/user/login";
//		if (session_svc.hasNoPermission(session)) return "nopermission";
//		
//		model.addAttribute("roleType", RoleType.values());
//		model.addAttribute("path", "/user/save");
//		if (bindingResult.hasErrors()) return "editUser";
//		
//		User user = new User(userForm);
//		long id = user.getId();
//		boolean success = user_svc.updateUser(user);
//		if (success == true) {
//			return "redirect:/user/users";
//		} else {
//			return "error";
//		}
//	}
//	
//	//everyone can change password
//	@GetMapping("/update")
//	public String updateUser(Model model, HttpSession session) {
//		if (session_svc.isNotLoggedIn(session)) return "redirect:/user/login";
//		User user = (User) session.getAttribute("user"); 
//		model.addAttribute("roleType", RoleType.values());
//		model.addAttribute("path", "/user/update");
//		model.addAttribute("userForm", new UserForm(user));
//		return "editUser";
//	}
//
//	@PostMapping("/update")
//	public String updateUser(@ModelAttribute("userForm") @Valid UserForm userForm, BindingResult bindingResult, HttpSession session, Model model) {
//		if (session_svc.isNotLoggedIn(session)) return "redirect:/user/login";
//		model.addAttribute("roleType", RoleType.values());
//		model.addAttribute("path", "/user/update");
//		if (bindingResult.hasErrors()) return "editUser";
//		
//		User user = new User(userForm);
//		user_svc.updateUser(user);
//		session.setAttribute("user", user);
//		return "redirect:/";
//	}
//	
//	//only admin can delete
//	@PostMapping("/delete")
//	public String deleteUser(@RequestParam(value = "deleteUser", required = false) String[] deleteUsers, HttpSession session) {
//		if (session_svc.isNotLoggedIn(session)) return "redirect:/user/login";
//		if (session_svc.hasNoPermission(session)) return "nopermission";
//		System.out.println("test");
//		user_svc.deleteUsers(deleteUsers);
//		return "redirect:/user/users";
//	}
	
}

