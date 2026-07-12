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

    // public void testnews(){
    // try {
    // int n = 999;
    // String url = "https://www.pokemon.co.jp/api/info/index/?limit=20&page=" + n;
    // ResponseEntity<String> res = restTemplate.getForEntity(url, String.class);
    // // JsonNode root = objectMapper.readTree(res.getBody());
    // // JsonNode results = root.get("results");
    // // System.out.println(results);

    // System.out.println(res);
    // } catch (Exception e){
    // System.out.println("***에러*** : " + e);
    // }
    // }

    public void newsScheduler() {
        int page = 1;
        boolean flag = true;
        while (flag) {
            int n = 0;
            flag = false;
            try {
                String url = "https://www.pokemon.co.jp/api/info/index/?limit=100&page=" + page;
                ResponseEntity<String> res = restTemplate.getForEntity(url, String.class);
                JsonNode root = objectMapper.readTree(res.getBody());
                JsonNode results = root.get("results");

                // n = results.size();
                // System.out.println("n : "+ n);
                if (results == null || !results.isArray() || results.size() == 0) {
                    break;
                }
                
                for (JsonNode item : results) {
                    Integer newsId = item.get("id").asInt();
                    if (newsRepository.existsByNewsId(newsId)){
                        n++;
                    }

                    
                    // System.out.println("test : "+!newsRepository.existsByNewsId(newsId));
                    
                    // if (!newsRepository.existsByNewsId(newsId)) {
                    //     sendDB(item);
                    //     flag = true;
                    // }
                }
                System.out.println("n : " + n);
                System.out.println("page " + page + " 확인 완료");

                page++;
                Thread.sleep(100000);

            } catch (Exception e) {
                System.out.println("page " + page + " 에러: " + e);
                break;
            }
        }
    }

    // @Transactional
    public void sendDB(JsonNode item) {
        int n = 0;
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

        String dateStr = item.get("start_date").asString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        LocalDate newsDate = LocalDate.parse(dateStr, formatter);
        entity.setNewsDate(newsDate);

        newsRepository.save(entity);
    }
}