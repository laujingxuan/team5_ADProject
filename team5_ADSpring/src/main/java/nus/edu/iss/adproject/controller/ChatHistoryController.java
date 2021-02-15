package nus.edu.iss.adproject.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
 
@Controller
public class ChatHistoryController  {
 
//    @RequestMapping("/Chat")
//    public String index(HttpServletRequest request, Model model) {
//        String username = (String) request.getSession().getAttribute("username");
// 
//        if (username == null || username.isEmpty()) {
//            return "redirect:/chatlogin";
//        }
//        model.addAttribute("username", username); 
//        return "chat";
//    }
// 
////    @RequestMapping(path = "/chatlogin", method = RequestMethod.GET)
////    public String showLoginPage() {
////        return "chatlogin";
////    }
//// 
////    @RequestMapping(path = "/chatlogin", method = RequestMethod.POST)
////    public String doLogin(HttpServletRequest request, @RequestParam(defaultValue = "") String username) {
////        username = username.trim();
//// 
////        if (username.isEmpty()) {
////            return "chatlogin";
////        }
////        request.getSession().setAttribute("username", username);
//// 
////        return "redirect:/Chat";
////    }
// 
//    @RequestMapping(path = "/chatlogout")
//    public String logout(HttpServletRequest request) {
//        request.getSession(true).invalidate();
//         
//        return "redirect:/chatlogin";
//    }
     
}