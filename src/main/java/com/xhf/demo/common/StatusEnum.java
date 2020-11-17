package com.xhf.demo.common;

import lombok.Getter;

/**
 * @author 谢红飞
 * @date 2019-11-25 22:49
 */
@Getter
public enum StatusEnum {

    FIELD_ERROR(24000, "字段错误"),
    SUCCESS(0, "返回成功"),
    FAILURE(-1, "系统错误"),
    BIZ_ERROR(2000, "业务错误"),
    SERIALIZABLE_ERROR(2001, "序列化错误");

    private int code;

    private String msg;

    private StatusEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }


}
