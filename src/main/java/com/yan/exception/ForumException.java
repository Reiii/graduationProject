package com.yan.exception;

/**
 * Package ：com.yan.exception
 * Description：
 * date： 2019/2/26 下午4:44
 * author： Li KaiYan
 */

public class ForumException extends Exception {

    public static final String PAGE_OVER_LIMIT = "页数超出限制";

    public ForumException(String msg){
        super(msg);
    }

}
