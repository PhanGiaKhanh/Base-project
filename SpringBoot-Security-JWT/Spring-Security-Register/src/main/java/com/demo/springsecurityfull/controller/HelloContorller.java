package com.demo.springsecurityfull.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* @author: KhanhPG
* @since: 28/02/2022	
*/
@RestController
public class HelloContorller {
	@GetMapping("/hello")
	public String sayHello() {
		return "Hello! Welcome to my page";
	}
}
