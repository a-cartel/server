package server.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.data.entity.ShopEntity;

public interface ShopRepository extends JpaRepository<ShopEntity, Long> {
}
