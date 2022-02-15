package com.example.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.springboot.models.ResponseObject;
import com.example.springboot.service.IStorageService;

@Controller
@RequestMapping(path = "/api/v1/FileUpload")
public class FileUploadController {
	//Injectt Storage Service here
	@Autowired
	private IStorageService iStorageService;
	
	
	//This controller receive file/image from client
	@PostMapping("")
	public ResponseEntity<ResponseObject> uploadFile(@RequestParam("file") MultipartFile file) {
		try {
			// save files to a folder => use service
			String generatedFileName = iStorageService.storeFile(file);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("OK", "upload file successfully", generatedFileName));
			// ex: 870e84e08ab64ef78a9d02e7634c789f.jpg => how to open this file in Web Browser? 
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
					.body(new ResponseObject("fale", e.getMessage(), ""));
		}
	}
	
	// get image's url 
	@GetMapping("/files/{fileName:.+}")
	public ResponseEntity<byte[]> readDetailFile(@PathVariable String fileName) {
		
		return null;
	}
}
