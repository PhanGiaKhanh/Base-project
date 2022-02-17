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
import com.example.relationshipSpring.models.Author;
import com.example.relationshipSpring.repositories.AuthorRepository;

@RestController
@RequestMapping("/api/v1/authors")
public class AuthorController {
	@Autowired
	private AuthorRepository authorRepository;

	@GetMapping("")
	public ResponseEntity<List<Author>> getAll(){
		return ResponseEntity.ok(authorRepository.findAll());
	}

	@PostMapping("insert")
	public ResponseEntity<ResponseObject> insertBook(@RequestBody Author author) {
		try {
			return ResponseEntity
					.ok(new ResponseObject(true, "Insert Author successfully", authorRepository.save(author)));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ResponseObject(false, "Insert Author failed", ""));
		}
	}

	@DeleteMapping("delete/{id}")
	public ResponseEntity<ResponseObject> removeBook(@PathVariable int id) {
		try {
			authorRepository.deleteById(id);
			if (authorRepository.findById(id).isPresent()) {
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
						.body(new ResponseObject(false, "Insert Author failed", ""));
			}
			return ResponseEntity.ok(new ResponseObject(true, "Delete Author successfully", ""));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false, "Id not exists", ""));
		}
	}
	
}
