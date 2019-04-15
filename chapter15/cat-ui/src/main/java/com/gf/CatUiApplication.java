package com.gf;

import com.gf.config.CatRestInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@SpringBootApplication
@RestController
public class CatUiApplication {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${service.business.address:localhost:8083}")
    private String businessAddress;

    private static final Logger log = LoggerFactory.getLogger(CatUiApplication.class);

    public static void main(String[] args) {
        SpringApplication.run( CatUiApplication.class, args );
    }

    @GetMapping("/start")
    public String start () throws InterruptedException {
        log.info( "start ..." );
        String response = restTemplate.getForObject("http://" + businessAddress + "/business-order", String.class);
        Thread.sleep(100);
        log.info("end ... result : {}", response);
        return response;

    }

    @Bean
    RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        // 保存和传递调用链上下文
        restTemplate.setInterceptors( Collections.singletonList(new CatRestInterceptor()));
        return restTemplate;
    }


}
