package com.gf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class CatStorageServiceApplication {

    private static final Logger log = LoggerFactory.getLogger(CatStorageServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run( CatStorageServiceApplication.class, args );
    }

    @GetMapping("/storage/reduce")
    public String create() throws InterruptedException {

        log.info("start reduce storage ...");
        Thread.sleep(2000);
        log.info("end reduce storage ...");
        return "reduce storage success";
    }

}
