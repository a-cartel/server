package com.example.server;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class healthCheck {

	@GetMapping("/healthCheck")
	public String home() {
		return "서버 열림";
	}

}