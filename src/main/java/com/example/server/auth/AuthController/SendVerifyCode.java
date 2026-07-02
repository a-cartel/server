package com.example.server.auth.AuthController;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.server.auth.dto.SendVerifyCodeRequest;
import com.example.server.auth.service.UserService;

@RestController
@RequestMapping("/auth")
public class SendVerifyCode {

	private final UserService userService;

	public SendVerifyCode(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/sendVerifyCode")
	public Map<String, Object> sendVerifyCode(@RequestBody SendVerifyCodeRequest request) {
		return userService.sendVerifyCode(request);
	}
}