package com.gf.controller;


import com.gf.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloControler {

    @Autowired
    private HelloService helloService;

    @GetMapping(value = "/hi")
    public String hi() {
        return helloService.hiService();
    }

}
