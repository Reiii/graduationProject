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
    private List<Picture> pictures;

    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }
}
