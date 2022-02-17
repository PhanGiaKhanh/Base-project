package com.example.relationshipSpring.controllers;

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
import com.example.relationshipSpring.models.Address;
import com.example.relationshipSpring.repositories.AddressRepository;

@RestController
@RequestMapping("/api/v1/addresses")
public class AddressController {
	@Autowired
	private AddressRepository addressRepository;

	@GetMapping("")
	public ResponseEntity<ResponseObject> getAll() {
		return ResponseEntity.ok(new ResponseObject(true, "Get all address successfully", addressRepository.findAll()));
	}

	@PostMapping("insert")
	public ResponseEntity<ResponseObject> insertAddress(@RequestBody Address address) {
		try {
			return ResponseEntity
					.ok(new ResponseObject(true, "Insert address successfully", addressRepository.save(address)));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_EXTENDED)
					.body(new ResponseObject(false, "Insert address failed", ""));
		}
	}

	@DeleteMapping("delete/{id}")
	public ResponseEntity<ResponseObject> removeAddress(@PathVariable int id) {
		try {
			addressRepository.deleteById(id);
			return ResponseEntity.ok(new ResponseObject(true, "Delete address successfully", ""));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(false, "Id not exists", ""));
		}
	}
}
