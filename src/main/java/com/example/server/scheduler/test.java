package com.example.server.scheduler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    // @GetMapping("/test")
    // public String test() {
    //     return "1";
    // }

    @Scheduled(fixedRate = 600000)
    public void onScheduler(){
        try {
            DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            System.out.println("onScheduler 시작 : " + LocalDateTime.now().format(f));
            newsService.newsScheduler();
            System.out.println("onScheduler 완료 : " + LocalDateTime.now().format(f));
        } catch (Exception e) {
            System.out.println("onScheduler error : " + e);
        }
    }
}
