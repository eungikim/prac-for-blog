package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;


@DataJpaTest // JPA 와 연동하는 테스트
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;

    // 4번 게시글의 모든 댓글을 조회
    @Test
    void findByArticleId() {
        // given
        Long articleId = 4L;
        Article article = new Article(4L, "당신의 인생 영화는?", "댓글 ㄱ");
        Comment comment1 = new Comment(1L,  article, "Park", "굳 윌 헌팅");
        Comment comment2 = new Comment(2L,  article, "Kim", "아이 엠 샘");
        Comment comment3 = new Comment(3L,  article, "Choi", "쇼생크의 탈출");
        List<Comment> expectedComments = Arrays.asList(comment1, comment2, comment3);

        // when
        List<Comment> comments = commentRepository.findByArticleId(articleId);
        boolean a = comments.containsAll(expectedComments);
        System.out.println(a);
        // then
        org.junit.jupiter.api.Assertions.assertTrue(
                comments.size() == expectedComments.size()
                        && comments.containsAll(expectedComments)
                        && expectedComments.containsAll(comments));

//        Assertions.assertThat(comments).isEqualTo(expectedComments);

    }

    @Test
    void findByNickname() {
        // given
        String nickname = "Park";
        Article a4 = new Article(4L, "당신의 인생 영화는?", "댓글 ㄱ");
        Article a5 = new Article(5L, "당신의 소울 푸드는?", "댓글 ㄱㄱ");
        Article a6 = new Article(6L, "당신의 취미는?", "댓글 ㄱㄱㄱ");
        Comment comment1 = new Comment(1L,  a4, nickname, "굳 윌 헌팅");
        Comment comment2 = new Comment(4L,  a5, nickname, "치킨");
        Comment comment3 = new Comment(7L,  a6, nickname, "조깅");
        List<Comment> expectedComments = Arrays.asList(comment1, comment2, comment3);

        // when
        List<Comment> comments = commentRepository.findByNickname(nickname);

        // then
        Assertions.assertThat(comments).isEqualTo(expectedComments);
    }
}