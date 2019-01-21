package com.yan.domain;

import java.io.Serializable;

/**
 * Package ：com.yan.domain
 * Description：收藏夹
 * date： 2019/1/5 下午2:41
 * author： Li KaiYan
 */

public class Favorite implements Serializable {
    private int fid;
    private User user;
    private Toy toy;

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Toy getToy() {
        return toy;
    }

    public void setToy(Toy toy) {
        this.toy = toy;
    }
}
