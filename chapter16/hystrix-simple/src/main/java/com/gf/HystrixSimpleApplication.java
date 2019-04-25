package com.gf;

import com.gf.service.HelloService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class HystrixSimpleApplication {

    @Autowired
    private HelloService helloService;

    public static void main(String[] args) {
        SpringApplication.run( HystrixSimpleApplication.class, args );
    }

    @GetMapping("/hi")
    public String hi(String name) {
        return helloService.sayHello( name );
    }


}
