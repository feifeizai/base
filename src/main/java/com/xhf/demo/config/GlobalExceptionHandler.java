package com.xhf.demo.config;

import com.xhf.demo.common.BizException;
import com.xhf.demo.common.StatusEnum;
import com.xhf.demo.common.ResultT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 谢红飞
 * date 2019-11-25 22:28
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultT checkException(HttpServletRequest req, MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        ObjectError error = bindingResult.getAllErrors().get(0);
        error.getObjectName();
        Map<String, String> map = new HashMap<>();
        map.put(error.getObjectName(), error.getDefaultMessage());
        return ResultT.failure(StatusEnum.FIELD_ERROR, map);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResultT illegalException(HttpServletRequest req, IllegalArgumentException e) {
        return ResultT.failure(StatusEnum.FAILURE);
    }

    @ExceptionHandler(BizException.class)
    public ResultT bizException(HttpServletRequest req, BizException e) {
        return ResultT.failure(StatusEnum.BIZ_ERROR);
    }

}
