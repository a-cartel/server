package com.example.server.scheduler.infos;

import com.example.server.scheduler.repository.NewsRepository;
import com.example.server.scheduler.entity.NewsEntity;

import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

// import org.springframework.transaction.annotation.Transactional; 바로 커밋 방지 어노테이션

import org.springframework.web.client.RestTemplate;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class News {
    private final NewsRepository newsRepository;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void newsScheduler() {
        int page = 1;

        while (true) {
            try {
                String url = "https://www.pokemon.co.jp/api/info/index/?limit=100&page=" + page;
                ResponseEntity<String> res = restTemplate.getForEntity(url, String.class);
                JsonNode root = objectMapper.readTree(res.getBody());
                JsonNode results = root.get("results");

                if (results == null || !results.isArray() || results.size() == 0) {
                    break;
                }

                int cnt = 0;

                for (JsonNode item : results) {
                    Integer newsId = item.get("id").asInt();
                    if (newsRepository.existsByNewsId(newsId)) {
                        cnt++;
                    } else {
                        sendDB(item);
                    }
                }
                // 업데이트 할께 없다면 while 종료
                if (cnt == results.size()) {
                    break;
                }

                page++;
                Thread.sleep(3000);

            } catch (Exception e) {
                System.out.println("infos/News.java newsScheduler error : " + e);
                break;
            }
        }
    }

    // @Transactional
    public void sendDB(JsonNode item) {
        try {
            Integer newsId = item.get("id").asInt();

            if (newsRepository.existsByNewsId(newsId)) {
                // 중복된 값 있으면 스킵
                // System.out.println("중복 값으로 인한 스킵 : " + newsId);
                return;
            }

            NewsEntity entity = new NewsEntity();
            entity.setNewsId(newsId);
            entity.setNewsUrl(item.get("full_uniq").asString());
            entity.setNewsTitle(item.get("title").asString());
            entity.setNewsType(item.get("term").asString());
            entity.setNewsImg(item.get("img_1").asString());

            String dateStr = item.get("start_date").asString();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
            LocalDate newsDate = LocalDate.parse(dateStr, formatter);
            entity.setNewsDate(newsDate);

            newsRepository.save(entity);
        } catch (Exception e) {
            System.out.println("infos/News.java sendDB error : " + e);
        }
    }
}