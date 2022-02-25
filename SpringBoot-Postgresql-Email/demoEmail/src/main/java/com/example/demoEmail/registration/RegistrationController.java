package com.example.demoEmail.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
* @author: KhanhPG
* @since: 24/02/2022	
*/
@RestController
@RequestMapping(path = "api/v1/registration")
public class RegistrationController {
	@Autowired
	private RegistrationService registrationService;
	@PostMapping
	public String register(@RequestBody RegistrationRequest request) {
		return registrationService.register(request);
	}
	
	@GetMapping(path = "confirm")
	public String confirm(@RequestParam("token") String token) {
		return registrationService.confirmToken(token) ;
	}
	
	@GetMapping
	public String test() {
		return "Hello" ;
	}
}
