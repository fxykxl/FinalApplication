package com.example.demo.payload;

import javax.validation.constraints.NotBlank;

public class LoginRequest {
	
	@NotBlank(message="Email Cannot Be Blank")
	private String email;
	
	@NotBlank(message="Password Cannot Be Blank")
	private String password;

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPasswordClient(String password) {
		this.password = password;
	}
	
	
	
	

}
