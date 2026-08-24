package server.auth.AuthController;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import server.util.SessionUtil;

@RestController
@RequestMapping("/auth")
public class UserStatus {

	@GetMapping("/status")
	public Map<String, Object> status(HttpServletRequest httpRequest) {

		Map<String, Object> data = SessionUtil.getData(httpRequest.getSession(false));

		if (data == null) {
			return Map.of("login", false);
		}
		return Map.of("login", true, "id", data.get("id"), "email", data.get("email"), "name", data.get("name"));
	}
}
