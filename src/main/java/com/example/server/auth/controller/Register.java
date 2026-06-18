package com.example.server.auth.controller;

import com.example.server.auth.dto.RegisterRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class Register {

	// 이메일, 패스워드를 받아서 다시 반환하는 테스트용 API
	@PostMapping("/register")
	public Map<String, Object> register(@RequestBody RegisterRequest request) {

		System.out.println("받은 이메일: " + request.getEmail());
		System.out.println("받은 비밀번호: " + request.getPassword());

		Map<String, Object> result = new HashMap<>();

		result.put("message", "회원가입 테스트 성공");
		result.put("email", request.getEmail());
		result.put("password", request.getPassword());

		return result;
	}
}