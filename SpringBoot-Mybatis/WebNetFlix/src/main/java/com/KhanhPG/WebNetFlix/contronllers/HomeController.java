package com.KhanhPG.WebNetFlix.contronllers;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.KhanhPG.WebNetFlix.config.AES;
import com.KhanhPG.WebNetFlix.mapper.UserMapper;
import com.KhanhPG.WebNetFlix.model.User;
import com.KhanhPG.WebNetFlix.model.UserExample;

@Controller
public class HomeController {
	@Autowired
	UserMapper userMapper;

	// show home
	@GetMapping(value = {"", "/home"})
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView("index");
		UserExample example = new UserExample();
		List<Map<String, Object>> users =userMapper.getAllUserByName("Khanh12");
		for (Map<String, Object> value : users) {
			System.err.println("Check: " + value.get("full_name"));
		}

		System.err.println("URL: "+ AES.encrypt("jdbc:mysql://localhost:3306/webnetflix", "Aa@123"));
		System.err.println("Password: " + AES.encrypt("12345678", "Aa@123"));
		System.err.println("Root: " + AES.encrypt("root", "Aa@123"));
		return modelAndView;
	}
	
	// get User by id 
	@GetMapping(value = "/{id}")
	public ModelAndView getById(@PathVariable long id) {
		ModelAndView modelAndView = new ModelAndView("index");
		UserExample example = new UserExample();
		example.createCriteria().andIdEqualTo(id);
		List<User> users =userMapper.selectByExample(example);
		System.err.println("Check kiểm tra: " + users.get(0).getFullName());
		return modelAndView;
	}

	
	@GetMapping("/login")
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView("login");
		modelAndView.addObject("user", new User());
		return modelAndView;
	}
	
	@GetMapping("/other")
	public ModelAndView anypage() {
		return new ModelAndView("otherPage");
	}
}
