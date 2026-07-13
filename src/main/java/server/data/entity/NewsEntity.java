package server.data.entity;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "POKE_NEWS")
public class NewsEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer newsId;
    private String newsTitle;
    private String newsImg;
    private String newsType;
    private String newsUrl;
    private LocalDate newsDate;

    // @Column
    // private Integer newsId;

    // @Column
    // private String newsTitle;

    // @Column
    // private String newsImg;

    // @Column
    // private String newsType;

    // @Column
    // private String newsUrl;

    // @Column
    // private LocalDate newsDate;
}
