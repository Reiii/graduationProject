package com.yan.exception;

/**
 * Package ：com.yan.exception
 * Description：
 * date： 2019/1/22 下午4:46
 * author： Li KaiYan
 */

public class UserException extends Exception{

    public static final String NO_LOGIN = "未登录";
    public static final String WRONG_PASSWORD = "账号或密码错误";
    public static final String EMAIL_EXIST = "邮箱已存在";
    public static final String USER_NOT_ACTIVE = "账号未激活";
    public static final String USER_LOCKED = "账号被锁定";
    public static final String REG_ERROR = "注册失败";
    public static final String ACTIVE_FAILED = "激活失败";
    public static final String DENY_CHANGE = "验证失败";
    public static final String CHANGE_FAILED = "修改失败";
    public static final String UNKNOW_ERROR = "未知错误";

    public UserException(String msg){
        super(msg);
    }

}
