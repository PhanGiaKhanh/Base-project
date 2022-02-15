package com.example.springboot.service;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.web.multipart.MultipartFile;

public interface IStorageService {
	String storeFile(MultipartFile file);
	Stream<Path> loadAll(); // load all file inside a folder
	byte[] readFileContent(String fileName);
	void deleteAllFiles();
}
	
