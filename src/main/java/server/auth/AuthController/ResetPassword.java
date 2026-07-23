package server.auth.AuthController;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import server.auth.dto.ResetPasswordRequest;
import server.auth.service.UserService;
import server.exception.CustomException;

@RestController
@RequestMapping("/auth")
public class ResetPassword {

	private final UserService userService;

	public ResetPassword(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/resetPassword")
	public Map<String, Object> resetPassword(@Valid @RequestBody ResetPasswordRequest request,
			HttpServletRequest httpRequest) {

		HttpSession session = httpRequest.getSession(false);

		if (session == null) {
			throw new CustomException.UnauthorizedException("세션이 없습니다.");
		}

		Object user = session.getAttribute("user");

		if (!(user instanceof Map<?, ?> userData)) {
			throw new CustomException.UnauthorizedException("세션이 없습니다.");
		}

		Object id = userData.get("id");

		if (!(id instanceof String userId)) {
			throw new CustomException.UnauthorizedException("세션이 없습니다.");
		}

		Map<String, Object> result = userService.resetPassword(userId, request);

		session.invalidate();

		return result;
	}
}