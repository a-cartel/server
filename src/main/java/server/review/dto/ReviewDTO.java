package server.review.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import server.review.entity.ReviewEntity;

/**
 * 요청(생성/수정)과 응답(조회)을 하나로 합친 DTO.
 *
 * - id/shopId/userId/writerName/createdAt: 응답 전용. 클라이언트가 요청 보낼 땐 안 보내도 됨(검증 없음, 우리가 채워서 내려줌).
 * - rating/content: 요청/응답 공통. 요청 시엔 검증 어노테이션이 적용됨.
 *
 * 주의: 엔티티 -> DTO 변환은 생성자가 아니라 정적 팩터리 메서드(from)로 한다.
 * 생성자로 하면 Jackson이 요청 바디를 파싱할 때 이 생성자를 deserialize용으로 잘못 골라서
 * entity가 null인 채로 호출해버리는 문제가 있었음 (NullPointerException 발생).
 */
@Getter
@Setter
@NoArgsConstructor
public class ReviewDTO {

	private Long id;
	private Long shopId;
	private String userId;
	private String writerName;
	private LocalDateTime createdAt;

	@NotNull(message = "星評価をつけてください")
	@Min(value = 1, message = "星評価は 1点以上でなければなりません。")
	@Max(value = 5, message = "星評価は 5点以下でなければなりません。")
	private Integer rating;

	@NotBlank(message = "レビューの内容を入力してください。")
	@Size(max = 1000, message = "レビューは1000文字以下で入力してください。")
	private String content;

	public static ReviewDTO from(ReviewEntity entity, String writerName) {

		ReviewDTO dto = new ReviewDTO();
		dto.id = entity.getId();
		dto.shopId = entity.getShopId();
		dto.userId = entity.getUserId();
		dto.writerName = writerName;
		dto.rating = entity.getRating();
		dto.content = entity.getContent();
		dto.createdAt = entity.getCreatedAt();

		return dto;
	}
}
