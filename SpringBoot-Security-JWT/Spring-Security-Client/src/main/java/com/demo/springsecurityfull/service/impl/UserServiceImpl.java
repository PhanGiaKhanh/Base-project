package com.demo.springsecurityfull.service.impl;

import java.util.Calendar;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.springsecurityfull.entity.PasswordResetToken;
import com.demo.springsecurityfull.entity.User;
import com.demo.springsecurityfull.entity.VerificationToken;
import com.demo.springsecurityfull.model.UserModel;
import com.demo.springsecurityfull.repository.PasswordResetTokenRepository;
import com.demo.springsecurityfull.repository.UserRepository;
import com.demo.springsecurityfull.repository.VerificationTokenRepository;
import com.demo.springsecurityfull.service.UserService;

/**
 * @author: KhanhPG
 * @since: 28/02/2022
 */
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private VerificationTokenRepository verificationTokenRepository;

	@Autowired
	private PasswordResetTokenRepository passwordResetTokenRepository;
	
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

		// Check Token exists in DB
		if (verificationToken == null) {
			return "invalid";
		}

		User user = verificationToken.getUser();
		Calendar cal = Calendar.getInstance();

		// Check time limit Token
		if (verificationToken.getExpirationTime().getTime() - cal.getTime().getTime() <= 0) {
			verificationTokenRepository.delete(verificationToken);
			return "expired";
		}

		// Save user checked Token
		user.setEnabled(true);
		userRepository.save(user);
		return "valid";
	}

	@Override
	public VerificationToken generateNewVerificationToken(String oldToken) {
		VerificationToken verificationToken = verificationTokenRepository.findByToken(oldToken);
		verificationToken.setToken(UUID.randomUUID().toString());
		return verificationTokenRepository.save(verificationToken);
	}

	@Override
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public void createPasswordResetTokenForUser(User user, String token) {
		PasswordResetToken passwordResetToken = new PasswordResetToken(token, user);
		passwordResetTokenRepository.save(passwordResetToken);
	}

	@Override
	public String validatePasswordResetToken(String token) {
		PasswordResetToken passwordResetToken = passwordResetTokenRepository.findByToken(token);

		// Check Password Token exists in DB
		if (passwordResetToken == null) {
			return "invalid";
		}

		Calendar cal = Calendar.getInstance();

		// Check time limit Password Token
		if (passwordResetToken.getExpirationTime().getTime() - cal.getTime().getTime() <= 0) {
			passwordResetTokenRepository.delete(passwordResetToken);
			return "expired";
		}

		return "valid";
	}

	@Override
	public Optional<User> getUserByPasswordResetToken(String token) {
		return Optional.ofNullable(passwordResetTokenRepository.findByToken(token).getUser());
	}

	@Override
	public void changePassword(User user, String newPassword) {
		user.setPassword(passwordEncoder.encode(newPassword));
		userRepository.save(user);
	}

	@Override
	public boolean checkIfValidOldPassword(User user, String oldPassword) {
		return passwordEncoder.matches(oldPassword, user.getPassword());
	}

}
