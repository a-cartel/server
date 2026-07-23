package server.auth.AuthController;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/auth")
public class UserStatus {

    @SuppressWarnings("unchecked")
    @GetMapping("/status")
    public Map<String, Object> status(HttpSession session) {
        Map<String, Object> sessionUser = (Map<String, Object>) session.getAttribute("user");

        if (sessionUser == null) {
            return Map.of("login", false);
        }

        return Map.of(
                "login", true,
                "name", sessionUser.get("name"));
    }
}