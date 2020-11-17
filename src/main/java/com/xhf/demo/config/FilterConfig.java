package com.xhf.demo.config;

import com.xhf.demo.filter.RequestFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Administrator
 * @date 2020-11-01 13:23
 */
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean  bean = new FilterRegistrationBean(new RequestFilter());
        //bean.addUrlPatterns(new String[]{"*.do","*.jsp"});
        bean.addUrlPatterns("*");
        return bean;
    }
}
