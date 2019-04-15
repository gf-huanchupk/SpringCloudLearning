package com.gf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class CatOrderServiceApplication {

    private static final Logger log = LoggerFactory.getLogger(CatOrderServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run( CatOrderServiceApplication.class, args );
    }

    @GetMapping("/order/create")
    public String create() throws InterruptedException {

        log.info("start order ...");
        Thread.sleep(2000);
        log.info("end order ...");
        return "order success";
    }

}
