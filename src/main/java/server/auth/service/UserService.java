package server.auth.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import server.auth.dto.LoginRequest;
import server.auth.dto.RegisterRequest;
import server.auth.dto.ResetPasswordRequest;
import server.auth.entity.userEntity;
import server.auth.repository.userRepository;
import server.exception.CustomException;

@Service
public class UserService {

    private final userRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(userRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Map<String, Object> login(LoginRequest request) {
        String email = request.getEmail().trim().toLowerCase();

        userEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException.NotFoundException("이메일 또는 비밀번호가 올바르지 않습니다."));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new CustomException.InvalidPasswordException("이메일 또는 비밀번호가 올바르지 않습니다.");
        }

        Map<String, Object> data = new HashMap<>();
        data.put("email", user.getEmail());
        data.put("name", user.getName());

        return Map.of(
                "success", true,
                "message", "로그인이 완료되었습니다.",
                "data", data
        );
    }

    public Map<String, Object> register(RegisterRequest request) {
        String email = request.getEmail().trim().toLowerCase();

        if (userRepository.existsByEmail(email)) {
            throw new CustomException.DuplicateEmailException("이미 사용중인 이메일 입니다.");
        }

        String userId = UUID.randomUUID().toString().replace("-", "").substring(0, 24);
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        userEntity user = new userEntity(userId, email, encodedPassword, request.getName());
        userRepository.save(user);

        Map<String, Object> data = new HashMap<>();
        data.put("email", user.getEmail());
        data.put("name", user.getName());

        return Map.of(
                "success", true,
                "message", "회원가입이 완료되었습니다.",
                "data", data
        );
    }

    @Transactional
    public Map<String, Object> resetPassword(String email, ResetPasswordRequest request) {
        userEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException.NotFoundException("회원 정보를 찾을 수 없습니다."));

        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new CustomException.InvalidPasswordException("현재 비밀번호가 올바르지 않습니다.");
        }

        if (passwordEncoder.matches(request.getNewPassword(), user.getPassword())) {
            throw new CustomException.SamePasswordException("새 비밀번호는 현재 비밀번호와 달라야 합니다.");
        }

        user.changePassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);

        return Map.of(
                "success", true,
                "message", "비밀번호가 변경되었습니다."
        );
    }
}