package server.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

//DB의 뉴스 테이블 컬럼들을 Java 객체로 정의
@Entity
@Table(name = "POKE_NEWS")
@Getter
@NoArgsConstructor
public class NewsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer newsId;
    private String newsTitle;
    private String newsImg;
    private String newsType;
    private String newsUrl;
    private LocalDate newsDate;
}
