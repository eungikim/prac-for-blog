package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
public class ArticleController {

    private final ArticleRepository articleRepository;
    private final CommentService commentService;

    @Autowired
    public ArticleController(ArticleRepository articleRepository, CommentService commentService) {
        this.articleRepository = articleRepository;
        this.commentService = commentService;
    }


    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(
            ArticleForm articleForm) {
        log.info("articleForm: {}", articleForm);


        // 1. Dto -> Entity
        Article article = articleForm.toEntity();
        // Entity 를 DB 에 저장
        Article savedArticle = articleRepository.save(article);
        log.info("savedArticle: {}", savedArticle);

        return "redirect:/articles/" + savedArticle.getId();
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model) {
        log.info("path id: {}", id);
        Article foundArticle = articleRepository.findById(id).orElseThrow(() ->{
            return new IllegalArgumentException("없는 ID: " + id);
        });
        // 댓글도 동시에 가져오기
        List<CommentDto> commentDtos = commentService.comments(id);

        // 가져온 데이터 모델에 적재
        model.addAttribute("article", foundArticle);
        model.addAttribute("commentsDtos", commentDtos);
        return "/articles/show";
    }

//    @GetMapping("/articles")
//    public String index(Model model) {
//        List<Article> articleList = new ArrayList<>();
//        articleRepository.findAll().forEach((a) -> {
//            articleList.add(a);
//        });
//        model.addAttribute("articleList", articleList);
//        return "articles/index";
//    }

    @GetMapping("/articles")
    public String index(
            @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
            @RequestParam(value = "perPageNum", defaultValue = "5") int perPageNum,
            Model model) {


        int articleCnt = (int) articleRepository.count();
        int totalPages = (int) Math.ceil((double) articleCnt/perPageNum);

        int startArticleIndex = (pageNum - 1) * perPageNum;
        if (startArticleIndex < 0) startArticleIndex = 0;

        int endArticleIndex = (pageNum) * perPageNum; // exclude
        if (endArticleIndex > articleCnt) endArticleIndex = articleCnt;

        int[] pageNums = new int[totalPages];
        for (int i = 0; i < totalPages; i++) {
            pageNums[i] = (i + 1);
        }

        int previous = pageNum - 1;
        int next = pageNum + 1;

        // For visualizing Pre and Next button.
        boolean preStatus = previous >= 1;
        boolean nextStatus = next <= totalPages;

        Sort descendingSort = Sort.by(Sort.Direction.DESC, "id");
        List<Article> articleListForShow = articleRepository
                .findAll(descendingSort)
                .subList(startArticleIndex, endArticleIndex);

        model.addAttribute("articleList", articleListForShow);
        model.addAttribute("pageNums", pageNums);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("startArticleIndex", startArticleIndex);
        model.addAttribute("endArticleIndex", endArticleIndex);
        model.addAttribute("preStatus", preStatus);
        model.addAttribute("nextStatus", nextStatus);
        model.addAttribute("pageNum", pageNum);
        model.addAttribute("previous", previous);
        model.addAttribute("next", next);

        return "articles/index";
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        log.info("path id: {}", id);
        Article foundArticle = articleRepository.findById(id).orElseThrow(() ->{
            return new IllegalArgumentException("없는 ID: " + id);
        });
        model.addAttribute("article", foundArticle);
        return "articles/edit";
    }

    @PostMapping("/articles/update")
    public String update(@RequestParam String id, @ModelAttribute ArticleForm form, Model model) {
        log.info("path id: {}, form: {}", id, form);
        Article foundArticle = articleRepository.findById(Long.parseLong(id)).orElse(null);
        if (foundArticle != null){
            foundArticle.setTitle(form.getTitle());
            foundArticle.setContent(form.getContent());
            articleRepository.save(foundArticle);
        }
        return "redirect:/articles/" + foundArticle.getId();
    }

    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes reAttr) {
        log.info("delete id = " + id);
        Article foundArticle = articleRepository.findById(id).orElse(null);
        if (foundArticle != null) {
            articleRepository.delete(foundArticle);
            reAttr.addFlashAttribute("msg", "삭제 OK");
        }
        return "redirect:/articles";
    }


}
