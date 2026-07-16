package server.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import server.data.entity.NewsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NewsRepository extends JpaRepository<NewsEntity, Long> {
    // boolean existsByNewsId(Integer newsId);

    // Page<NewsEntity> findAllByOrderByNewsDateDesc(Pageable pageable);
    List<NewsEntity> findAllByOrderByNewsDateDesc();
}

