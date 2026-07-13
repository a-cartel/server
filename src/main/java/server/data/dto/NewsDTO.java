package server.data.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NewsDTO {
    private Integer newsId;     // 뉴스 아이디
    private String newsTitle;   // 뉴스 제목
    private String newsImg;     // 이미지
    private String newsType;    // 뉴스 타입
    private String newsUrl;     // 리다이렉트용 url
    private LocalDate newsDate; // 뉴스 올라온 날짜
}