package com.example.firstproject.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@AllArgsConstructor @NoArgsConstructor
@ToString
@Getter @Setter
public class Article {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String title;
    @Column
    private String content;

    public void patch(Article update) {
        if (update.title != null) this.title = update.title;
        if (update.content != null) this.content = update.content;
    }
}
