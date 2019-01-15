package com.yan.util;

import java.util.Properties;
import java.util.Random;

/**
 * Package ：com.yan.util
 * Description：
 * date： 2019/1/15 下午10:31
 * author： Li KaiYan
 */

public class ActiveCode {
    public static final String str = "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String getActiveCode(){
        StringBuilder sb = new StringBuilder();
        Random rd = new Random();
        for(int i = 0; i < 32; i++){
            sb.append(str.charAt(rd.nextInt(62)));
        }
        return sb.toString();
    }
}
