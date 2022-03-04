package com.KhanhPG.springbatch.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
	* @author: KhanhPG
	* @since: 04/03/2022	
	*/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
	private int id;
	private String name;
	private String email;
	private String address;
	
	public static String[] fields() {
		return new String[] {"id", "name", "email", "address"};
	}

	@Override
	public String toString() {
		return id + ", " + name + ", " + email + ", " + address;
	}
	
	
}
