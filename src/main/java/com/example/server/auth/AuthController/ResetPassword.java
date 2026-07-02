package com.example.server.auth.AuthController;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.server.auth.dto.ResetPasswordRequest;
import com.example.server.auth.service.UserService;

@RestController
@RequestMapping("/auth")
public class ResetPassword {

	private final UserService userService;

	public ResetPassword(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/resetPassword")
	public Map<String, Object> resetPassword(@RequestBody ResetPasswordRequest request) {
		return userService.resetPassword(request);
	}
}