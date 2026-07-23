package server.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.data.entity.GoodsEntity;

public interface GoodsRepository extends JpaRepository<GoodsEntity, Long> {
}
