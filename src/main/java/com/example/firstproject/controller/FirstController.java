package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/test")
public class FirstController {

    @GetMapping("/hi")
    public String greetings() {
        return "greetings";
    }
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
    @GetMapping("/hello2")
    @ResponseBody
    public String hello2(@RequestParam String msg) {
        return msg;
    }
    @GetMapping("/hello3/{msg}")
    @ResponseBody
    public String hello3(@PathVariable String msg) {
        return msg;
    }
    @GetMapping("/hello4/{msg}")
    public String hello4(@PathVariable String msg, Model model) {
        model.addAttribute("msg", msg);
        return "hello";
    }
}
