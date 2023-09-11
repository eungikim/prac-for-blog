package com.example.firstproject.api;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class CommentApiController {

    private CommentService commentService;

    @Autowired
    public CommentApiController(CommentService commentService) {
        this.commentService = commentService;
    }

    // 댓글목록조회
    @GetMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<List<CommentDto>> comments(@PathVariable Long articleId) {
        // 서비스에 위임
        List<CommentDto> dtos = commentService.comments(articleId);

        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    // 댓글생성
    @PostMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<CommentDto> create(@PathVariable Long articleId, @RequestBody CommentDto dto) {
        // 서비스에 위임
        CommentDto createdDto = commentService.create(articleId, dto);

        return ResponseEntity.status(HttpStatus.OK).body(createdDto);
    }

    // 댓글수정
    @PatchMapping("/api/comments/{id}")
    public ResponseEntity<CommentDto> update(@PathVariable Long id, @RequestBody CommentDto dto) {
        log.info("Controller create {}", dto);
        // 서비스에 위임
        CommentDto createdDto = commentService.update(id, dto);

        return ResponseEntity.status(HttpStatus.OK).body(createdDto);
    }

    @DeleteMapping("/api/comments/{id}")
    public ResponseEntity<CommentDto> update(@PathVariable Long id) {
        // 서비스에 위임
        CommentDto createdDto = commentService.delete(id);

        return ResponseEntity.status(HttpStatus.OK).body(createdDto);
    }

}
