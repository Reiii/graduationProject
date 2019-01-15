package com.yan.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Package ：com.yan.util
 * Description：
 * date： 2019/1/15 下午11:45
 * author： Li KaiYan
 */

public class Utils {
    private static final String EMAIL_REGEX = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

    public static boolean isEmail(String email){
        Pattern regex = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = regex.matcher(email);
        return matcher.matches();
    }
}
