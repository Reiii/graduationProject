package com.yan.util;

import java.io.Serializable;

/**
 * Package ：com.yan.util
 * Description：
 * date： 2019/1/8 下午12:26
 * author： Li KaiYan
 */

public class Status implements Serializable {
    private String msg;

    public Status(){};

    public Status(String msg){
        this.msg = msg;
    }

    public String getStatus() {
        return msg;
    }

    public void setStatus(String msg) {
        this.msg = msg;
    }
}
