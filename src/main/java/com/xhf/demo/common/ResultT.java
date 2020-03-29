package com.xhf.demo.common;

import lombok.Builder;
import lombok.Data;

/**
 * @author 谢红飞
 * @Title:
 * @Description:
 * @date 2019-11-25 22:38
 */
@Data
@Builder
public class ResultT<T> {

    private T data;

    private int code;

    private String msg;

    public static ResultT success() {
        return ResultT.builder().code(ResultEnum.SUCCESS.getCode()).msg(ResultEnum.SUCCESS.getMsg()).build();
    }

    public static <T> ResultT success(T t) {
        return ResultT.builder().code(ResultEnum.SUCCESS.getCode()).msg(ResultEnum.SUCCESS.getMsg()).data(t).build();
    }

    public static ResultT failure() {
        return ResultT.builder().code(ResultEnum.FAILURE.getCode()).msg(ResultEnum.FAILURE.getMsg()).build();
    }

    public static <T> ResultT failure(ResultEnum resultEnum) {
        return ResultT.builder().code(resultEnum.getCode()).msg(resultEnum.getMsg()).build();
    }
}
