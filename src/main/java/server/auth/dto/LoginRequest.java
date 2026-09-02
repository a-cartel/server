package server.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginRequest {

	@NotBlank(message = "メールアドレスを入力してください。")
	@Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "正しいメールアドレスの形式ではありません。")
	@Size(max = 100, message = "メールアドレスは 100文字以下で入力してください。")
	private String email;

	@NotBlank(message = "パスワードを入力してください。")
	@Size(min = 4, max = 100, message = "パスワードは 4文字以上 100文字以下で入力してください。")
	private String password;
}