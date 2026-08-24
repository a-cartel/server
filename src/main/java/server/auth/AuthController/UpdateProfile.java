package server.auth.AuthController;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import server.auth.dto.UpdateProfileRequest;
import server.auth.service.UserService;
import server.util.SessionUtil;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UpdateProfile {

	private final UserService userService;

	@PostMapping("/update")
	public Map<String, Object> updateProfile(@Valid @RequestBody UpdateProfileRequest request,
			HttpServletRequest httpRequest) {

		String email = SessionUtil.requireEmail(httpRequest.getSession(false));

		Map<String, Object> result = userService.updateProfile(email, request);

		HttpSession session = httpRequest.getSession(false);
		SessionUtil.login(session, result);

		return Map.of("success", true, "message", "회원 정보가 변경되었습니다.", "data", result);
	}
}
