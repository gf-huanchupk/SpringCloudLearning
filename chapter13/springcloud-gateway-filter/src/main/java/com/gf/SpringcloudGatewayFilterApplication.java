package com.gf;

import com.gf.config.CustomerGatewayFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.net.URI;

@SpringBootApplication
public class SpringcloudGatewayFilterApplication {

    public static void main(String[] args) {
        SpringApplication.run( SpringcloudGatewayFilterApplication.class, args );
    }

    //@Bean
    public RouteLocator customerRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/customer/**")
                        .filters(f -> f.filter(new CustomerGatewayFilter())
                                .addResponseHeader("X-Response-test", "test"))
                        .uri("http://httpbin.org:80/get")
                        .id("customer_filter_router")
                )
                .build();
    }

}
