package com.yan.domain;

import java.io.Serializable;

/**
 * Package ：com.yan.domain
 * Description：
 * date： 2019/1/7 下午1:30
 * author： Li KaiYan
 */

public class Participants implements Serializable {
    private String activity_id;
    private User[] users;

    public String getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(String activity_id) {
        this.activity_id = activity_id;
    }

    public User[] getUsers() {
        return users;
    }

    public void setUsers(User[] users) {
        this.users = users;
    }
}
