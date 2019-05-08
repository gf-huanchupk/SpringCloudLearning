package com.gf.controller;

import com.gf.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {

    @Autowired
    private HelloService helloService;


    @GetMapping("/hi")
    public String hi() {
        return helloService.hiService();
    }

    @GetMapping("/hey")
    public String hey() {
        return helloService.heyService();
    }

    @GetMapping("/oh")
    public String oh() {
        return helloService.ohService();
    }

    @GetMapping("/ah")
    public String ah() {
        return helloService.ahService();
    }



}
