package com.yan.exception;

/**
 * Package ：com.yan.exception
 * Description：
 * date： 2019/2/18 下午5:00
 * author： Li KaiYan
 */

public class NoLoginException extends Exception {
    public static final String NO_LOGIN = "未登录";

    public NoLoginException(String msg){
        super(msg);
    }
}
