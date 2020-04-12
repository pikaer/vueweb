package com.pikaer.vueweb.entity.utils;

public enum ResponseCodeEnum {
    SUCCESS(0, "SUCCESS");

    private int code;

    private String msg;

    ResponseCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}