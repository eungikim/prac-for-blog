package com.example.firstproject.dto;

import com.example.firstproject.entity.Comment;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@ToString
public class CommentDto {
    private Long id;

//    @JsonProperty("article_id")
    private Long articleId;
    private String nickname;
    private String body;

    public CommentDto(Comment c) {
        this.id = c.getId();
        this.articleId = c.getArticle().getId();
        this.nickname = c.getNickname();
        this.body = c.getBody();
    }
}
