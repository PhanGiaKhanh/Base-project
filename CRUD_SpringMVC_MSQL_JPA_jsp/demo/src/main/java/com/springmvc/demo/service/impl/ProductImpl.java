package com.springmvc.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springmvc.demo.models.Product;
import com.springmvc.demo.repositories.ProductRepository;
import com.springmvc.demo.service.ProductServcie;

@Service
public class ProductImpl implements ProductServcie {
	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	@Override
	public Product findById(String id) {
		return productRepository.findById(id).orElse(null);
	}

	@Override
	public void save(Product entity) {
		productRepository.save(entity);
	}

	@Override
	public void delete(String id) {
		productRepository.deleteById(id);
	}

	@Override
	public List<Product> findByCategoryId(String categoryId) {
		return productRepository.findByCategoryId(categoryId);
	}

	@Override
	public String getLastId() {
		return productRepository.getLastId();
	}

}
