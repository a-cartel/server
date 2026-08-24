package server.review.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import server.review.dto.ReviewDTO;
import server.review.service.ReviewService;
import server.util.SessionUtil;

// 프런트엔드에서 axios(/shop/{shopId}/reviews)로 요청이 왔을 때 안내되는 곳
// 조회는 누구나 가능(permitAll이었던 shop과 동일), 작성/수정/삭제는 로그인 필요.
// Spring Security가 없으므로, 로그인 체크는 SessionUtil.requireData()로 컨트롤러가 직접 한다.
@RestController
@RequestMapping("/shop/{shopId}/reviews")
@RequiredArgsConstructor
public class ReviewController {

	private final ReviewService reviewService;

	@GetMapping
	public ResponseEntity<List<ReviewDTO>> getReviews(@PathVariable Long shopId) {

		List<ReviewDTO> reviews = reviewService.getReviewsByShop(shopId);

		return ResponseEntity.ok(reviews);
	}

	@PostMapping
	public ResponseEntity<ReviewDTO> createReview(@PathVariable Long shopId,
			@Valid @RequestBody ReviewDTO request, HttpServletRequest httpRequest) {

		Map<String, Object> user = SessionUtil.requireData(httpRequest.getSession(false));
		String userId = (String) user.get("id");
		String writerName = (String) user.get("name");

		ReviewDTO review = reviewService.createReview(shopId, userId, writerName, request);

		return ResponseEntity.ok(review);
	}

	@PutMapping("/{reviewId}")
	public ResponseEntity<ReviewDTO> updateReview(@PathVariable Long shopId, @PathVariable Long reviewId,
			@Valid @RequestBody ReviewDTO request, HttpServletRequest httpRequest) {

		Map<String, Object> user = SessionUtil.requireData(httpRequest.getSession(false));
		String userId = (String) user.get("id");
		String writerName = (String) user.get("name");

		ReviewDTO review = reviewService.updateReview(reviewId, userId, writerName, request);

		return ResponseEntity.ok(review);
	}

	@DeleteMapping("/{reviewId}")
	public ResponseEntity<Map<String, Object>> deleteReview(@PathVariable Long shopId, @PathVariable Long reviewId,
			HttpServletRequest httpRequest) {

		String userId = (String) SessionUtil.requireData(httpRequest.getSession(false)).get("id");

		reviewService.deleteReview(reviewId, userId);

		return ResponseEntity.ok(Map.of("success", true, "message", "리뷰가 삭제되었습니다."));
	}
}
