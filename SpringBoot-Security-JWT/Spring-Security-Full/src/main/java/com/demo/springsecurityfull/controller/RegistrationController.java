package com.demo.springsecurityfull.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.springsecurityfull.entity.User;
import com.demo.springsecurityfull.event.RegistrationCompleteEvent;
import com.demo.springsecurityfull.model.UserModel;
import com.demo.springsecurityfull.service.UserService;

/**
* @author: KhanhPG
* @since: 28/02/2022	
*/
@RestController
public class RegistrationController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@PostMapping("/register")
	public String registerUser(@RequestBody UserModel userModel, HttpServletRequest request) {
		User user = userService.registerUser(userModel);
		publisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl(request)));
		return "success";
	}
	
	@GetMapping("/verifyRegistration")
	public String verifyRegistration(@RequestParam("token") String token) {
		String result = userService.validateVerificatoinToken(token);
		if (result.equalsIgnoreCase("valid")) {
			return "User Verifies Successfully";
		}
		return "Bad User";
	}
	
	private String applicationUrl(HttpServletRequest request) {
		return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	}
}

