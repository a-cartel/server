package com.example.server.scheduler;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// import com.example.server.scheduler.dto.newsDTO;
import com.example.server.scheduler.infos.News;

import java.util.List;

@RestController
public class test {
    private final News newsService;

    public test(News newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/test")
    public void test() {
        newsService.fetchNews();
        // return "1";
    }
}
