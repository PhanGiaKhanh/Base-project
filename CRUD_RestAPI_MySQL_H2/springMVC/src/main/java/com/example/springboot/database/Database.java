package com.example.springboot.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.springboot.models.Product;
import com.example.springboot.repositories.ProductRepository;

@Configuration // Bên trong chứa hàm chạy cùng lúc với khởi động chương trình
public class Database {
	// logger - Đọc lỗi
	private static final Logger logger = LoggerFactory.getLogger(Database.class);
	
	@Bean
	CommandLineRunner initDatabase(ProductRepository productRepository) {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
//				Product productA = new Product("Iphone 111", 2000, 24000.0, "");
//				Product productB = new Product("Samsum 222", 2001, 23000.0, "");
//				logger.info("insert data: " + productRepository.save(productA));
//				logger.info("insert data: " + productRepository.save(productB));
			}
		};
	}
}
