package com.example.demoEmail.registration;

/**
 * @author: KhanhPG
 * @since: 24/02/2022
 */
public class RegistrationRequest {
	private String firstName;
	private String lastName;
	private String email;
	private String password;

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public String toString() {
		return "RegistrationRequest [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + "]";
	}

}
