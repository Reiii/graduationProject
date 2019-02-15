package com.yan.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Package ：com.yan.domain
 * Description：商品的图片
 * date： 2019/1/5 下午3:25
 * author： Li KaiYan
 */

public class Pictures implements Serializable {
    private String commodity_id;
    private List<Picture> pictures;

    public String getCommodity_id() {
        return commodity_id;
    }

    public void setCommodity_id(String commodity_id) {
        this.commodity_id = commodity_id;
    }

    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }
}
