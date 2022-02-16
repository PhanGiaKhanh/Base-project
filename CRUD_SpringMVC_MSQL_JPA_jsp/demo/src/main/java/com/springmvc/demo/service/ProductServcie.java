package com.springmvc.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springmvc.demo.models.Product;

@Service
public interface ProductServcie extends GenericService<Product>{
	List<Product> findByCategoryId(String categoryId);
	String getLastId();
}
