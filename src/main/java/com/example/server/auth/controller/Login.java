package com.example.server.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.server.auth.dto.LoginRequest;
import com.example.server.auth.dto.LoginResponse;

@RestController
@RequestMapping("/auth")
public class Login {

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {

		System.out.println("로그인 이메일: " + request.getEmail());
		System.out.println("로그인 비밀번호: " + request.getPassword());

		String testEmail = "test@test.com";
		String testPassword = "1234";

		if (testEmail.equals(request.getEmail()) && testPassword.equals(request.getPassword())) {

			LoginResponse response = new LoginResponse("로그인 성공", true, request.getEmail());

			return ResponseEntity.ok(response);
		}

		LoginResponse response = new LoginResponse("로그인 실패", false, null);

		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
	}
}