package com.example.server.auth.dto;

public class LoginResponse {

	private String message;
	private boolean login;
	private String email;

	public LoginResponse(String message, boolean login, String email) {
		this.message = message;
		this.login = login;
		this.email = email;
	}

	public String getMessage() {
		return message;
	}

	public boolean isLogin() {
		return login;
	}

	public String getEmail() {
		return email;
	}
}