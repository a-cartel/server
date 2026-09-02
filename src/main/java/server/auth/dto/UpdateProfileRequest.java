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
public class UpdateProfileRequest {

    @NotBlank(message = "メールアドレスを入力してください。")
    @Pattern(
            regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
            message = "正しいメールアドレスの形式ではありません。"
    )
    @Size(max = 40, message = "メールアドレスは 40文字以下で入力してください。")
    private String email;

    @NotBlank(message = "名前を入力してください。")
    @Size(max = 8, message = "名前は 8文字以下で入力してください。")
    private String name;
}