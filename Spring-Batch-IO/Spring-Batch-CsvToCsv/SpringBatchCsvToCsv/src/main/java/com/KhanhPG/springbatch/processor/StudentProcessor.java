package com.KhanhPG.springbatch.processor;

import org.springframework.batch.item.ItemProcessor;

import com.KhanhPG.springbatch.model.Student;

/**
	* @author: KhanhPG
	* @since: 04/03/2022	
	*/
public class StudentProcessor implements ItemProcessor<Student, Student>{

	@Override
	public Student process(Student item) throws Exception {
		final String name = item.getName().toUpperCase();
		final String email = item.getEmail().toUpperCase();
		final String address = item.getAddress().toUpperCase();
		final Student data = new Student(item.getId(), name, email, address);
		return data;
	}

}
