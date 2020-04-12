package com.pikaer.vueweb.entity.utils;

public class BaseResponse<T> {
    /**
     * 0：成功
     */
    private int code;

    private String msg;

    private T data;

    public void setCodeAndMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public void setCodeAndMsg(ResponseCodeEnum codeEnum) {
        this.code = codeEnum.getCode();
        this.msg = codeEnum.getMsg();
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setCode(ResponseCodeEnum codeEnum) {
        this.code = codeEnum.getCode();
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
