package com.swn.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class MainController {
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/custom-logout")
	public String logout() {
		return "custom-logout";
	}

	@GetMapping("/")
	public String home() {
		return "index";
	}
	
	@GetMapping("/check")
	public String check(Model model) {
//		String homeMessage = "With our quick and easy process and earn FD wala interest up to 7%* p.a. with ActivMoney.";
//		String headingMessage = "Enjoy amazing benefits with your Kotak Savings Account.";
//		String messagesOne = "Bank from home with our best-in-class Mobile Banking App with 200+ features";
//		String messagesTwo = "Make contactless payments with just the mobile number via Pay Your Contact facility";
//		String messagesThree = "Grab savings up to &#x20B9;8,000 on Myntra, MakeMyTrip, Swiggy & many more with Kotak Debit Cards. T&C.";
//		model.addAttribute("homeMessage", homeMessage);
//		model.addAttribute("headingMessage", headingMessage); 
//		model.addAttribute("messagesOne", messagesOne);
//		model.addAttribute("messagesTwo", messagesTwo);
//		model.addAttribute("messagesThree", messagesThree);
		return "xpress-home-new";
	}
}
