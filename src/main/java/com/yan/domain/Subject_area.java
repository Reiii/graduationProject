package com.yan.domain;

import java.io.Serializable;

/**
 * Package ：com.yan.domain
 * Description：主题区域
 * date： 2019/1/5 下午3:36
 * author： Li KaiYan
 */

public class Subject_area implements Serializable {
    private String subject_id;
    private String name;
    private String theme_num;
    private String post_num;
    private String last_post_time;

    public Subject_area(){}

    public Subject_area(String name){
        this.name = name;
        theme_num = "0";
        post_num = "0";
    }

    public String getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(String subject_id) {
        this.subject_id = subject_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTheme_num() {
        return theme_num;
    }

    public void setTheme_num(String theme_num) {
        this.theme_num = theme_num;
    }

    public String getPost_num() {
        return post_num;
    }

    public void setPost_num(String post_num) {
        this.post_num = post_num;
    }

    public String getLast_post_time() {
        return last_post_time;
    }

    public void setLast_post_time(String last_post_time) {
        this.last_post_time = last_post_time;
    }
}
