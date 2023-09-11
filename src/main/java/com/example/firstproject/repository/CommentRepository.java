package com.example.firstproject.repository;

import com.example.firstproject.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository  extends JpaRepository<Comment, Long> {

    // 특정 게시글의 모든 댓글조회
    @Query(value = "SELECT * FROM comment WHERE article_id=:articleId", nativeQuery = true)
    List<Comment> findByArticleId(@Param("articleId") Long articleId);

    // @Query(...) 생략해도 메서드 이름가지고 만들어줌
    List<Comment> findByNickname(@Param("nickname") String nickname);
}
