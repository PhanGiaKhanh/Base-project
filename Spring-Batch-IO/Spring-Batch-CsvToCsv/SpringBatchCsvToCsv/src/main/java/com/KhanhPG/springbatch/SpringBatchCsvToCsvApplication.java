package com.KhanhPG.springbatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
public class SpringBatchCsvToCsvApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBatchCsvToCsvApplication.class, args);
	}

}
