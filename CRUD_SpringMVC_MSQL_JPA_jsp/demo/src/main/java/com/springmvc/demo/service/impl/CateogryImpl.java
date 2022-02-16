package com.springmvc.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springmvc.demo.models.Category;
import com.springmvc.demo.repositories.CategoryRepository;
import com.springmvc.demo.service.CategoryService;

@Service
public class CateogryImpl implements CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	@Override
	public Category findById(String id) {
		return categoryRepository.findById(id).orElse(null);
	}

	@Override
	public void save(Category entity) {
		categoryRepository.save(entity);
	}

	@Override
	public void delete(String id) {
		categoryRepository.deleteById(id);
	}

}
