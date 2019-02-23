package com.gf.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.Instant;

/**
 * 统计某个或者某种路由的处理时长
 */
public class CustomerGatewayFilter implements GatewayFilter, Ordered {

    private static final Logger log = LoggerFactory.getLogger( CustomerGatewayFilter.class );
    private static final String COUNT_START_TIME = "countStartTime";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        exchange.getAttributes().put(COUNT_START_TIME, Instant.now().toEpochMilli() );
        return chain.filter(exchange).then(
                Mono.fromRunnable(() -> {
                    long startTime = exchange.getAttribute(COUNT_START_TIME);
                    long endTime=(Instant.now().toEpochMilli() - startTime);
                    log.info(exchange.getRequest().getURI().getRawPath() + ": " + endTime + "ms");
                })
        );
    }

    @Override
    public int getOrder() {
        return 0;
    }
}

