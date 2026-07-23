package server.auth.AuthController;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import server.auth.dto.RegisterRequest;
import server.auth.service.UserService;

@RestController
@RequestMapping("/auth")
public class Register {

	private final UserService userService;

	public Register(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/register")
	public Map<String, Object> register(@Valid @RequestBody RegisterRequest request, HttpSession session) {

		Map<String, Object> result = userService.register(request);

		session.setAttribute("user", result.get("data"));

		return result;
	}
}