package com.yan.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Package ：com.yan.domain
 * Description：订单
 * date： 2019/1/5 下午2:42
 * author： Li KaiYan
 */

public class Order implements Serializable {
    private String order_id;
    private String buyer_id;
    private String seller_id;
    private String commodity_id;
    private String order_time;
    private BigDecimal price;
    private String buyer_phone;
    private String province;
    private String city;
    private String district;
    private String address;
    private String means_of_transction;
    private String status;

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getBuyer_id() {
        return buyer_id;
    }

    public void setBuyer_id(String buyer_id) {
        this.buyer_id = buyer_id;
    }

    public String getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(String seller_id) {
        this.seller_id = seller_id;
    }

    public String getCommodity_id() {
        return commodity_id;
    }

    public void setCommodity_id(String commdity_id) {
        this.commodity_id = commdity_id;
    }

    public String getOrder_time() {
        return order_time;
    }

    public void setOrder_time(String order_time) {
        this.order_time = order_time;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getBuyer_phone() {
        return buyer_phone;
    }

    public void setBuyer_phone(String buyer_phone) {
        this.buyer_phone = buyer_phone;
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

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMeans_of_transction() {
        return means_of_transction;
    }

    public void setMeans_of_transction(String means_of_transction) {
        this.means_of_transction = means_of_transction;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
