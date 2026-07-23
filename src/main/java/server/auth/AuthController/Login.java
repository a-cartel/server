package server.auth.AuthController;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import server.auth.dto.LoginRequest;
import server.auth.service.UserService;

@RestController
@RequestMapping("/auth")
public class Login {

	private final UserService userService;

	public Login(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/login")
	public Map<String, Object> login(@Valid @RequestBody LoginRequest request, HttpSession session) {

		Map<String, Object> result = userService.login(request);

		session.setAttribute("user", result.get("data"));

		return result;
	}
}