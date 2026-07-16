package server.review.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import server.review.entity.ReviewEntity;

public interface ReviewRepository extends JpaRepository<ReviewEntity, String> {

	List<ReviewEntity> findByShopIdOrderByCreatedAtDesc(String shopId);

	Optional<ReviewEntity> findByReviewIdAndShopId(String reviewId, String shopId);
}