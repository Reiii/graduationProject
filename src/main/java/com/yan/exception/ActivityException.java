package com.yan.exception;

/**
 * Package ：com.yan.exception
 * Description：
 * date： 2019/2/22 下午7:43
 * author： Li KaiYan
 */

public class ActivityException extends Exception {
    public static final String PAGE_OVER_LIMIT = "超过页数限制";

    public ActivityException(String msg){
        super(msg);
    }
}
