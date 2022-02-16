package com.springmvc.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.demo.models.Category;
import com.springmvc.demo.service.CategoryService;

@Controller
@RequestMapping(path = "categories")
//	http://localhost:8083/categories
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	@GetMapping("")
	public ModelAndView getAllCategories() {
		ModelAndView modelAndView = new ModelAndView("category");
		List<Category> findAll = categoryService.findAll();
		modelAndView.addObject("categories", findAll);
		return modelAndView;
	}
}
