package com.KhanhPG.WebNetFlix.contronllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.KhanhPG.WebNetFlix.mapper.UserMapper;
import com.KhanhPG.WebNetFlix.model.User;
import com.KhanhPG.WebNetFlix.model.UserExample;

@Controller
public class HomeController {
	@Autowired
	UserMapper userMapper;
	
	@GetMapping(value = {"", "/home"})
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView("index");
		UserExample example = new UserExample();
		List<User> users =userMapper.selectByExample(example);
		System.err.println("Check kiểm tra " + users.size());
		return modelAndView;
	}
}
