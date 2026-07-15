package server.auth.AuthController;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/auth")
public class LoginStatus {

    @GetMapping("/status")
    public Map<String, Object> getLoginStatus(HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        if (session == null) {
            return Map.of(
                    "login", false,
                    "message", "로그인되어 있지 않습니다."
            );
        }

        String email =
                (String) session.getAttribute("LOGIN_USER_EMAIL");

        if (email == null) {
            return Map.of(
                    "login", false,
                    "message", "로그인되어 있지 않습니다."
            );
        }

        return Map.of(
                "login", true,
                "email", email,
                "message", "로그인 상태입니다."
        );
    }
}