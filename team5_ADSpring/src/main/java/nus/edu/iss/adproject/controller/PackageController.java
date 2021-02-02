package nus.edu.iss.adproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import nus.edu.iss.adproject.NonEntityModel.TravelPackageWrapper;
import nus.edu.iss.adproject.service.TravelPackageService;

@Controller
@RequestMapping("/package")
public class PackageController {
	
	@Autowired
	private TravelPackageService tpService;
	
	@GetMapping("/list")
	public String showPackages(Model model) {
		TravelPackageWrapper wrapper = new TravelPackageWrapper(tpService.retrieveAll());
		model.addAttribute("TPWrapper", wrapper);
		return "package";
	}
	
	@PostMapping("/update")
	public String updatePackages(@ModelAttribute("TPWrapper") TravelPackageWrapper wrapper) {
		tpService.updatePackage(wrapper.getTravelPackages());
		return "redirect:/booking/list";
	}
}
