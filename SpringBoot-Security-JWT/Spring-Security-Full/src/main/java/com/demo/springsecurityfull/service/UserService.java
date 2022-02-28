package com.demo.springsecurityfull.service;

import com.demo.springsecurityfull.entity.User;
import com.demo.springsecurityfull.entity.VerificationToken;
import com.demo.springsecurityfull.model.UserModel;

/**
* @author: KhanhPG
* @since: 28/02/2022	
*/
public interface UserService {

	User registerUser(UserModel userModel);

	void saveVerificationTokenForUser(String token, User user);

	String validateVerificatoinToken(String token);

	VerificationToken generateNewVerificationToken(String oldToken);

}
