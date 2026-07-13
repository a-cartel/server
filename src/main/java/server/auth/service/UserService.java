package server.auth.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import server.auth.dto.LoginRequest;
import server.auth.dto.LoginResponse;
import server.auth.dto.RegisterRequest;
import server.auth.dto.ResetPasswordRequest;
import server.auth.dto.SendVerifyCodeRequest;

@Service
public class UserService {

	public LoginResponse login(LoginRequest request) {

		// TODO: DB 연동 후 POKE_USER 테이블 조회 로직으로 변경
		String testEmail = "test@test.com";
		String testPassword = "1234";

		if (testEmail.equals(request.getEmail()) && testPassword.equals(request.getPassword())) {
			return new LoginResponse("로그인 성공", true, request.getEmail());
		}

		return new LoginResponse("로그인 실패", false, null);
	}

	public Map<String, Object> register(RegisterRequest request) {

		// TODO: DB 연동 후 POKE_USER 테이블 저장 로직으로 변경
		Map<String, Object> result = new HashMap<>();

		result.put("message", "회원가입 테스트 성공");
		result.put("email", request.getEmail());

		return result;
	}

	public Map<String, Object> logout() {

		// TODO: 로그인 유지 방식 확정 후 세션 로그아웃 처리로 변경
		Map<String, Object> result = new HashMap<>();

		result.put("message", "로그아웃 테스트 성공");

		return result;
	}

	public Map<String, Object> sendVerifyCode(SendVerifyCodeRequest request) {

		// TODO: 이메일 발송 기능 연동 후 실제 인증코드 발송 로직으로 변경
		Map<String, Object> result = new HashMap<>();

		result.put("message", "인증코드 발송 테스트 성공");
		result.put("email", request.getEmail());

		return result;
	}

	public Map<String, Object> resetPassword(ResetPasswordRequest request) {

		// TODO: DB 연동 후 인증코드 검증 및 비밀번호 변경 로직으로 변경
		Map<String, Object> result = new HashMap<>();

		result.put("message", "비밀번호 재설정 테스트 성공");
		result.put("email", request.getEmail());

		return result;
	}
}