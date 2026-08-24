package server.review.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import server.review.entity.ReviewEntity;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {

	List<ReviewEntity> findByShopIdOrderByCreatedAtDesc(Long shopId);
}
