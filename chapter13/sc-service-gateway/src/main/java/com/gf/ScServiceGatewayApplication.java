package com.gf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ScServiceGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run( ScServiceGatewayApplication.class, args );
    }

}
