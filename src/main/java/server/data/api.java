package server.data;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RestController;
import server.data.repository.NewsRepository;
import server.data.dto.NewsListResponse;
import server.data.dto.NewsDTO;
import server.data.dto.NewsPaging;

import server.data.entity.NewsEntity;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController
public class api {
    private final NewsRepository newsRepository;

    public api(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    // @GetMapping("/news")
    // public NewsListResponse getNewsList(
    //         @RequestParam(defaultValue = "1") int page,
    //         @RequestParam(defaultValue = "20") int limit) {
    //     if (page < 1)
    //         page = 1;
    //     if (limit < 1 || limit > 100)
    //         limit = 20;

    //     Pageable pageable = PageRequest.of(page - 1, limit);
    //     Page<NewsEntity> newsPage = newsRepository.findAllByOrderByNewsDateDesc(pageable);

    //     List<NewsDTO> results = newsPage.getContent()
    //             .stream()
    //             .map(entity -> new NewsDTO(
    //                     entity.getNewsId(),
    //                     entity.getNewsTitle(),
    //                     entity.getNewsImg(),
    //                     entity.getNewsType(),
    //                     entity.getNewsUrl(),
    //                     entity.getNewsDate()))
    //             .toList();
    //     NewsPaging paging = new NewsPaging(page, newsPage.getTotalElements(), limit);

    //     // System.out.println("results : " + results);
    //     return new NewsListResponse(results, paging);

    @GetMapping("/news")
    public List<NewsEntity> getNewsList() {

        List<NewsEntity> newsList = newsRepository.findAllByOrderByNewsDateDesc();

        return newsList;
    }
}
