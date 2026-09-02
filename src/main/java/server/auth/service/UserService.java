package server.auth.service;

import java.util.Map;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import server.auth.dto.LoginRequest;
import server.auth.dto.RegisterRequest;
import server.auth.dto.ResetPasswordRequest;
import server.auth.dto.UpdateProfileRequest;
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

	public Map<String, Object> login(LoginRequest request)  {

		String email = request.getEmail().trim().toLowerCase();

		userEntity user = userRepository.findByEmail(email)
				.orElseThrow(() -> new CustomException.InvalidPasswordException("メールアドレスまたはパスワードが正しくありません。"));

		if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
			throw new CustomException.InvalidPasswordException("メールアドレスまたはパスワードが正しくありません。");
		}

		return Map.of("id", user.getId(), "email", user.getEmail(), "name", user.getName());
	}

	public Map<String, Object> register(RegisterRequest request) {

		String email = request.getEmail().trim().toLowerCase();
		String name = request.getName().trim();

		if (userRepository.existsByEmail(email)) {
			throw new CustomException.DuplicateEmailException("すでに使用されているメールアドレスです。");
		}

		String userId = UUID.randomUUID().toString().replace("-", "").substring(0, 24);

		String encodedPassword = passwordEncoder.encode(request.getPassword());

		userEntity user = new userEntity(userId, email, encodedPassword, name);

		userRepository.save(user);

		return Map.of("id", user.getId(), "email", user.getEmail(), "name", user.getName());
	}

	@Transactional
	public Map<String, Object> resetPassword(String email, ResetPasswordRequest request) {

		userEntity user = userRepository.findByEmail(email)
				.orElseThrow(() -> new CustomException.NotFoundException("会員情報が見つかりません。"));

		if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
			throw new CustomException.InvalidPasswordException("現在のパスワードが正しくありません。");
		}

		if (passwordEncoder.matches(request.getNewPassword(), user.getPassword())) {
			throw new CustomException.SamePasswordException("新しいパスワードは現在のパスワードと異なるものにしてください。");
		}

		String encodedNewPassword = passwordEncoder.encode(request.getNewPassword());

		user.changePassword(encodedNewPassword);

		userRepository.save(user);

		return Map.of("success", true, "message", "パスワードを更新しました。");
	}

	@Transactional
	public Map<String, Object> updateProfile(String email, UpdateProfileRequest request) {

		userEntity user = userRepository.findByEmail(email)
				.orElseThrow(() -> new CustomException.NotFoundException("会員情報が見つかりません。"));

		String newEmail = request.getEmail().trim().toLowerCase();

		String newName = request.getName().trim();

		if (!newEmail.equals(user.getEmail()) && userRepository.existsByEmail(newEmail)) {

			throw new CustomException.DuplicateEmailException("すでに使用されているメールアドレスです。");
		}

		user.changeProfile(newEmail, newName);

		userRepository.save(user);

		return Map.of("id", user.getId(), "email", user.getEmail(), "name", user.getName());
	}
}