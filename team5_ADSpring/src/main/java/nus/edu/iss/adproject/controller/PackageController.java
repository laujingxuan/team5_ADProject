package nus.edu.iss.adproject.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import nus.edu.iss.adproject.model.User;
import nus.edu.iss.adproject.nonEntityModel.RoleType;
import nus.edu.iss.adproject.nonEntityModel.TravelPackageWrapper;
import nus.edu.iss.adproject.service.SessionService;
import nus.edu.iss.adproject.service.TravelPackageService;

@Controller
@RequestMapping("/package")
public class PackageController {
	
	@Autowired
	private TravelPackageService tpService;
	
	@Autowired
	private SessionService session_svc;
	
	@GetMapping("/list")
	public String showPackages(Model model, HttpSession session) {
		if (session_svc.isNotLoggedIn(session)) return "redirect:/user/login";
		User user = (User) session.getAttribute("user");
		if (session_svc.hasPlatformPermission(session) == false) {
			model.addAttribute("error", "No Permission");
			return "error";
		}
		TravelPackageWrapper wrapper = new TravelPackageWrapper(tpService.retrieveAll());
		model.addAttribute("TPWrapper", wrapper);
		return "package";
	}
	
	@PostMapping("/update")
	public String updatePackages(@ModelAttribute("TPWrapper") TravelPackageWrapper wrapper) {
		tpService.updatePackage(wrapper.getTravelPackages());
		return "redirect:/package/list";
	}
}
