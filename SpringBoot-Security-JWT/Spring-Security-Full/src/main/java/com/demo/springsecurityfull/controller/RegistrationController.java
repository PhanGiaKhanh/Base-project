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
import com.demo.springsecurityfull.entity.VerificationToken;
import com.demo.springsecurityfull.event.RegistrationCompleteEvent;
import com.demo.springsecurityfull.model.UserModel;
import com.demo.springsecurityfull.service.UserService;

import lombok.extern.slf4j.Slf4j;

/**
* @author: KhanhPG
* @since: 28/02/2022	
*/
@Slf4j
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
	
	//  Link to confirm registration by token
	@GetMapping("/verifyRegistration")
	public String verifyRegistration(@RequestParam("token") String token) {
		String result = userService.validateVerificatoinToken(token);
		if (result.equalsIgnoreCase("valid")) {
			return "User Verifies Successfully";
		}
		return "Bad User";
	}
	
	//	Link re-generator token
	@GetMapping("/resendVerifyToken")
	public String resendVerificationToken(@RequestParam("token") String oldToken, HttpServletRequest request) {
		VerificationToken verificationToken = userService.generateNewVerificationToken(oldToken);
		User user = verificationToken.getUser();
		resendVerificationTokenMail(user, applicationUrl(request), verificationToken.getToken());
		return "Verication link Sent";
	}
	
	private void resendVerificationTokenMail(User user, String applicationUrl, String token) {
		String url = applicationUrl + "/verifyRegistration?token=" + token;
		log.info("Click the link to verify your account: {}", url);
	}

	private String applicationUrl(HttpServletRequest request) {
		return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	}
}

