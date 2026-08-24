package server.auth.AuthController;

import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import server.config.SessionTracker;

@RestController
@RequestMapping("/auth")
public class SessionDebug {

	@GetMapping("/session")
	public Map<String, Object> session(HttpServletRequest httpRequest) {

		HttpSession session = httpRequest.getSession(false);

		if (session == null) {
			return Map.of("success", true, "message", "세션이 없습니다.");
		}

		return Map.of(
				"success", true,
				"sessionId", session.getId(),
				"attributes", readAttributes(session));
	}

	@GetMapping("/sessions")
	public Map<String, Object> allSessions() {

		Map<String, Object> result = new LinkedHashMap<>();

		SessionTracker.getSessions().forEach((id, session) -> {
			try {
				result.put(id, Map.of(
						"creationTime", session.getCreationTime(),
						"lastAccessedTime", session.getLastAccessedTime(),
						"attributes", readAttributes(session)));
			} catch (IllegalStateException e) {
				// 조회하는 그 짧은 순간에 이미 invalidate된 세션 -> 그냥 건너뜀
			}
		});

		return Map.of("success", true, "sessionCount", result.size(), "sessions", result);
	}

	private Map<String, Object> readAttributes(HttpSession session) {

		Map<String, Object> attributes = new LinkedHashMap<>();
		Enumeration<String> names = session.getAttributeNames();

		while (names.hasMoreElements()) {
			String name = names.nextElement();
			attributes.put(name, session.getAttribute(name));
		}

		return attributes;
	}
}
