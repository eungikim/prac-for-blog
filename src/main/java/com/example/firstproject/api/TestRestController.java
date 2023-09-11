package com.example.firstproject.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/api")
public class TestRestController {

    @GetMapping("/hello")
    public String hello() {
        return "hello welcome";
    }
    @GetMapping("/hello1")
    public String hello1(@RequestParam(value = "msg", required = false) String msg) {
        return msg;
    }
    @GetMapping("/hello5")
    public HashMap<String ,String> hello5() {
        HashMap<String, String> map = new HashMap<>() {{
            put("이름", "김원빈");
            put("나이", "34");
            put("국적", "구로구");
        }};
        return map;
    }

}
