package com.KhanhPG.springboot.dto;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.KhanhPG.springboot.validation.ValidAge;

/**
 * @author: KhanhPG
 * @since: 21/02/2022
 */

public class UserDto {
	private long id;

	// user name should not be null or empty
	// user name should have at least 2 characters
	@NotEmpty
	@Size(min = 2, message = "user name should have at least 2 characters")
	private String name;

	// email should be a valid email format
	// email should be null or empty
	@NotEmpty
	@Email
	private String email;

	// password should not be null or empty
	// password should have at least 6 characters
	@NotEmpty
	@Size(min = 6, message = "password should have at least 6 characters")
	private String password;
	@ValidAge
//	@Min(value = 18, message = "must be greater or equal to 18")
	@NotNull(message = "{notnull.age}")
	private Integer age;

//	@NotNull(message = "birthday is required field")
//	@DateTimeFormat(pattern = "MM-dd-yyyy")
	private LocalDate birthday;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

}
