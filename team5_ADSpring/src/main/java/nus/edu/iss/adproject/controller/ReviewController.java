package nus.edu.iss.adproject.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
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

import nus.edu.iss.adproject.model.Product;
import nus.edu.iss.adproject.model.ProductReview;
import nus.edu.iss.adproject.model.User;
import nus.edu.iss.adproject.repository.ProductRepo;
import nus.edu.iss.adproject.repository.ProductReviewRepo;
import nus.edu.iss.adproject.service.ProductReviewService;
import nus.edu.iss.adproject.service.SessionService;

@Controller
@RequestMapping("/review")
public class ReviewController {
	
	@Autowired
	ProductReviewService prservice;
	
	@Autowired
	ProductReviewRepo prrepo;
	
	@Autowired
	ProductRepo prepo;
	
	@Autowired
	private SessionService sessionservice;
	
	
	@GetMapping("/list/{id}")
	public String listProductReview(Model model, @PathVariable("id")Long id) throws IOException {
		Product product = prepo.findById(id).get();
		List<ProductReview> reviewList = prservice.findReviewByProductId(id);
		List<BufferedImage> image1 = new ArrayList<>();
		for (ProductReview p :reviewList) {
			byte[] picture = p.getPic();
			InputStream is = new ByteArrayInputStream(picture);
		    BufferedImage bImage2 = ImageIO.read(is);
		    image1.add(bImage2);
		}
		
		model.addAttribute("review", reviewList); 
		model.addAttribute("product", product);
		model.addAttribute("photo", image1);
		return "reviewList";
	}
	
	@GetMapping("/product/image/{id}")
	public void showProductImage(@PathVariable Long id,
	                               HttpServletResponse response) throws IOException {
	response.setContentType("image/jpeg"); // Or whatever format you wanna use

	ProductReview review = prrepo.findById(id);

	InputStream is = new ByteArrayInputStream(product.getImage());
	IOUtils.copy(is, response.getOutputStream());
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
		System.out.println(fileName);
		File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + fileName);
		multipartFile.transferTo(convFile);
		byte[] fileContent = FileUtils.readFileToByteArray(convFile);
		review.setPic(fileContent);
		review.setPhoto(fileName);
//		String photoImagePath = "/user-photos/" + review.getUser().getId() + "/" + review.getPhoto();
//		review.setPhotoImagePath(photoImagePath);
		review.setProduct(prepo.findById(id).get());
		if(bindingResult.hasErrors()) {
			return "reviewForm";
		}
		prservice.save(review);
//		String uploadDir = "user-photos/" + review.getUser().getId();
//		FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		return "reviewList";
	}
	
	
	
}