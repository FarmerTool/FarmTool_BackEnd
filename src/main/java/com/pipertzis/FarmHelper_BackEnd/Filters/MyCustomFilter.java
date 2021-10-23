package com.pipertzis.FarmHelper_BackEnd.Filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyCustomFilter implements Filter {
    private final static Logger logger = LoggerFactory.getLogger(MyCustomFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.info("Local Port " + request.getLocalPort());
        logger.info("Server Name " + request.getServerName());
        logger.info("Protocol " + request.getProtocol());

        HttpServletRequest http = (HttpServletRequest) request;
        logger.info("Method " + http.getMethod());
        logger.info("Path " + http.getServletPath());
        HttpServletResponse resp = (HttpServletResponse) response;
        logger.info("Status " + resp.getStatus());
        chain.doFilter(request,response);
    }
}
