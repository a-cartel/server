package server.auth.AuthController;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/auth")
public class UserStatus {

	@GetMapping("/status")
	public Map<String, Object> status(HttpServletRequest request) {

		HttpSession session = request.getSession(false);

		if (session == null) {
			return Map.of("login", false);
		}

		Object user = session.getAttribute("user");

		if (!(user instanceof Map<?, ?> userData)) {
			return Map.of("login", false);
		}

		Object email = userData.get("email");

		Object name = userData.get("name");

		if (!(email instanceof String) || !(name instanceof String)) {

			return Map.of("login", false);
		}

		return Map.of("login", true, "email", email, "name", name);
	}
}