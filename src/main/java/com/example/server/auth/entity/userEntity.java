package com.example.server.auth.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Poke_user")
public class userEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE) // oracle에서 auto_increment 지정
    private Long id;

    @Column(unique = true) // unique 제약조건을 추가
    private String email;

    @Column
    private String password;

    @Column
    private String name;

    @Column
    private String img;

    @Column
    private String create_at;
}