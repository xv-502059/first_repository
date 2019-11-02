package com.mage.crm.base.exceptions;
/**
 *  自定义异常
 */
public class ParamsException extends RuntimeException{
    private Integer code;//状态码
    private String msg;//提示信息

    public ParamsException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
