package server.auth.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import server.auth.dto.LoginRequest;
import server.auth.dto.LoginResponse;
import server.auth.dto.RegisterRequest;
import server.auth.dto.ResetPasswordRequest;
import server.auth.dto.SendVerifyCodeRequest;
import server.auth.entity.userEntity;
import server.auth.repository.userRepository;
import server.exception.DuplicateEmailException;

@Service
public class UserService {

    private final userRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(
            userRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public LoginResponse login(LoginRequest request) {

        String email = request.getEmail()
                .trim()
                .toLowerCase();

        userEntity user = userRepository
                .findByEmail(email)
                .orElse(null);

        if (user == null) {
            return new LoginResponse(
                    "이메일 또는 비밀번호가 올바르지 않습니다.",
                    false,
                    null,
                    null
            );
        }

        boolean passwordMatches = passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        );

        if (!passwordMatches) {
            return new LoginResponse(
                    "이메일 또는 비밀번호가 올바르지 않습니다.",
                    false,
                    null,
                    null
            );
        }

        return new LoginResponse(
                "로그인 성공",
                true,
                user.getId(),
                user.getEmail()
        );
    }

    public Map<String, Object> register(RegisterRequest request) {

        Map<String, Object> result = new HashMap<>();

        String email = request.getEmail()
                .trim()
                .toLowerCase();

        if (userRepository.existsByEmail(email)) {
            throw new DuplicateEmailException(
                    "이미 사용 중인 이메일입니다."
            );
        }

        String userId = UUID.randomUUID()
                .toString()
                .replace("-", "")
                .substring(0, 24);

        String encodedPassword =
                passwordEncoder.encode(request.getPassword());

        userEntity user = new userEntity(
                userId,
                email,
                encodedPassword,
                request.getName()
        );

        userRepository.save(user);

        result.put("success", true);
        result.put("message", "회원가입이 완료되었습니다.");
        result.put("id", userId);
        result.put("email", email);
        result.put("name", request.getName());

        return result;
    }

    public Map<String, Object> sendVerifyCode(
            SendVerifyCodeRequest request
    ) {

        Map<String, Object> result = new HashMap<>();

        result.put(
                "message",
                "인증코드 발송 테스트 성공"
        );

        result.put(
                "email",
                request.getEmail()
        );

        return result;
    }

    @Transactional
    public Map<String, Object> resetPassword(
            String email,
            ResetPasswordRequest request
    ) {

        Map<String, Object> result = new HashMap<>();

        userEntity user = userRepository
                .findByEmail(email)
                .orElse(null);

        if (user == null) {
            result.put("success", false);
            result.put(
                    "message",
                    "회원 정보를 찾을 수 없습니다."
            );

            return result;
        }

        boolean currentPasswordMatches =
                passwordEncoder.matches(
                        request.getCurrentPassword(),
                        user.getPassword()
                );

        if (!currentPasswordMatches) {
            result.put("success", false);
            result.put(
                    "message",
                    "현재 비밀번호가 올바르지 않습니다."
            );

            return result;
        }

        boolean sameAsCurrentPassword =
                passwordEncoder.matches(
                        request.getNewPassword(),
                        user.getPassword()
                );

        if (sameAsCurrentPassword) {
            result.put("success", false);
            result.put(
                    "message",
                    "새 비밀번호는 현재 비밀번호와 달라야 합니다."
            );

            return result;
        }

        String encodedNewPassword =
                passwordEncoder.encode(
                        request.getNewPassword()
                );

        user.changePassword(encodedNewPassword);

        userRepository.save(user);

        result.put("success", true);
        result.put(
                "message",
                "비밀번호가 변경되었습니다."
        );

        return result;
    }
}