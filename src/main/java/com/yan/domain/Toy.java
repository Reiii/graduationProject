package com.yan.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Package ：com.yan.domain
 * Description：二手玩具交易 玩具类
 * date： 2019/1/5 下午2:39
 * author： Li KaiYan
 */

public class Toy implements Serializable {
    private String commodity_id;
    private String uid;
    private String title;
    private String description;
    private BigDecimal price;
    private String means_of_transction;
    private String province;
    private String city;
    private String status;

    public String getCommodity_id() {
        return commodity_id;
    }

    public void setCommodity_id(String commdity_id) {
        this.commodity_id = commdity_id;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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
