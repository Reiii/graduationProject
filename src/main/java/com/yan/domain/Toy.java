package com.yan.domain;

import java.io.Serializable;

/**
 * Package ：com.yan.domain
 * Description：二手玩具交易 玩具类
 * date： 2019/1/5 下午2:39
 * author： Li KaiYan
 */

public class Toy implements Serializable {
    private String commdity_id;
    private String uid;
    private String title;
    private String description;
    private String type;
    private String means_of_transction;
    private String province;
    private String city;
    private String status;

    public String getCommdity_id() {
        return commdity_id;
    }

    public void setCommdity_id(String commdity_id) {
        this.commdity_id = commdity_id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMeans_of_transction() {
        return means_of_transction;
    }

    public void setMeans_of_transction(String means_of_transction) {
        this.means_of_transction = means_of_transction;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
