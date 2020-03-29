package com.xhf.demo.common;

import lombok.Getter;

/**
 * @author 谢红飞
 * @Title:
 * @Description:
 * @date 2019-11-25 22:49
 */
@Getter
public enum ResultEnum {

    FIELD_ERROR(24000, "字段错误"),
    SUCCESS(0, "返回成功"),
    FAILURE(1000, "系统错误");

    private Integer code;

    private String msg;

    private ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }


}
