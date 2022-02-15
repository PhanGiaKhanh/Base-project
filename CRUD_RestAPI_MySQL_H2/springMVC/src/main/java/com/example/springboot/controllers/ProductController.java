package com.example.springboot.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.models.Product;
import com.example.springboot.models.ResponseObject;
import com.example.springboot.repositories.ProductRepository;

@RestController
@RequestMapping(path = "/api/v1/products")
public class ProductController {
	@Autowired // DI
	private ProductRepository repository;

	@GetMapping(value = "")
	// this request is: http://localhost:8080/api/v1/products
	public List<Product> getAllProducts() {
		return repository.findAll();
	}

	@GetMapping(value = "/{id}")
	// Let's return an object with: data, message, status
	public ResponseEntity<ResponseObject> findByID(@PathVariable Long id) {
		Optional<Product> foundProduct = repository.findById(id);
		return foundProduct.isPresent()
				? ResponseEntity.status(HttpStatus.OK)
						.body(new ResponseObject("OK", "Query product successfully", foundProduct))
				: ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(new ResponseObject("false", "Cannot find product with id = " + id, ""));
	}

	// insert new Product with Post method
	// Postman : Raw, JSON
	@PostMapping(value = "")
	public ResponseEntity<ResponseObject> insertProduct(@RequestBody Product newProduct) {
		List<Product> foundProducts = repository.findByProductName(newProduct.getProductName().trim());
		if (foundProducts.size() > 0) {
			// HttpStatus.NOT_IMPLEMENTED > request không có thay đổi gì?
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
					.body(new ResponseObject("failed", "Product name already taken", ""));
		}
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseObject("OK", "Insert Product successfully", repository.save(newProduct)));
	}

	// update, upsert = update if found, otherwise insert
	@PutMapping(value = "/{id}")
	public ResponseEntity<ResponseObject> updateProduct(@RequestBody Product newProduct, @PathVariable Long id) {
		Product updateProduct = repository.findById(id).map(product -> {
			product.setProductName(newProduct.getProductName());
			product.setPrice(newProduct.getPrice());
			product.setUrl(newProduct.getUrl());
			return repository.save(product);
		}).orElseGet(() -> {
			newProduct.setId(id);
			return repository.save(newProduct);
		});
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseObject("OK", "Update Product successfully", updateProduct));
	}

	// Delete a Product by Id
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ResponseObject> deleteProduct(@PathVariable Long id) {
		boolean exists = repository.existsById(id);
		if (exists) {
			repository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("OK", "Delete Product successfully", ""));
		}
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseObject("fasle", "Cannot find Product to delete", ""));
	}
}
