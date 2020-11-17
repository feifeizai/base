package com.xhf.demo.common;

import lombok.Data;

/**
 * @author 谢红飞
 * date 2020-9-4
 */
@Data
public class BizException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    private int errorCode;

    /**
     * 错误信息
     */
    private String errorMsg;

    public BizException() {
        super();
    }

    public BizException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    public BizException(int errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public BizException(int errorCode, String errorMsg, Throwable cause) {
        super(errorMsg, cause);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public BizException(StatusEnum statusEnum) {
        super(statusEnum.getMsg());
        this.errorCode = statusEnum.getCode();
        this.errorMsg = statusEnum.getMsg();
    }
}
