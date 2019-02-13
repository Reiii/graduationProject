package com.yan.exception;

/**
 * Package ：com.yan.exception
 * Description：
 * date： 2019/2/12 上午11:12
 * author： Li KaiYan
 */

public class MallException extends Exception {

    public static final String PAGE_OVER_LIMIT = "页数超出";

    public MallException(String msg){
        super(msg);
    }

}
