package server.auth.AuthController;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import server.auth.service.UserService;

@RestController
@RequestMapping("/auth")
public class Logout {

	private final UserService userService;

	public Logout(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/logout")
	public Map<String, Object> logout() {
		return userService.logout();
	}
}