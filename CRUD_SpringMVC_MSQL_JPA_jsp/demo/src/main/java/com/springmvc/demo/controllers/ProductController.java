package com.springmvc.demo.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.demo.models.Product;
import com.springmvc.demo.service.CategoryService;
import com.springmvc.demo.service.ProductServcie;

@Controller
@RequestMapping(path = "products")
public class ProductController {
	@Autowired
	private ProductServcie productServcie;

	@Autowired
	private CategoryService categoryService;

	// Find Products
	@GetMapping("")
	public ModelAndView getProducts() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("productList");
		modelAndView.addObject("products", productServcie.findAll());
		return modelAndView;
	}

	// Find Products by CategoryID
	@GetMapping("/{id}")
	public ModelAndView getProductsByCategoryId(@PathVariable(name = "id") String categoryId) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("productList");
		modelAndView.addObject("products", productServcie.findByCategoryId(categoryId));
		return modelAndView;
	}

	// show page change Category for Product
	@GetMapping("/changeCategory/{productId}")
	public ModelAndView changeCategory(@PathVariable(name = "productId") String productId) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("updateProduct");
		modelAndView.addObject("categories", categoryService.findAll());
		modelAndView.addObject("product", productServcie.findById(productId));
		return modelAndView;
	}

	// update Category for Product
	@PostMapping("/updateProduct/{productId}")
	public String updateCategoryForProduct(@Valid @ModelAttribute("product") Product product, BindingResult result,
			@PathVariable(name = "productId") String productId) {
		if (result.hasErrors()) {
			return "updateProduct";
		}
		Product foundProduct = productServcie.findById(product.getProductId());
		if (foundProduct != null) {
			productServcie.save(product);
		}
		return "redirect:/products/" + product.getCategoryId();
	}
}
