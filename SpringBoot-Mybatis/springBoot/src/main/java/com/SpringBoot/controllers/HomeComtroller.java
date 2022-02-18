package com.SpringBoot.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeComtroller {
	@GetMapping(value = { "", "/home" })
	public String homepage() {
		return "This is my home page";
	}
}
