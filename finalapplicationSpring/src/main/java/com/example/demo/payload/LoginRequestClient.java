package com.example.demo.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class LoginRequestClient {
	
	@NotNull(message="Phone number Cannot Be Blank")
	private Long phone;
	
	@NotBlank(message="Password Cannot Be Blank")
	private String password;

	public Long getPhone() {
		return this.phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPasswordClient(String password) {
		this.password = password;
	}

}
