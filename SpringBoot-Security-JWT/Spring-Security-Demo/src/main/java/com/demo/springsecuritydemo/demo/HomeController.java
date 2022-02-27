package com.demo.springsecuritydemo.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* @author: KhanhPG
* @since: 27/02/2022	
*/
@RestController
public class HomeController {

	@GetMapping("/home")
	public String home() {
		return "This is Home page";
	}
	
	@GetMapping("/admin")
	public String admin() {
		return "This is Amind page";
	}
}
