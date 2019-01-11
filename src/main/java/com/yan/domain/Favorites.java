package com.yan.domain;

import java.io.Serializable;

/**
 * Package ：com.yan.domain
 * Description：收藏夹
 * date： 2019/1/5 下午2:41
 * author： Li KaiYan
 */

public class Favorites implements Serializable {
    private String uid;
    private Toy[] toys;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Toy[] getToys() {
        return toys;
    }

    public void setToys(Toy[] toys) {
        this.toys = toys;
    }
}
