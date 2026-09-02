package server.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResetPasswordRequest {

	@NotBlank(message = "現在のパスワードを入力してください。")
	@Size(min = 4, max = 100, message = "現在のパスワードは 4文字以上 100文字以下で入力してください。")
	private String currentPassword;

	@NotBlank(message = "新しいパスワードを入力してください。")
	@Size(min = 4, max = 100, message = "新しいパスワードは 4文字以上 100文字以下で入力してください。")
	private String newPassword;
}