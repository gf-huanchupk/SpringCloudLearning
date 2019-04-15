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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@SpringBootApplication
@RestController
public class CatBusinessConsumerApplication {

    private static final Logger log = LoggerFactory.getLogger(CatBusinessConsumerApplication.class);

    @Autowired
    private RestTemplate restTemplate;

    @Value("${service.order.address:localhost:8084}")
    private String orderAddress;
    @Value("${service.storage.address:localhost:8085}")
    private String storageAddress;

    public static void main(String[] args) {
        SpringApplication.run( CatBusinessConsumerApplication.class, args );
    }

    @GetMapping("/business-order")
    public String businessOrder() throws InterruptedException {
        Thread.sleep(2000);
        log.info("business start...");
        String response1 = restTemplate.getForObject("http://" + orderAddress + "/order/create", String.class);
        String response2 = restTemplate.getForObject("http://" + storageAddress + "/storage/reduce", String.class);
        return String.format("business end...", response1, response2);
    }

    @Bean
    RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        // 保存和传递调用链上下文
        restTemplate.setInterceptors( Collections.singletonList(new CatRestInterceptor()));
        return restTemplate;
    }

}
