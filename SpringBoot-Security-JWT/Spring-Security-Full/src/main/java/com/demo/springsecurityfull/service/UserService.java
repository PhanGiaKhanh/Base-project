package com.demo.springsecurityfull.service;

import java.util.Optional;

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

	User findUserByEmail(String email);

	void createPasswordResetTokenForUser(User user, String token);

	String validatePasswordResetToken(String token);

	Optional<User> getUserByPasswordResetToken(String token);

	void changePassword(User user, String newPassword);

	boolean checkIfValidOldPassword(User user, String oldPassword);

}
