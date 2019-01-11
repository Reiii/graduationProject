package com.yan.domain;

import java.io.Serializable;

/**
 * Package ：com.yan.domain
 * Description：商品下方的评论
 * date： 2019/1/5 下午3:16
 * author： Li KaiYan
 */

public class Comments implements Serializable {
    private String commodity_id;
    private Comment[] comments;

    public String getCommodity_id() {
        return commodity_id;
    }

    public void setCommodity_id(String commodity_id) {
        this.commodity_id = commodity_id;
    }

    public Comment[] getComments() {
        return comments;
    }

    public void setComments(Comment[] comments) {
        this.comments = comments;
    }
}
