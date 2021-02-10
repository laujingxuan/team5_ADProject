package nus.edu.iss.adproject.api.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sun.mail.iap.Response;

import nus.edu.iss.adproject.model.Product;
import nus.edu.iss.adproject.model.ProductReview;
import nus.edu.iss.adproject.model.User;
import nus.edu.iss.adproject.nonEntityModel.FileUploadUtil;
import nus.edu.iss.adproject.nonEntityModel.ProductReviewWrapper;
import nus.edu.iss.adproject.repository.ProductRepo;
import nus.edu.iss.adproject.service.ProductReviewService;
import nus.edu.iss.adproject.service.SessionService;

@RestController
@CrossOrigin
@RequestMapping("/api/review")
public class ReviewApiController {
	
	@Autowired
	ProductReviewService prservice;
	
	@Autowired
	ProductRepo prepo;
	
	@Autowired
	private SessionService sessionservice;
	
	// need wrapper 
	@GetMapping("/list/{id}")
	public ResponseEntity<ProductReviewWrapper> listProductReview(@PathVariable("id")Long id) {
		//Product product = prepo.findById(id).get();
		List<ProductReview> reviewList = prservice.findReviewByProductId(id);
//		model.addAttribute("review", reviewList); 
//		model.addAttribute("product", product);
		
		return new ResponseEntity<ProductReviewWrapper>(new ProductReviewWrapper(reviewList),HttpStatus.OK);
	}
	
	
	@GetMapping("/post/{id}")
	public ResponseEntity<ProductReview> postProductReview(Model model, HttpSession session, @PathVariable("id")Long id) {
//		if (sessionservice.isNotLoggedIn(session))
//			return "redirect:/user/login";
		User user = (User) session.getAttribute("user");
		ProductReview review = new ProductReview();
		review.setUser(user);
		review.setProduct(prepo.findById(id).get());
//		model.addAttribute("review", review);
		
		return new ResponseEntity<ProductReview>(review,HttpStatus.OK);
	}
	
	
	@PostMapping("/save/{id}")
	public ResponseEntity<ProductReview> postProductReveiw(Model model, @PathVariable("id")Long id, 
			@ModelAttribute("review")@Valid ProductReview review, 
			BindingResult bindingResult, @RequestParam("image") MultipartFile multipartFile) throws IOException {
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		review.setPhoto(fileName);
		review.setProduct(prepo.findById(id).get());
//		if(bindingResult.hasErrors()) {
//			return "reviewForm";
//		}
		prservice.save(review);
		String uploadDir = "user-photos/" + review.getId();
		FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		
		return  new ResponseEntity<ProductReview>(review,HttpStatus.OK);
	}

}
