package com.lhr13.newyorkcab.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("web")
public class HelloWord {

    @RequestMapping("/hello")
    public String hello() {
        return "helloworld";
    }

}
