package server.data.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.data.entity.NewsEntity;
import server.data.service.NewsService;
import server.data.dto.NewsDTO;

import java.util.List;

// 프런트엔드에서 axios(/news)요청이 왔을때 안내되는 곳
@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    @GetMapping
    // NewsController의 getNewsList(): "HTTP 요청을 받는 창구(API 엔드포인트)"
    public ResponseEntity<Page<NewsDTO>> getNewsList(
            // 페이징 정보가 포함된 뉴스 상자 (뉴스 데이터 목록 + 전체 페이지 수 + 현재 페이지 번호 등)
            // 기본값: 0번째 페이지부터 시작, 한 페이지당 10개씩, newsDate 기준 최신순(내림차순) 정렬
            @PageableDefault(page = 0, size = 10, sort = "newsDate", direction = Sort.Direction.DESC) Pageable pageable,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "9") int size,
            @RequestParam(defaultValue = "ALL") String category)
        {
        // NewsService의 getNewsList() 메서드를 가져다 씀
        // 조건 정보가 담긴 pageable 객체를 Service로 전달하고 그 결과를 newsPage 변수에 담음
        Page<NewsDTO> newsPage = newsService.getNewsList(page, size, category);

        // 프런트엔드에게 "성공(200 OK)" 상태와 함께 뉴스 목록을 보내기
            return ResponseEntity.ok(newsService.getNewsList(page, size, category));
    }
}
