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

	@NotBlank(message = "현재 비밀번호를 입력해주세요.")
	@Size(min = 4, max = 100, message = "현재 비밀번호는 4자 이상 100자 이하로 입력해주세요.")
	private String currentPassword;

	@NotBlank(message = "새 비밀번호를 입력해주세요.")
	@Size(min = 4, max = 100, message = "새 비밀번호는 4자 이상 100자 이하로 입력해주세요.")
	private String newPassword;
}