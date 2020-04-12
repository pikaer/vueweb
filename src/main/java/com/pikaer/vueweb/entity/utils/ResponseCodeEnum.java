package com.pikaer.vueweb.entity.utils;

public enum ResponseCodeEnum {
    SUCCESS(0, "SUCCESS"),
    INSERT_FAIL(-1, "数据库插入失败"),
    UPDATE_FAIL(-2, "数据库修改失败"),
    DELETE_FAIL(-2, "数据库删除失败"),
    REQUEST_VALIDFAIL(-10, "请求校验失败"),
    OTHER_ERROR(-99, "未知异常");
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