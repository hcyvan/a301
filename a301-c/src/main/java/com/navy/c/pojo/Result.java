package com.navy.c.pojo;

import java.io.Serializable;

public class Result implements Serializable {
    private Integer status;
    private String message;
    private Object data;

    public Result(Integer status, Object data, String message) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static Result build(Integer status, Object data, String message) {
        return new Result(status, data, message);
    }

    public static Result ok() {
        return Result.build(0, null, "");
    }
    public static Result ok(Object data) {
        return Result.build(0, data, "");
    }

    public Integer getStatus() {
        return this.status;
    }
    public String getMessage() {
        return this.message;
    }
    public Object getData() {
        return this.data;
    }
}
