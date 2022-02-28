package com.demo.springsecurityfull.service.impl;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.springsecurityfull.entity.User;
import com.demo.springsecurityfull.entity.VerificationToken;
import com.demo.springsecurityfull.model.UserModel;
import com.demo.springsecurityfull.repository.UserRepository;
import com.demo.springsecurityfull.repository.VerificationTokenRepository;
import com.demo.springsecurityfull.service.UserService;

/**
* @author: KhanhPG
* @since: 28/02/2022	
*/
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private VerificationTokenRepository verificationTokenRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public User registerUser(UserModel userModel) {
		User user = new User();
		user.setEmail(userModel.getEmail());
		user.setFirstName(userModel.getFirstName());
		user.setLastName(userModel.getLastName());
		user.setRole("USER");
		user.setPassword(passwordEncoder.encode(userModel.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public void saveVerificationTokenForUser(String token, User user) {
		VerificationToken verificationToken = new VerificationToken(token, user);
		verificationTokenRepository.save(verificationToken);
	}

	@Override
	public String validateVerificatoinToken(String token) {
		VerificationToken verificationToken = verificationTokenRepository.findByToken(token);
		
		// 	Check Token exists in DB
		if (verificationToken == null) {
			return "invalid";
		}
	
		User user = verificationToken.getUser();
		Calendar cal = Calendar.getInstance();
		
		// 	Check time limit Token
		if (verificationToken.getExpirationTime().getTime() - cal.getTime().getTime() <= 0) {
			verificationTokenRepository.delete(verificationToken);
			return "expired";
		}
		
		// 	Save user checked Token
		user.setEnabled(true);
		userRepository.save(user);
		return "valid";
	}

}
