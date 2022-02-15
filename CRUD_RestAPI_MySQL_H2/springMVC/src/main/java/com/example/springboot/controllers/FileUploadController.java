package com.example.springboot.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.example.springboot.models.ResponseObject;
import com.example.springboot.service.IStorageService;

@Controller
@RequestMapping(path = "/api/v1/FileUpload")
public class FileUploadController {
	// Injectt Storage Service here
	@Autowired
	private IStorageService iStorageService;

	// This controller receive file/image from client
	@PostMapping("")
	public ResponseEntity<ResponseObject> uploadFile(@RequestParam("file") MultipartFile file) {
		try {
			// save files to a folder => use service
			String generatedFileName = iStorageService.storeFile(file);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("OK", "upload file successfully", generatedFileName));
			// ex: 870e84e08ab64ef78a9d02e7634c789f.jpg => how to open this file in Web
			// Browser?
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
					.body(new ResponseObject("fale", e.getMessage(), ""));
		}
	}

	// get image's url
	// http://localhost:8080/api/v1/FileUpload/files/870e84e08ab64ef78a9d02e7634c789f.jpg
	@GetMapping("/files/{fileName:.+}")
	public ResponseEntity<byte[]> readDetailFile(@PathVariable String fileName) {
		try {
			byte[] bytes = iStorageService.readFileContent(fileName);
			return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bytes);
		} catch (Exception e) {
			return ResponseEntity.noContent().build();
		}
	}

	// How to load all uploaded files ?
	@GetMapping("")
	public ResponseEntity<ResponseObject> getUploadedFiles() {
		try {
			List<String> urls = iStorageService.loadAll().map(path -> {
				// convert fileName to url(send request "readDetailFile")
				return MvcUriComponentsBuilder
						.fromMethodName(FileUploadController.class, "readDetailFile", path.getFileName().toString())
						.build().toUri().toString();
			}).collect(Collectors.toList());
			return ResponseEntity.ok(new ResponseObject("ok", "List files successfully", urls));
		} catch (Exception exception) {
			return ResponseEntity.ok(new ResponseObject("failed", "List files failed", new String[] {}));
		}
	}
};