package server.review.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "POKE_REVIEWS")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewEntity {

	@Id
	@Column(name = "REVIEW_ID", length = 24, nullable = false)
	private String reviewId;

	@Column(name = "USER_ID", length = 24, nullable = false)
	private String userId;

	@Column(name = "SHOP_ID", length = 24, nullable = false)
	private String shopId;

	@Lob
	@Column(name = "CONTENT")
	private String content;

	@Column(name = "RATING", nullable = false)
	private Integer rating;

	@Column(name = "CREATED_AT", nullable = false, insertable = false, updatable = false)
	private LocalDateTime createdAt;
}