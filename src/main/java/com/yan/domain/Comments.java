package com.yan.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Package ：com.yan.domain
 * Description：
 * date： 2019/2/15 下午5:47
 * author： Li KaiYan
 */

public class Comments implements Serializable {
    private List<Comment> comments;

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
