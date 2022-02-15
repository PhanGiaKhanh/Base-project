package com.example.springboot.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Stream;

import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.springboot.service.IStorageService;

@Service
public class StorageImpl implements IStorageService {
	private final Path storageFolder = Paths.get("uploads");

	// Contructor
	public StorageImpl() {
		try {
			Files.createDirectories(storageFolder);
		} catch (IOException e) {
			throwExeption("Cannot initiallize storage", e);
		}
	}

	// Check đuôi file thuộc ["png", "jpg", "jpeg", "bmp"]
	private boolean isImageFile(MultipartFile file) {
		String fileExtenstion = FilenameUtils.getExtension(file.getOriginalFilename());
		String[] array = { "png", "jpg", "jpeg", "bmp" };
		return Arrays.asList(array).contains(fileExtenstion.trim().toLowerCase());
	}


	private void throwExeption(String string) {
		throw new RuntimeException(string);
	}

	private void throwExeption(String string, IOException e) {
		throw new RuntimeException(string, e);
	}
	
	@Override
	public String storeFile(MultipartFile file) {
		try {
			if (file.isEmpty()) {
				throwExeption("Failed to store empty file.");
			}
			
			// 	Check file is image ?
			if (!isImageFile(file)) {
				throwExeption("You can only upload image file");
			}
			//	file must be <= 5Mb
			float fileSizeInMegabytes = file.getSize() / 1_000_000.0f;
			if (fileSizeInMegabytes > 5.0f) {
				throwExeption("File must be <= 5Mb");
			}
			// 	File must be rename, why ? khi 2 file cùng tên thì tên cũ bị xóa mất
			String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
			String generatedFileName = UUID.randomUUID().toString().replace("-", "");
			generatedFileName = generatedFileName + "." + fileExtension;
			// Đường dẫn đến chi tiết nơi lưu
			Path destinationFilePath = this.storageFolder.resolve(
						Paths.get(generatedFileName)).normalize().toAbsolutePath();
			if (!destinationFilePath.getParent().equals(this.storageFolder.toAbsolutePath())) {
				throwExeption("Cannot store file outside current directory.");
			}
			//	Nếu có thì chép đè lên
			try (InputStream inputStream = file.getInputStream()) {
				Files.copy(inputStream, destinationFilePath, StandardCopyOption.REPLACE_EXISTING);
			}
			return generatedFileName;
		} catch (IOException e) {
			throwExeption("Failed to storage file", e);
		}
		return null;
	}

	@Override
	public Stream<Path> loadAll() {
		try {
            //	list all files in storageFolder
            //	How to fix this ?
            return Files.walk(this.storageFolder, 1)
                    .filter(path -> !path.equals(this.storageFolder) && !path.toString().contains("._"))
                    .map(this.storageFolder::relativize);
        }
        catch (IOException e) {
            throw new RuntimeException("Failed to load stored files", e);
        }
	}

	@Override
	public byte[] readFileContent(String fileName) {
		try {
			// Get resource >> muốn xem return byte / muốn tải về return data resource
			Path file = storageFolder.resolve(fileName);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return StreamUtils.copyToByteArray(resource.getInputStream());
			} else {
				throw new RuntimeException(
                        "Could not read file: " + fileName);
            }
        }
        catch (IOException exception) {
            throw new RuntimeException("Could not read file: " + fileName, exception);
        }
	}

	@Override
	public void deleteAllFiles() {

	}

}
