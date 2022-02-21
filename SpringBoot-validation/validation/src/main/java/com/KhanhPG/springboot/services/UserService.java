package com.KhanhPG.springboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.KhanhPG.springboot.models.User;
import com.KhanhPG.springboot.repositories.UserRepository;

/**
* @author: KhanhPG
* @since: 21/02/2022	
*/
@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public User createUser(User user) {
		return userRepository.save(user);
	}
}
