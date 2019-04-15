package com.gf.config;


import com.dianping.cat.Cat;
import com.dianping.cat.CatConstants;
import com.dianping.cat.message.Message;
import com.dianping.cat.message.Transaction;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * http协议传输，远程调用链目标端接收context的filter，
 * 通过header接收rootId、parentId、childId并放入CatContextImpl中，调用Cat.logRemoteCallServer()进行调用链关联
 * 注:若不涉及调用链，则直接使用cat-client.jar中提供的filter即可
 * 使用方法（视项目框架而定）：
 *      1、web项目：在web.xml中引用此filter
 *      2、Springboot项目，通过注入bean的方式注入此filter
 */
public class CatServletFilter implements Filter {


    private String[] urlPatterns = new String[0];

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String patterns = filterConfig.getInitParameter("CatHttpModuleUrlPatterns");
        if (patterns != null) {
            patterns = patterns.trim();
            urlPatterns = patterns.split(",");
            for (int i = 0; i < urlPatterns.length; i++) {
                urlPatterns[i] = urlPatterns[i].trim();
            }
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        String url = request.getRequestURL().toString();
        for (String urlPattern : urlPatterns) {
            if (url.startsWith(urlPattern)) {
                url = urlPattern;
            }
        }

        CatContextImpl catContext = new CatContextImpl();
        catContext.addProperty( Cat.Context.ROOT, request.getHeader(CatHttpConstants.CAT_HTTP_HEADER_ROOT_MESSAGE_ID));
        catContext.addProperty(Cat.Context.PARENT, request.getHeader(CatHttpConstants.CAT_HTTP_HEADER_PARENT_MESSAGE_ID));
        catContext.addProperty(Cat.Context.CHILD, request.getHeader(CatHttpConstants.CAT_HTTP_HEADER_CHILD_MESSAGE_ID));
        Cat.logRemoteCallServer(catContext);

        Transaction t = Cat.newTransaction( CatConstants.TYPE_URL, url);

        try {

            Cat.logEvent("Service.method", request.getMethod(), Message.SUCCESS, request.getRequestURL().toString());
            Cat.logEvent("Service.client", request.getRemoteHost());

            filterChain.doFilter(servletRequest, servletResponse);

            t.setStatus(Transaction.SUCCESS);
        } catch (Exception ex) {
            t.setStatus(ex);
            Cat.logError(ex);
            throw ex;
        } finally {
            t.complete();
        }
    }

    @Override
    public void destroy() {

    }
}
