package server.auth.AuthController;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import server.auth.dto.ResetPasswordRequest;
import server.auth.service.UserService;
import server.util.SessionUtil;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class ResetPassword {

	private final UserService userService;

	@PostMapping("/resetPassword")
	public Map<String, Object> resetPassword(@Valid @RequestBody ResetPasswordRequest request,
			HttpServletRequest httpRequest) {

		String email = SessionUtil.requireEmail(httpRequest.getSession(false));

		Map<String, Object> result = userService.resetPassword(email, request);

		SessionUtil.logout(httpRequest.getSession(false));

		return result;
	}
}
