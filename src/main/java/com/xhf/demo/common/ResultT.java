package com.xhf.demo.common;

import lombok.Builder;
import lombok.Data;

/**
 * @author 谢红飞
 * date 2019-11-25 22:38
 */
@Data
@Builder
public class ResultT<T> {

    private T data;

    private int code;

    private String msg;

    public static ResultT success() {
        return ResultT.builder().code(StatusEnum.SUCCESS.getCode()).msg(StatusEnum.SUCCESS.getMsg()).build();
    }

    public static <T> ResultT success(T t) {
        return ResultT.<T>builder().code(StatusEnum.SUCCESS.getCode()).msg(StatusEnum.SUCCESS.getMsg()).data(t).build();
    }

    public static ResultT failure() {
        return ResultT.builder().code(StatusEnum.FAILURE.getCode()).msg(StatusEnum.FAILURE.getMsg()).build();
    }

    public static <T> ResultT failure(T data) {
        return ResultT.<T>builder().code(StatusEnum.FAILURE.getCode()).msg(StatusEnum.FAILURE.getMsg()).data(data).build();
    }

    public static ResultT failure(StatusEnum resultEnum) {
        return ResultT.builder().code(resultEnum.getCode()).msg(resultEnum.getMsg()).build();
    }

    public static <T> ResultT failure(StatusEnum resultEnum, T data) {
        return ResultT.<T>builder().code(resultEnum.getCode()).msg(resultEnum.getMsg()).data(data).build();
    }
}
