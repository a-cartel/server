// package server.auth.AuthController;

// import java.util.HashMap;
// import java.util.Map;

// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpSession;
// import jakarta.validation.Valid;
// // import server.auth.dto.UpdateProfileRequest;
// import server.auth.service.UserService;
// import server.exception.CustomException;

// @RestController
// @RequestMapping("/auth")
// public class UpdateProfile {

// 	// private final UserService userService;

// 	// public ResetPassword(UserService userService) {
// 	// 	this.userService = userService;
// 	// }

// 	@PostMapping("/update")
// 	public Map<String, Object> resetPassword(
// 			@Valid @RequestBody UpdateProfile request,
// 			HttpServletRequest httpRequest) {

// 		HttpSession session = httpRequest.getSession(false);

// 		if (session == null) {
// 			throw new CustomException.UnauthorizedException("세션이 존재하지 않습니다.");
// 		}

// 		@SuppressWarnings("unchecked")
// 		Map<String, Object> userData = (Map<String, Object>) session.getAttribute("user");

// 		if (userData == null) {
// 			throw new CustomException.UnauthorizedException("로그인이 필요합니다.");
// 		}

// 		String email = (String) userData.get("email");
// 		Map<String, Object> result = userService.resetPassword(email, request);
// 		session.invalidate();

// 		return result;
// 	}
// }