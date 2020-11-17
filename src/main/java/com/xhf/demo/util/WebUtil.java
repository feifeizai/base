package com.xhf.demo.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 谢红飞
 * description:
 * date 2020-8-23 13:01
 */
public class WebUtil {

    /**
     * 获取request, 用于获取用户信息, service层可通过该方法获取信息
     */
    public HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

}
