package com.gf.config;


import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * Token 校验全局过滤器
 */
@Component
public class AuthorizeFilter implements GlobalFilter, Ordered {

    private static final Logger log = LoggerFactory.getLogger( AuthorizeFilter.class );

    private static final String AUTHORIZE_TOKEN = "token";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = exchange.getRequest().getQueryParams().getFirst( AUTHORIZE_TOKEN );
        if ( StringUtils.isBlank( token )) {
            log.info( "token is empty ..." );
            exchange.getResponse().setStatusCode( HttpStatus.UNAUTHORIZED );
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }

}
