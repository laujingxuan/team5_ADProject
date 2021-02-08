package nus.edu.iss.adproject.service;

import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.List;

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

    public String sendMail(Long id) throws MessagingException {
    	User user = userimpl.findById(id);
//    	List<BookingDetails> bookingdetail = bookingservice.retrieveDetailsByBookingId(id);
//    	for(BookingDetails b :bookingdetail ) {
//    		System.out.println(b.getProduct().getType());
//    		ProductType producttype = b.getProduct().getType();
//    		if(producttype == ProductType.ATTRACTION) {
//    			System.out.print(b.getProduct().getAttraction().toString());
//    		}
//    		else {
//    			System.out.print(b.getProduct().getRoomType().getHotel().getName());
//    		}
//    	}
        Context context = new Context();
        context.setVariable("user", user);

        String process = templateEngine.process("welcome", context);
        javax.mail.internet.MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setSubject("welcome " + user.getUserName());
        helper.setText(process, true);
        helper.setTo(user.getEmail());
        javaMailSender.send(mimeMessage);
        return "Sent";
    }

}
