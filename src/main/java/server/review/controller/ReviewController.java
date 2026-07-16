package server.review.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import server.review.dto.ReviewResponse;
import server.review.service.ReviewService;

@RestController
@RequestMapping("/info/shops/{shopId}/reviews")
public class ReviewController {

	private final ReviewService reviewService;

	public ReviewController(ReviewService reviewService) {
		this.reviewService = reviewService;
	}

	@GetMapping
	public ResponseEntity<List<ReviewResponse>> getReviews(@PathVariable String shopId, HttpServletRequest request) {

		String loginUserId = getLoginUserId(request);

		List<ReviewResponse> reviews = reviewService.getReviews(shopId, loginUserId);

		return ResponseEntity.ok(reviews);
	}

	@DeleteMapping("/{reviewId}")
	public ResponseEntity<Void> deleteReview(@PathVariable String shopId, @PathVariable String reviewId,
			HttpServletRequest request) {

		String loginUserId = getLoginUserId(request);

		reviewService.deleteReview(shopId, reviewId, loginUserId);

		return ResponseEntity.noContent().build();
	}

	private String getLoginUserId(HttpServletRequest request) {

		HttpSession session = request.getSession(false);

		if (session == null) {
			return null;
		}

		return (String) session.getAttribute("LOGIN_USER_ID");
	}
}