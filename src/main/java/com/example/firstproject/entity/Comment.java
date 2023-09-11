package com.example.firstproject.entity;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.dto.CommentDto;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor @NoArgsConstructor
@ToString
@Getter @Setter
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne         // 해당 댓글 엔티티 여러개가, 하나의 Article에 연관된다!
    @JoinColumn(name = "article_id")
    private Article article;  // 댓글의 부모 게시글

    @Column
    private String nickname;

    @Column
    private String body;

    public Comment(CommentDto dto, Article article) {
        this.article = article;
        this.nickname = dto.getNickname();
        this.body = dto.getBody();
    }

    public void patch(CommentDto dto) {
        this.nickname = dto.getNickname();
        this.body = dto.getBody();
    }
}
