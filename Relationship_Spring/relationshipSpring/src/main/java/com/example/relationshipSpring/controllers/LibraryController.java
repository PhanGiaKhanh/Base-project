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
import com.example.relationshipSpring.models.Library;
import com.example.relationshipSpring.repositories.LibraryRepository;

@RestController
@RequestMapping("/api/v1/libraries")
public class LibraryController {
	@Autowired
	private LibraryRepository libraryRepository;

	@GetMapping("")
	public ResponseEntity<List<Library>> getAll(){
		return ResponseEntity.ok(libraryRepository.findAll());
	}

	@PostMapping("insert")
	public ResponseEntity<ResponseObject> insertLibrary(@RequestBody Library library) {
		try {
			libraryRepository.insertLibrary(library.getName(), library.getAddress().getId());
			return ResponseEntity
					.ok(new ResponseObject(true, "Insert library successfully", ""));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ResponseObject(false, "Insert library failed", ""));
		}
	}

	@DeleteMapping("delete/{id}")
	public ResponseEntity<ResponseObject> removeLibrary(@PathVariable int id) {
		try {
			libraryRepository.deleteById(id);
			if(libraryRepository.findById(id).isPresent()) {
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseObject(false, "Delete library failed", ""));
			}
			return ResponseEntity.ok(new ResponseObject(true, "Delete library successfully", ""));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false, "Id not exists", ""));
		}
	}
	
}
