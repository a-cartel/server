package server.review.service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import server.auth.entity.userEntity;
import server.auth.repository.userRepository;
import server.data.repository.ShopRepository;
import server.exception.CustomException;
import server.review.dto.ReviewDTO;
import server.review.entity.ReviewEntity;
import server.review.repository.ReviewRepository;

@Service
@RequiredArgsConstructor
public class ReviewService {

	private final ReviewRepository reviewRepository;
	private final ShopRepository shopRepository;
	private final userRepository userRepository;

	/**
	 * 리뷰 목록 조회. ReviewEntity에는 작성자 이름이 없어서(USER_ID로 POKE_USER를 FK 참조하는 구조),
	 * 리뷰들에 등장하는 userId를 모아서 한 번에 조회(N+1 방지)한 뒤 이름을 붙여준다.
	 */
	public List<ReviewDTO> getReviewsByShop(Long shopId) {

		List<ReviewEntity> reviews = reviewRepository.findByShopIdOrderByCreatedAtDesc(shopId);

		Set<String> userIds = reviews.stream()
				.map(ReviewEntity::getUserId)
				.collect(Collectors.toSet());

		Map<String, String> nameByUserId = userRepository.findAllById(userIds).stream()
				.collect(Collectors.toMap(userEntity::getId, userEntity::getName));

		return reviews.stream()
				.map(review -> ReviewDTO.from(review, nameByUserId.get(review.getUserId())))
				.toList();
	}

	@Transactional
	public ReviewDTO createReview(Long shopId, String userId, String writerName, ReviewDTO request) {

		// FK_REVIEW_SHOP 제약조건이 DB에 있긴 하지만, 그냥 두면 못생긴 SQL 예외(500)가 나가서 미리 확인한다.
		if (!shopRepository.existsById(shopId)) {
			throw new CustomException.NotFoundException("存在しない店舗にレビューを作成することはできません。");
		}

		ReviewEntity review = new ReviewEntity(shopId, userId, request.getRating(), request.getContent());

		reviewRepository.save(review);

		return ReviewDTO.from(review, writerName);
	}

	@Transactional
	public ReviewDTO updateReview(Long reviewId, String userId, String writerName, ReviewDTO request) {

		ReviewEntity review = reviewRepository.findById(reviewId)
				.orElseThrow(() -> new CustomException.NotFoundException("レビューが見つかりません。"));

		if (!review.getUserId().equals(userId)) {
			throw new CustomException.UnauthorizedException("本人が作成したレビューのみ修正できます。");
		}

		review.update(request.getRating(), request.getContent());

		reviewRepository.save(review);

		return ReviewDTO.from(review, writerName);
	}

	@Transactional
	public void deleteReview(Long reviewId, String userId) {

		ReviewEntity review = reviewRepository.findById(reviewId)
				.orElseThrow(() -> new CustomException.NotFoundException("レビューが見つかりません。"));

		if (!review.getUserId().equals(userId)) {
			throw new CustomException.UnauthorizedException("本人が作成したレビューのみ削除できます。");
		}

		reviewRepository.delete(review);
	}
}
