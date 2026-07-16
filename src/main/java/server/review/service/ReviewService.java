package server.review.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import server.review.dto.ReviewResponse;
import server.review.entity.ReviewEntity;
import server.review.repository.ReviewRepository;

@Service
public class ReviewService {

	private final ReviewRepository reviewRepository;

	public ReviewService(ReviewRepository reviewRepository) {
		this.reviewRepository = reviewRepository;
	}

	@Transactional(readOnly = true)
	public List<ReviewResponse> getReviews(String shopId, String loginUserId) {

		List<ReviewEntity> reviews = reviewRepository.findByShopIdOrderByCreatedAtDesc(shopId);

		return reviews.stream().map(review -> ReviewResponse.from(review, loginUserId)).toList();
	}

	@Transactional
	public void deleteReview(String shopId, String reviewId, String loginUserId) {

		if (loginUserId == null) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인이 필요합니다.");
		}

		ReviewEntity review = reviewRepository.findByReviewIdAndShopId(reviewId, shopId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "리뷰를 찾을 수 없습니다."));

		if (!loginUserId.equals(review.getUserId())) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "본인이 작성한 리뷰만 삭제할 수 있습니다.");
		}

		reviewRepository.delete(review);
	}
}