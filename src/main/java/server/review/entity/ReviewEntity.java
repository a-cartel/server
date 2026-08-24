package server.review.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "POKE_SHOP_REVIEW")
@Getter
@NoArgsConstructor
public class ReviewEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "SHOP_ID", nullable = false)
	private Long shopId;

	// POKE_USER(ID)를 FK로 참조. JPA 연관관계는 안 맺고(ShopEntity/userEntity와 같은 스타일) 값만 저장.
	@Column(name = "USER_ID", length = 24, nullable = false)
	private String userId;

	@Column(name = "RATING", nullable = false)
	private Integer rating;

	@Column(name = "CONTENT", length = 1000, nullable = false)
	private String content;

	@Column(name = "CREATED_AT", nullable = false, updatable = false)
	private LocalDateTime createdAt;

	public ReviewEntity(Long shopId, String userId, Integer rating, String content) {
		this.shopId = shopId;
		this.userId = userId;
		this.rating = rating;
		this.content = content;
		this.createdAt = LocalDateTime.now();
	}

	public void update(Integer rating, String content) {
		this.rating = rating;
		this.content = content;
	}
}
