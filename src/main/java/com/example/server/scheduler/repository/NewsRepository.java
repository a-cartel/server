package com.example.server.scheduler.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.server.scheduler.entity.NewsEntity;

public interface NewsRepository extends JpaRepository<NewsEntity, Long> {
    boolean existsByNewsId(Integer newsId);
}
