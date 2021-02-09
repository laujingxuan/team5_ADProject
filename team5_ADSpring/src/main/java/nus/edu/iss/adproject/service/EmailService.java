package nus.edu.iss.adproject.service;

import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import nus.edu.iss.adproject.model.BookingDetails;
import nus.edu.iss.adproject.model.User;
import nus.edu.iss.adproject.nonEntityModel.Email;
import nus.edu.iss.adproject.nonEntityModel.ProductType;

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private UserServiceImpl userimpl;
	
	@Autowired
	private BookingService bookingservice;
	
    @Autowired
    private SpringTemplateEngine templateEngine;
	
    public void sendSimpleEmail(String toAddress, String subject, String message) {

    	  SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
    	  simpleMailMessage.setTo(toAddress);
    	  simpleMailMessage.setSubject(subject);
    	  simpleMailMessage.setText(message);
    	  javaMailSender.send(simpleMailMessage);
    	 }
    
    public void sendEmailWithAttachment(String toAddress, String subject, String message, String attachment) throws MessagingException, FileNotFoundException {

    	  MimeMessage mimeMessage = javaMailSender.createMimeMessage();
    	  MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
    	  messageHelper.setTo(toAddress);
    	  messageHelper.setSubject(subject);
    	  messageHelper.setText(message);
    	  FileSystemResource file = new FileSystemResource(ResourceUtils.getFile(attachment));
    	  messageHelper.addAttachment("Purchase Order", file);
    	  javaMailSender.send(mimeMessage);
    	 }

    public String sendMail(Long detailid,Long userId,LocalDate startDate, LocalDate endDate,
    		int quantity ,String Remarks)
    		throws MessagingException {
    	User user = userimpl.findById(userId);
 //   	List<BookingDetails> bookingdetail = bookingservice.retrieveDetailsByBookingId(id);
    	BookingDetails details = bookingservice.retrieveDetailsByDetailId(detailid);
    	
    		//System.out.println(b.getProduct().getType());
    		ProductType producttype = details.getProduct().getType();
    		
    		if(producttype == ProductType.ATTRACTION) {
    			//System.out.print(details.getProduct().getAttraction().toString());
    			
    	        Context context = new Context();
    	        context.setVariable("startdate", startDate);
    	        context.setVariable("enddate",endDate);
    	        context.setVariable("quantity", quantity);
    	        context.setVariable("remarks", Remarks);
    	        context.setVariable("bookingdetail", details);
    	        String process = templateEngine.process("Attraction-confirmation", context);
    	        javax.mail.internet.MimeMessage mimeMessage = javaMailSender.createMimeMessage();
    	        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
    	        helper.setSubject("Thank you for booking");
    	        helper.setText(process, true);
    	        helper.setTo(user.getEmail());
    	        javaMailSender.send(mimeMessage);
    			
    		}
    		else {
    			//System.out.print(b.getProduct().getRoomType().getClass().toString());
    	        Context context = new Context();
    	        
    	        context.setVariable("bookingdetail", details);
    	        context.setVariable("startdate", startDate);
    	        context.setVariable("enddate",endDate);
    	        context.setVariable("quantity", quantity);
    	        context.setVariable("remarks", Remarks);
    	        String process = templateEngine.process("hotel-confirmation", context);
    	        javax.mail.internet.MimeMessage mimeMessage = javaMailSender.createMimeMessage();
    	        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
    	        helper.setSubject("Thank you for booking");
    	        helper.setText(process, true);
    	        helper.setTo(user.getEmail());
    	        javaMailSender.send(mimeMessage);
    		}
    	

        return "Sent";
    }

}
