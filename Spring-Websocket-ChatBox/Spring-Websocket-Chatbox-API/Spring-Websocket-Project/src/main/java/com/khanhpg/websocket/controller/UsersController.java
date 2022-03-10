package com.khanhpg.websocket.controller;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.khanhpg.websocket.storage.UserStorage;

import lombok.extern.slf4j.Slf4j;

/**
	* @author: KhanhPG
	* @since: 05/03/2022	
	*/
@Slf4j
@RestController
public class UsersController {
	@GetMapping("/registration/{userName}")
	public ResponseEntity<Void> register(@PathVariable String userName) {
		log.info("hanging register user request: " + userName);
		try {
			UserStorage.getInstance().setUsers(userName);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/fetchAllUsers")
	public Set<String> fetchAll() {
		return UserStorage.getInstance().getUsers();
	}
}
