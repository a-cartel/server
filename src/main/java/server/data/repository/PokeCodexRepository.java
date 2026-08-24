package server.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.data.entity.PokeCodex;

@Repository
public interface PokeCodexRepository extends JpaRepository<PokeCodex, Integer> {
    // 기본적인 CRUD는 JpaRepository가 알아서 제공합니다.
}