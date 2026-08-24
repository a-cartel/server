package server.util;

import java.util.Map;

import jakarta.servlet.http.HttpSession;
import server.exception.CustomException;

public class SessionUtil {

	private static final String DATA_KEY = "user";

	public static void login(HttpSession session, Map<String, Object> data) {
		session.setAttribute(DATA_KEY, data);
	}

	public static boolean isLoggedIn(HttpSession session) {
		return session != null;
	}

	@SuppressWarnings("unchecked")
	public static Map<String, Object> getData(HttpSession session) {
		if (!isLoggedIn(session)) {
			return null;
		}
		return (Map<String, Object>) session.getAttribute(DATA_KEY);
	}

	public static Map<String, Object> requireData(HttpSession session) {
		Map<String, Object> data = getData(session);
		if (data == null) {
			throw new CustomException.UnauthorizedException("세션이 없습니다.");
		}
		return data;
	}

	public static String requireEmail(HttpSession session) {
		return (String) requireData(session).get("email");
	}

	public static void logout(HttpSession session) {
		if (session != null) {
			session.invalidate();
		}
	}
}
