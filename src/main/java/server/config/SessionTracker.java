package server.config;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionListener;

@Component
public class SessionTracker implements HttpSessionListener {

	private static final Map<String, HttpSession> sessions = new ConcurrentHashMap<>();

	public static Map<String, HttpSession> getSessions() {
		return sessions;
	}
}
