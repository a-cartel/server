package com.example.server.scheduler.infos;

import com.example.server.scheduler.repository.NewsRepository;
import com.example.server.scheduler.entity.NewsEntity;
// import com.example.server.scheduler.dto.NewsDTO;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class News {
    private final NewsRepository newsRepository;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    // 롬복써서 final 필드 기준 생성자 자동 생성
    // public News(RestTemplate restTemplate, NewsRepository newsRepository) {
    //     this.restTemplate = restTemplate;
    //     this.newsRepository = newsRepository;
    // }

    public void fetchNews() {

        // perPage 수 찾기
        String url = "https://www.pokemon.co.jp/api/info/index/?limit=20&page=1";
        try {
            ResponseEntity<String> res = restTemplate.getForEntity(url, String.class);
            JsonNode paging = objectMapper.readTree(res.getBody()).get("paging");
            JsonNode n = paging.get("perPage");
            // int total = n.asInt();
            
        } catch (Exception e) {
            System.out.println("perPage 조회 에러: " + e.getMessage());
        }

        // for (int page = 1; page >= 1; page--) 
        int testPage = 87;

            for (int page = testPage; page >= testPage; page--){
            // String base = "https://www.pokemon.co.jp/api/info/index/?limit=20&page=" + page;
            String base = "https://www.pokemon.co.jp/api/info/index/?limit=20&page=" + "84";

            try {
                ResponseEntity<String> pageRes = restTemplate.getForEntity(base, String.class);
                JsonNode root = objectMapper.readTree(pageRes.getBody());
                JsonNode results = root.get("results");

                if (results != null && results.isArray()) {
                    for (JsonNode item : results) {
                        // System.out.println(item);
                        // System.out.println(item.get("id"));

                        saveOne(item);
                        System.out.println(item);
                        System.out.println(item.get("img_1"));
                        System.out.println("");
                        System.out.println("");
                        System.out.println("");
                        
                    }
                    // System.out.println("page " + page + " 저장 완료, " + results.size() + "건");
                }

                Thread.sleep(5000);

            } catch (Exception e) {
                System.out.println("page " + page + " 에러: " + e.getMessage());
            }
        }
    }

    @Transactional
    public void saveOne(JsonNode item) {
        Integer newsId = item.get("id").asInt();

        if (newsRepository.existsByNewsId(newsId)) {
            System.out.println(newsId + " 이미 존재, 스킵");
            return;
        }

        NewsEntity entity = new NewsEntity();
        entity.setNewsId(newsId);
        entity.setNewsUrl(item.get("full_uniq").asString());
        entity.setNewsTitle(item.get("title").asString());
        entity.setNewsType(item.get("term").asString());
        entity.setNewsImg(item.get("img_1").asString());

        newsRepository.save(entity);
    }
}