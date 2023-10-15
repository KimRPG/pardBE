package com.pard.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PracController {
    @PostMapping("/hi/{id}")
    public String hi(@PathVariable int id) {
        return "hi" + id;
    }
    @PostMapping("/hi")
    public String hello(@RequestParam int id) {
        return "hi" + id;
    }
}
