package nus.edu.iss.adproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import nus.edu.iss.adproject.model.Product;
import nus.edu.iss.adproject.service.ProductService;

@Controller
public class MainController {
	
	@Autowired
	private ProductService pservice;
	
	@RequestMapping("/")
	public String listProductForm(Model model, @Param("keyword") String keyword) {
		System.out.println("keyword" + keyword);
		
		List<Product> listproducts = pservice.listAllSearchAttractions(keyword);
		List<Product> listhotels = pservice.listAllSearchHotels(keyword);
		listproducts.addAll(listhotels);
		System.out.println("list" + listproducts);
		model.addAttribute("product", listproducts);
		model.addAttribute("keyword", keyword); 
		
		return "productslist";
	}
}
