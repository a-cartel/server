package server.review.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import server.exception.CustomException;
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
	public List<ReviewResponse> getReviews(Long shopId, String loginUserId) {

		return reviewRepository.findByShopIdOrderByCreatedAtDesc(shopId).stream()
				.map(review -> ReviewResponse.from(review, loginUserId)).toList();
	}

	@Transactional
	public void deleteReview(Long shopId, String reviewId, String loginUserId) {

		if (loginUserId == null) {
			throw new CustomException.UnauthorizedException("세션이 없습니다.");
		}

		ReviewEntity review = reviewRepository.findByReviewIdAndShopIdAndUserId(reviewId, shopId, loginUserId)
				.orElseThrow(() -> new CustomException.NotFoundException("리뷰를 찾을 수 없습니다."));

		reviewRepository.delete(review);
	}
}