package server.auth.AuthController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import server.auth.dto.LoginRequest;
import server.auth.dto.LoginResponse;
import server.auth.service.UserService;

@RestController
@RequestMapping("/auth")
public class Login {

    private final UserService userService;

    public Login(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest request,
            HttpSession session
    ) {

        LoginResponse response =
                userService.login(request);

        if (response.isLogin()) {

            // 리뷰 삭제 시 로그인 회원과 작성자를 비교하기 위해 저장
            session.setAttribute(
                    "LOGIN_USER_ID",
                    response.getUserId()
            );

            // 기존 비밀번호 변경 등의 기능에서 사용하는 이메일
            session.setAttribute(
                    "LOGIN_USER_EMAIL",
                    response.getEmail()
            );

            return ResponseEntity.ok(response);
        }

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(response);
    }
}