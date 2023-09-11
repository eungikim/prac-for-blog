package com.example.firstproject.api;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class ArticleApiController {

    private ArticleService articleService;

    @Autowired
    public ArticleApiController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/api/articles")
    public List<Article> index() {
//        return StreamSupport.stream(articleRepository.findAll().spliterator(), false)
//                .collect(Collectors.toList());
        return articleService.index();
    }


    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id) {
//        return articleRepository.findById(id).orElse(null);
        return articleService.show(id);
    }

    @PostMapping("/api/articles")
    public ResponseEntity create(ArticleForm dto) {
        Article created = articleService.create(dto);
        if (created == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(created);
        }
    }

    @PatchMapping("/api/articles/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody ArticleForm dto) {
        Article update = articleService.update(id, dto);
        if (update == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(update);
        }
    }


    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        boolean deleted = articleService.delete(id);
        if (!deleted) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
    }

    @PostMapping("/api/transaction")
    public ResponseEntity transactionTest(@RequestBody List<ArticleForm> dtos) {
        List<Article> createdList = articleService.createArticles(dtos);

        if (createdList == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(createdList);
        }
    }
}
