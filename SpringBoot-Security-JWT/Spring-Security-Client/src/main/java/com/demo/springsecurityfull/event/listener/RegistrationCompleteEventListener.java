package com.demo.springsecurityfull.event.listener;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.demo.springsecurityfull.entity.User;
import com.demo.springsecurityfull.event.RegistrationCompleteEvent;
import com.demo.springsecurityfull.service.UserService;

import lombok.extern.slf4j.Slf4j;

/**
* @author: KhanhPG
* @since: 28/02/2022	
*/
@Component
@Slf4j
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {
	
	@Autowired
	private UserService userService;
	
	@Override
	public void onApplicationEvent(RegistrationCompleteEvent event) {
		// 	Create the Verification Token for the User with link
		User user = event.getUser();
		String token = UUID.randomUUID().toString();
		userService.saveVerificationTokenForUser(token, user);
		// 	Send Mail to user
		String url = event.getApplicationUrl() + "/verifyRegistration?token=" + token;
		// 	Send verification
		log.info("Click the link to verify your account: {}", url);
	}

}
