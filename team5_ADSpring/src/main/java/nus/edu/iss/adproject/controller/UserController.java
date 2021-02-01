package nus.edu.iss.adproject.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import nus.edu.iss.adproject.model.RoleType;
import nus.edu.iss.adproject.model.User;
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
	
	//only admin can add
	@GetMapping("/add")
	public String addUser(Model model, HttpSession session) {
		if (session_svc.isNotLoggedIn(session)) return "redirect:/user/login";
		if (session_svc.hasNoPermission(session)) return "nopermission";
		
		model.addAttribute("roleType", RoleType.values());
		model.addAttribute("path", "/user/validate");
		model.addAttribute("userForm", new UserForm());
		return "editUser";
	}
	
	@PostMapping("/validate")
	public String addUser(@ModelAttribute("userForm") @Valid UserForm userForm, BindingResult bindingResult, HttpSession session, Model model) {
		model.addAttribute("roleType", RoleType.values());
		model.addAttribute("path", "/user/validate");
		if (bindingResult.hasErrors()) {
			return "editUser";
		}
		User user = new User(userForm);
		user_svc.save(user);
		return "redirect:/";
	}
	
	//only admin can retrieve the list
	@GetMapping("/users")
	public String viewUser(Model model, HttpSession session) {
		if (session_svc.isNotLoggedIn(session)) return "redirect:/user/login";
		if (session_svc.hasNoPermission(session)) return "nopermission";

		// retrieval was done with js 
		return "UserList";
	}
		
	
	//only admin can edit
	@RequestMapping(value = "/edit/{id}")
	public String editUser(@PathVariable("id") long id, Model model, HttpSession session) {
		if (session_svc.isNotLoggedIn(session)) return "redirect:/user/login";
		if (session_svc.hasNoPermission(session)) return "nopermission";
		
		User toChange = user_svc.findById(id);
		model.addAttribute("roleType", RoleType.values());
		UserForm userForm = new UserForm(toChange);
		model.addAttribute("userForm", userForm);
		model.addAttribute("path", "/user/save");
		return "editUser";
	}
	
	@PostMapping("/save")
	public String editUser(@ModelAttribute("userForm") @Valid UserForm userForm, BindingResult bindingResult, HttpSession session, Model model) {
		if (session_svc.isNotLoggedIn(session)) return "redirect:/user/login";
		if (session_svc.hasNoPermission(session)) return "nopermission";
		
		model.addAttribute("roleType", RoleType.values());
		model.addAttribute("path", "/user/save");
		if (bindingResult.hasErrors()) return "editUser";
		
		User user = new User(userForm);
		long id = user.getId();
		boolean success = user_svc.updateUser(user);
		if (success == true) {
			return "redirect:/user/users";
		} else {
			return "error";
		}
	}
	
	//everyone can change password
	@GetMapping("/update")
	public String updateUser(Model model, HttpSession session) {
		if (session_svc.isNotLoggedIn(session)) return "redirect:/user/login";
		User user = (User) session.getAttribute("user"); 
		model.addAttribute("roleType", RoleType.values());
		model.addAttribute("path", "/user/update");
		model.addAttribute("userForm", new UserForm(user));
		return "editUser";
	}

	@PostMapping("/update")
	public String updateUser(@ModelAttribute("userForm") @Valid UserForm userForm, BindingResult bindingResult, HttpSession session, Model model) {
		if (session_svc.isNotLoggedIn(session)) return "redirect:/user/login";
		model.addAttribute("roleType", RoleType.values());
		model.addAttribute("path", "/user/update");
		if (bindingResult.hasErrors()) return "editUser";
		
		User user = new User(userForm);
		user_svc.updateUser(user);
		session.setAttribute("user", user);
		return "redirect:/";
	}
	
	//only admin can delete
	@PostMapping("/delete")
	public String deleteUser(@RequestParam(value = "deleteUser", required = false) String[] deleteUsers, HttpSession session) {
		if (session_svc.isNotLoggedIn(session)) return "redirect:/user/login";
		if (session_svc.hasNoPermission(session)) return "nopermission";
		System.out.println("test");
		user_svc.deleteUsers(deleteUsers);
		return "redirect:/user/users";
	}
}
