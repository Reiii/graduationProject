package com.yan.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Package ：com.yan.domain
 * Description：
 * date： 2019/1/7 下午1:09
 * author： Li KaiYan
 */

public class Theme_sticker implements Serializable {
    private String subject_id;
    private String theme_id;
    private String title;
    private String classification;
    private String time;
    private String uid;
    private String last_reply_time;
    private String type;
    private String reply_num;
    private String views;
    private String status;

    public Theme_sticker(){}

    public Theme_sticker(String subject_id, String title, String uid, String classification){
        this.subject_id = subject_id;
        this.title = title;
        this.uid = uid;
        this.classification = classification;
        this.time = String.valueOf(new Date().getTime());
        this.type = "0";
        this.reply_num = "0";
        this.views = "0";
        this.status = "0";
    }

    public String getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(String subject_id) {
        this.subject_id = subject_id;
    }

    public String getTheme_id() {
        return theme_id;
    }

    public void setTheme_id(String theme_id) {
        this.theme_id = theme_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getLast_reply_time() {
        return last_reply_time;
    }

    public void setLast_reply_time(String last_reply_time) {
        this.last_reply_time = last_reply_time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReply_num() {
        return reply_num;
    }

    public void setReply_num(String reply_num) {
        this.reply_num = reply_num;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
