package com.yan.domain;

import java.io.Serializable;

/**
 * Package ：com.yan.domain
 * Description：单张图片
 * date： 2019/1/5 下午3:24
 * author： Li KaiYan
 */

public class Picture implements Serializable {
    private String commodity_id;
    private String url;
    private String number;

    public String getCommodity_id() {
        return commodity_id;
    }

    public void setCommodity_id(String commodity_id) {
        this.commodity_id = commodity_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
