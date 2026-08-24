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
import server.auth.dto.LoginRequest;
import server.auth.service.UserService;
import server.util.SessionUtil;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class Login {

	private final UserService userService;

	@PostMapping("/login")
	public Map<String, Object> login(@Valid @RequestBody LoginRequest request, HttpServletRequest httpRequest) {

		Map<String, Object> user = userService.login(request);

		HttpSession session = httpRequest.getSession(true);
		SessionUtil.login(session, user);

		System.out.println("[LOGIN] sessionId=" + session.getId() + " user=" + user);

		return Map.of("success", true, "message", "로그인이 완료되었습니다.", "data", user);
	}
}
