package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ArticleServiceTest {

    @Autowired
    ArticleService service;

    @Test
    void index() {
        //given
        Article a1 = new Article(6L, "타이틀입니다.", "타이틀이 아닙니다.");
        Article a2 = new Article(10L, "제목입니다.제목입니다.제목입니다.제목입니다.제목입니다.제목입니다.", "제목입니다.제목입니다.제목입니다.제목입니다.제목입니다.제목입니다.제목입니다.제목입니다.제목입니다.제목입니다.제목입니다.제목입니다.제목입니다.제목입니다.");
        Article a3 = new Article(11L, "제목입니다.", "아만입니다.");
        List<Article> expected = new ArrayList<>(Arrays.asList(a1, a2, a3));

        //when
        List<Article> articles = service.index();

        //then
        assertThat(articles).containsAll(expected).hasSameSizeAs(expected);
    }

    @Test
    void show_성공() {
        // given
        Long id = 1L;
        Article expected = new Article(id, "123", "111");
        // when
        Article article = service.show(id);
        // then
        assertThat(expected).isEqualTo(article);
    }
    @Test
    void show_실패() {
        // given
        Long id = 2L;
        Article expected = new Article(id, "123", "111");
        // when
        Article article = service.show(id);
        // then
        assertThat(expected).isEqualTo(article);
    }

    @Test
    void create_성공() {
        String title = "welcome";
        String content = "my world";
        ArticleForm dto = new ArticleForm(title, content);
        Article expected = new Article(14L, title, content);

        Article article = service.create(dto);

        assertThat(expected).isEqualTo(article);
    }


    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}