package nus.edu.iss.adproject.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import nus.edu.iss.adproject.model.ChatData;
import nus.edu.iss.adproject.nonEntityModel.ChatMessage;
import nus.edu.iss.adproject.service.ChatDataService;
import nus.edu.iss.adproject.service.SessionService;
 
@Controller
public class WebSocketControler {
 
	@Autowired
	private SessionService session_svc;
	
	@Autowired
	private ChatDataService CD_svc;
 
	@RequestMapping("/chat")
	  public String index(HttpServletRequest request, Model model,HttpSession session) {
		if (session_svc.isNotLoggedIn(session)) return "redirect:/user/login";
		if (session_svc.hasPlatformPermission(session) == false) {
			model.addAttribute("error", "No Permission");
			return "error";
		}
		String urName= session_svc.findName(session);
		String username = urName.trim(); 
		model.addAttribute("sender", username);
		model.addAttribute("chatDATA",CD_svc.findAll());
		model.addAttribute("username", username); 
		ChatData NewChat= new ChatData();
		model.addAttribute("userchat",NewChat);    
	    return "chat";
	  }

	  @RequestMapping(path = "/chatlogout")
	  public String logout(HttpServletRequest request) {
	      request.getSession(true).invalidate();
	       
	      return "redirect:/";
	  }
	  
//	  @GetMapping("/chatsave")
//	  public String saveChat(@ModelAttribute("userchat") @Valid ChatData chatData,Model model, BindingResult bindingResult,HttpSession session)
//	  {
//		  if (bindingResult.hasErrors()) {
//				return "/";
//		  }
//		  else {
//			  String urName= session_svc.findName(session);
//				 String  username = urName.trim();
//				 
//			  CD_svc.save(chatData);
//			  model.addAttribute("sender", username);
//		      
//		      model.addAttribute("chatDATA",CD_svc.findAll());
//		      model.addAttribute("username", username); 
//		      model.addAttribute("userchat",new ChatData());
//		      
//		      return "chat";
//		  }
//			 
//		  
//		  
//	  }
 
    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/publicChatRoom")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
    	//System.out.println("this is chatmesage"+chatMessage);
    	//System.out.println("EEEE"+ chatMessage.getContent());
    	//System.out.println("FFFF"+chatMessage.getSender());
    	
    	ChatData dc=new ChatData();
    	dc.setSender(chatMessage.getSender());
    	dc.setMessage(chatMessage.getContent());
    	System.out.println(dc.getMessage());
    	CD_svc.save(dc);
//		  model.addAttribute("sender", username);
//	      
//	      model.addAttribute("chatDATA",CD_svc.findAll());
//	      model.addAttribute("username", username); 
//	      model.addAttribute("userchat",new ChatData());
    	return chatMessage;
    }
 
    @MessageMapping("/chat.addUser")
    @SendTo("/topic/publicChatRoom")
    public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }
 
}