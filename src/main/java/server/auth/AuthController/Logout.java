package server.auth.AuthController;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import server.util.SessionUtil;

@RestController
@RequestMapping("/auth")
public class Logout {

	@GetMapping("/logout")
	public Map<String, Object> logout(HttpServletRequest request) {

		SessionUtil.logout(request.getSession(false));
		return Map.of("success", true, "message", "ログアウトしました。");
	}
}
