package server.review.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import server.review.entity.ReviewEntity;

@Getter
@AllArgsConstructor
public class ReviewResponse {

	private String reviewId;
	private String userId;
	private Long shopId;
	private String content;
	private Integer rating;
	private LocalDateTime createdAt;
	private boolean mine;

	public static ReviewResponse from(ReviewEntity review, String loginUserId) {

		boolean mine = loginUserId != null && loginUserId.equals(review.getUserId());

		return new ReviewResponse(review.getReviewId(), review.getUserId(), review.getShopId(), review.getContent(),
				review.getRating(), review.getCreatedAt(), mine);
	}
}