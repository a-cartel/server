package server.auth.AuthController;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import server.auth.dto.ResetPasswordRequest;
import server.auth.service.UserService;

@RestController
@RequestMapping("/auth")
public class ResetPassword {

	private final UserService userService;

	public ResetPassword(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/resetPassword")
	public ResponseEntity<Map<String, Object>> resetPassword(@Valid @RequestBody ResetPasswordRequest request,
			HttpServletRequest httpRequest) {
		HttpSession session = httpRequest.getSession(false);

		if (session == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(Map.of("success", false, "message", "로그인이 필요합니다."));
		}

		String email = (String) session.getAttribute("LOGIN_USER_EMAIL");

		if (email == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(Map.of("success", false, "message", "로그인이 필요합니다."));
		}

		Map<String, Object> response = userService.resetPassword(email, request);

		if (Boolean.FALSE.equals(response.get("success"))) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}

		session.invalidate();

		return ResponseEntity.ok(response);
	}
}