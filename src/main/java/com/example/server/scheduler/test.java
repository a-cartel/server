package com.example.server.scheduler;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

// import com.example.server.scheduler.dto.newsDTO;
import com.example.server.scheduler.infos.News;

@RestController
public class test {
    private final News newsService;

    public test(News newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/test")
    public String test() {
        // newsService.testnews();
        // return "1";
        return "1";
    }

    @Scheduled(fixedRate = 600000)
    public void onScheduler(){
        newsService.newsScheduler();
    }
}
