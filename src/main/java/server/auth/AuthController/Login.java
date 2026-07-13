package server.auth.AuthController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import server.auth.dto.LoginRequest;
import server.auth.dto.LoginResponse;
import server.auth.service.UserService;

@RestController
@RequestMapping("/auth")
public class Login {

	private final UserService userService;

	public Login(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {

		LoginResponse response = userService.login(request);

		if (response.isLogin()) {
			return ResponseEntity.ok(response);
		}

		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
	}
}