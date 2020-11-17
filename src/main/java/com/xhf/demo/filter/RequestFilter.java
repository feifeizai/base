package com.xhf.demo.filter;

import com.xhf.demo.common.BaseContextHandler;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author Administrator
 * @date 2020-11-01 13:21
 */
public class RequestFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        System.out.println("进入RequestFilter");

        BaseContextHandler.setUsername(request.getParameter("username"));

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        BaseContextHandler.remove();
    }

}
