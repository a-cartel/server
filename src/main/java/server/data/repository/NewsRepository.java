package server.data.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import server.data.entity.NewsEntity;

public interface NewsRepository extends JpaRepository<NewsEntity, Long> {

    // 카테고리가 'ALL'이 아니면 해당 카테고리만 페이징해서 조회
    Page<NewsEntity> findByNewsType(String newsType, Pageable pageable);
}
