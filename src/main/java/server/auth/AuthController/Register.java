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
import server.auth.dto.RegisterRequest;
import server.auth.service.UserService;
import server.util.SessionUtil;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class Register {

	private final UserService userService;

	@PostMapping("/register")
	public Map<String, Object> register(@Valid @RequestBody RegisterRequest request, HttpServletRequest httpRequest) {

		Map<String, Object> result = userService.register(request);

		HttpSession session = httpRequest.getSession(true);
		System.out.println("session : " + session);
		SessionUtil.login(session, result);

		// return result;
		return Map.of("success", true, "message", "ログインしました。", "data", result);
	}
}
