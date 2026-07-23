package server.data.dto;

import lombok.Getter;
import server.data.entity.NewsEntity;

import java.time.LocalDate;

@Getter
public class NewsDTO {
    //  private Long newsId; Id는 굳이 프런트엔드에 전해줄 필요가 없음
    private String newsTitle;
    private String newsImg;
    private String newsUrl;
    private String newsType;
    private LocalDate newsDate;

    public NewsDTO(NewsEntity entity) {
        this.newsTitle = entity.getNewsTitle();
        this.newsImg = entity.getNewsImg();
        this.newsUrl = entity.getNewsUrl();
        this.newsType = entity.getNewsType();
        this.newsDate = entity.getNewsDate();
    }
}