package com.example.aswspringbootjava.domain.post;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(length = 50, nullable = false)
    private String content;

    private String author;

    @Builder
    public Post(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
