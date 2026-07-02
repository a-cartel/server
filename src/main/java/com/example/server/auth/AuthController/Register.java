package com.example.server.auth.AuthController;

import java.util.Map;

import org.springframework.web.bind.annotation.*;

import com.example.server.auth.dto.RegisterRequest;
import com.example.server.auth.service.UserService;

@RestController
@RequestMapping("/auth")
public class Register {

	private final UserService userService;

	public Register(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/register")
	public Map<String, Object> register(@RequestBody RegisterRequest request) {
		return userService.register(request);
	}
}