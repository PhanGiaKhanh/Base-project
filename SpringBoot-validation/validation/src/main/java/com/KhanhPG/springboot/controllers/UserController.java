package com.KhanhPG.springboot.controllers;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.KhanhPG.springboot.dto.UserDto;
import com.KhanhPG.springboot.models.User;
import com.KhanhPG.springboot.services.UserService;

/**
* @author: KhanhPG
* @since: 21/02/2022	
*/
@RestController
@RequestMapping("/api/v1/")
public class UserController {
	@Autowired
	private UserService userService;
	
	@PostMapping("users")
	public ResponseEntity<User> createUser(@Valid @RequestBody UserDto userDto) {
		User user = new User();
		BeanUtils.copyProperties(userDto, user);
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(user));
	}
}
