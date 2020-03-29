package com.xhf.demo.config;

import com.xhf.demo.common.ResultT;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author 谢红飞
 * @Title:
 * @Description:
 * @date 2019-11-25 23:06
 */
@ControllerAdvice(annotations = RestController.class)
//@ControllerAdvice(basePackages = "com.xhf.demo.controller")
public class SpringMvcAdvice implements ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {

        if (o instanceof ResultT) {
            return o;
        }
        if (o instanceof Void) {
            return ResultT.success();
        }

        return ResultT.success(o);
    }
}
