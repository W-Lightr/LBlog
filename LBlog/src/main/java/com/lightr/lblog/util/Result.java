package com.lightr.lblog.util;

import java.io.Serializable;

public class Result implements Serializable {
    private int code;
    private String msg;
    private Object data;

    public static Result Successful(int code, String msg, Object data) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static Result Successful(Object data) {
        return Successful(200, "操作成功", data);
    }

    public static Result Failure(int code, String msg, Object data) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static Result Failure(String msg) {
        return Failure(400, msg, null);
    }

    public Result() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
