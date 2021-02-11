package nus.edu.iss.adproject.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import nus.edu.iss.adproject.model.ProductReview;
import nus.edu.iss.adproject.model.User;
import nus.edu.iss.adproject.repository.ProductRepo;
import nus.edu.iss.adproject.service.ProductReviewService;
import nus.edu.iss.adproject.service.SessionService;

@Controller
@RequestMapping("/review")
public class ReviewController {
	
	@Autowired
	ProductReviewService prservice;
	
	
	@Autowired
	ProductRepo prepo;
	
	@Autowired
	private SessionService sessionservice;
	
	@GetMapping("/hello")
	public void hello() {
		System.out.print("hello");
	}
	
	@GetMapping("/list/{id}")
	public String listProductReview(Model model, @PathVariable("id")Long id,HttpServletResponse response)
			throws IOException {
		

		List<ProductReview> products = prservice.findReviewByProductId(id);
			
		model.addAttribute("review", products); 

		return "reviewList";
	}
	
	@GetMapping("/product/image/{id}")
	public void showProductImage(@PathVariable long id,
	                               HttpServletResponse response) throws IOException {
		response.setContentType("image/jpeg"); // Or whatever format you wanna use
		ProductReview products = prservice.findReviewById(id);
		InputStream is = new ByteArrayInputStream(products.getPic());
		IOUtils.copy(is, response.getOutputStream());
		System.out.println(is);
	}
	
	@GetMapping("/post/{id}")
	public String postProductReview(Model model, HttpSession session, @PathVariable("id")Long id) {
		if (sessionservice.isNotLoggedIn(session))
			return "redirect:/user/login";
		User user = (User) session.getAttribute("user");
		ProductReview review = new ProductReview();
		review.setUser(user);
		review.setProduct(prepo.findById(id).get());
		model.addAttribute("review", review);
		return "reviewForm";
	}
	
	
	@PostMapping("/save/{id}")
	public String postProductReveiw(Model model, @PathVariable("id")Long id, 
			@ModelAttribute("review")@Valid ProductReview review, 
			BindingResult bindingResult, @RequestParam("image") MultipartFile multipartFile) throws IOException {
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + fileName);
		multipartFile.transferTo(convFile);
		byte[] fileContent = FileUtils.readFileToByteArray(convFile);
		review.setPic(fileContent);
		review.setPhoto(fileName);

		review.setProduct(prepo.findById(id).get());
		if(bindingResult.hasErrors()) {
			return "reviewForm";
		}
		prservice.save(review);
		return "reviewList";
	}
	
	
	
}