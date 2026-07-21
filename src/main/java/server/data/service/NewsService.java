package server.data.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import server.data.dto.NewsDTO;
import server.data.entity.NewsEntity;
import server.data.repository.NewsRepository;

import java.util.List;

//컨트롤러와 리포지토리 사이에서 데이터 처리를 담당
@Service
@RequiredArgsConstructor
public class NewsService {

    private final NewsRepository newsRepository;

    // NewsService의 getNewsList(): "실제 데이터를 처리하는 비즈니스 로직"
    // Pageable을 매개변수로 받아 DB에서 해당 페이지 분량만큼만 조회
    public Page<NewsDTO> getNewsList(int page, int size, String category) {
    // newsDate 기준으로 최신순 정렬
        Pageable pageable = PageRequest.of(page, size, Sort.by("newsDate").descending());

        Page<NewsEntity> newsEntities;

        if ("all".equalsIgnoreCase(category)) {
            newsEntities = newsRepository.findAll(pageable);
        } else {
            // 카테고리(newsType)로 필터링
            newsEntities = newsRepository.findByNewsType(category, pageable);
        }

        // NewsDTO 생성자를 그대로 활용해서 매핑!
        return newsEntities.map(entity -> new NewsDTO(entity));
    }
}
