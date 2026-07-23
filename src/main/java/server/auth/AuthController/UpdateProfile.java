package server.auth.AuthController;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import server.auth.dto.UpdateProfileRequest;
import server.auth.service.UserService;
import server.exception.CustomException;

@RestController
@RequestMapping("/auth")
public class UpdateProfile {

	private final UserService userService;

	public UpdateProfile(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/update")
	public Map<String, Object> updateProfile(@Valid @RequestBody UpdateProfileRequest request,
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

		Map<String, Object> result = userService.updateProfile(userId, request);

		session.setAttribute("user", result.get("data"));

		return result;
	}
}