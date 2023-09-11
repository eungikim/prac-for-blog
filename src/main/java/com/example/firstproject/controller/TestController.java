package com.example.firstproject.controller;

import com.example.firstproject.entity.Comment;
import com.example.firstproject.repository.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
public class TestController {

    private CommentRepository commentRepository;

    @Autowired
    public TestController(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @GetMapping("/greeting")
    public String greeting(Model model) {
        model.addAttribute("username", "이순신");
        return "greetings";
    }
    @GetMapping("/bye")
    public String goodbye(Model model) {
        model.addAttribute("username", "이순신");
        return "goodbye";
    }

    // 닉네임 조회
    @GetMapping("/api/articles/comments")
    public String nicknameComments(@RequestParam String nickname, Model model) {
        List<Comment> comments = commentRepository.findByNickname(nickname);
        log.info("{} by {}", comments, nickname);
        model.addAttribute("comments", comments);
        return "comments/_nickname";
    }
}
