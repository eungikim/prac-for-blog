package com.example.firstproject.service;

import com.example.firstproject.annotation.RunningTime;
import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.repository.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CommentService {

    private CommentRepository commentRepository;
    private ArticleRepository articleRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, ArticleRepository articleRepository) {
        this.commentRepository = commentRepository;
        this.articleRepository = articleRepository;
    }

    // 서비스가 작으면 Dto 가 아니라 entity 를 그냥 날릴 수 있다
    public List<CommentDto> comments(Long articleId) {
        List<Comment> comments = commentRepository.findByArticleId(articleId);

        List<CommentDto> dtos = new ArrayList<>();
//        for (int i = 0; i < comments.size(); i++) {
//            Comment c = comments.get(i);
//            CommentDto cDto = new CommentDto(c);
//            dtos.add(cDto);
//        }
        dtos = comments.stream()
                .map(CommentDto::new)
                .collect(Collectors.toList());
        return dtos;
    }

    @Transactional
    public CommentDto create(Long articleId, CommentDto dto) {
//        log.info("입력값 = <> {}", articleId);
//        log.info("입력값 = <> {}", dto);
        Article article = articleRepository.findById(articleId).orElse(null);
        Comment c = new Comment(dto, article);
        Comment savedC = commentRepository.save(c);

//        log.info("comment entity = {}", savedC);
        return new CommentDto(savedC);
    }

    @Transactional
    public CommentDto update(Long id, CommentDto dto) {
        Comment c = commentRepository.findById(id).orElse(null);
        c.patch(dto);

//        Comment updatedC = commentRepository.save(c);
        // @Transactional 어노테이션 적용시 엔티티가 바뀔 경우 영속성 컨텍스트가 알아서 저장해준다.
        return new CommentDto(c);
    }

    @RunningTime
    public CommentDto delete(Long id) {
        // 메서드 수행전
//        StopWatch stopWatch = new StopWatch();
//        stopWatch.start();

        Comment c = commentRepository.findById(id).orElse(null);
        commentRepository.delete(c);

        // 종료 후
//        stopWatch.stop();
//        log.info("delete 메서드 총 수행시간 {}", stopWatch.getTotalTimeMillis());
        return new CommentDto(c);
    }
}
