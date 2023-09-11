package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class ArticleService {

    private ArticleRepository articleRepository;

    public ArticleService(ArticleRepository repository) {
        this.articleRepository = repository;
    }

    public List<Article> index() {
        return StreamSupport.stream(articleRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {
        Article article = dto.toEntity();
        // 아이디값이 있으면 post 요청 안함
        if (article.getId() != null) return null;
        return articleRepository.save(article);
    }

    public Article update(Long id, ArticleForm dto) {
        Article foundArticle = articleRepository.findById(id).orElse(null);
        Article newArticle = dto.toEntity();

        if(foundArticle == null) {
            log.info("잘못된 요청");
            return null;
        }

        foundArticle.patch(newArticle);
        return articleRepository.save(foundArticle);
    }

    public boolean delete(Long id) {
        Article foundArticle = articleRepository.findById(id).orElse(null);

        if(foundArticle == null) {
            log.info("잘못된 요청");
            return false;
        }
        articleRepository.delete(foundArticle);
        return true;
    }

//    @Transactional
    public List<Article> createArticles(List<ArticleForm> dtos) {
        List<Article> articles = dtos.stream()
                .map(ArticleForm::toEntity)
                .collect(Collectors.toList());

        articles.stream().map(article -> articleRepository.save(article));

        // TODO: 강제 예외 발생 상황!
        articleRepository.findById(-1L).orElseThrow(() -> {
            return new IllegalArgumentException("강제 실패!!");
        });

        return articles;
    }
}
