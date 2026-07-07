package com.example.server.scheduler.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Poke_news")
public class NewsEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer newsId;

    @Column
    private String newsTitle;

    @Column
    private String newsImg;

    @Column
    private String newsUrl;

    @Column
    private String newsType;
}
