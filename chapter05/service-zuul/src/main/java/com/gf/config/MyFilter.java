package com.gf.config;


import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class MyFilter extends ZuulFilter{

    private static Logger logger = LoggerFactory.getLogger( MyFilter.class);

    /**
     * pre : 路由访问之时
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * filterOrder : 过滤的顺序
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * shouldFilter : 这里可以写逻辑判断，是否过滤
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * run : 过滤的具体逻辑判断，可以很复杂，包括sql，nosql去判断请求有没有权限访问
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        logger.info( String.format("%s >>> %s" , request.getMethod() , request.getRequestURL()) );
        Object accessToken = request.getParameter( "token" );
        if (null == accessToken) {
            logger.warn( "token is empty" );
            ctx.setSendZuulResponse( false );
            ctx.setResponseStatusCode( 401 );
            try {
                ctx.getResponse().getWriter().write( "token is empty" );
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;

        }
        logger.info( "ok" );
        return null;
    }

}
