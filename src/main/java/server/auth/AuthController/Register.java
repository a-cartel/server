package server.auth.AuthController;

import java.util.Map;

import org.springframework.web.bind.annotation.*;

import server.auth.dto.RegisterRequest;
import server.auth.service.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class Register {

	private final UserService userService;

	public Register(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/register")
	public Map<String, Object> register(@Valid @RequestBody RegisterRequest request) {
		return userService.register(request);
	}
}