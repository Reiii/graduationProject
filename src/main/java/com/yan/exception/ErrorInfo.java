package com.yan.exception;

/**
 * Package ：com.yan.exception
 * Description：
 * date： 2019/1/25 上午9:45
 * author： Li KaiYan
 */

public class ErrorInfo<T> {
    public static final int OK = 1;
    public static final int ERROR = -1;
    private int code;
    private String msg;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
