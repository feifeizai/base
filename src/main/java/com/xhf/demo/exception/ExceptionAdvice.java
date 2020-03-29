package com.xhf.demo.exception;

import com.xhf.demo.common.ResultEnum;
import com.xhf.demo.common.ResultT;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 谢红飞
 * @Title:
 * @Description:
 * @date 2019-11-25 22:28
 */
@ControllerAdvice
public class ExceptionAdvice {

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultT checkException(MethodArgumentNotValidException e) {

        BindingResult bindingResult = e.getBindingResult();
        ObjectError error = bindingResult.getAllErrors().get(0);
        error.getObjectName();
        Map<String, String> map = new HashMap<>();
        map.put(error.getObjectName(),error.getDefaultMessage());
        return ResultT.failure(ResultEnum.FIELD_ERROR);
    }


    @ResponseBody
    @ExceptionHandler(IllegalArgumentException.class)
    public ResultT illegalException(IllegalArgumentException e) {
        return ResultT.failure(ResultEnum.FAILURE);
    }


}
