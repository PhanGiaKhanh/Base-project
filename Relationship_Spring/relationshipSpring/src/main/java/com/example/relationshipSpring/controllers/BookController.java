package com.example.relationshipSpring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.relationshipSpring.dto.ResponseObject;
import com.example.relationshipSpring.models.Book;
import com.example.relationshipSpring.repositories.BookRepository;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {
	@Autowired
	private BookRepository bookRepository;

	@GetMapping("")
	public ResponseEntity<List<Book>> getAll(){
		return ResponseEntity.ok(bookRepository.findAll());
	}

	@PostMapping("insert")
	public ResponseEntity<ResponseObject> insertBook(@RequestBody Book book) {
		try {
			return ResponseEntity
					.ok(new ResponseObject(true, "Insert Book successfully", bookRepository.save(book)));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ResponseObject(false, "Insert book failed", ""));
		}
	}

	@DeleteMapping("delete/{id}")
	public ResponseEntity<ResponseObject> removeBook(@PathVariable int id) {
		try {
			bookRepository.deleteById(id);
			if (bookRepository.findById(id).isPresent()) {
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
						.body(new ResponseObject(false, "Insert book failed", ""));
			}
			return ResponseEntity.ok(new ResponseObject(true, "Delete Book successfully", ""));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false, "Id not exists", ""));
		}
	}
	
}
