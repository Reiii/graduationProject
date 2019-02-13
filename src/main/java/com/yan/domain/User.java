package com.yan.domain;

import com.yan.util.Utils;

import java.io.Serializable;
import java.util.Date;

/**
 * Package ：com.yan.domain
 * Description：用户类
 * date： 2019/1/5 下午2:35
 * author： Li KaiYan
 */

public class User implements Serializable {
    private String uid;
    private String email;
    private String username;
    private String password;
    private String integral;
    private String credit;
    private String reg_time;
    private String last_login_time;
    private String active_code;
    private String type;
    private String status;

    public User(){

    }

    public User(String email, String username, String password){
        this.email = email;
        this.username = username;
        this.password = password;
        integral = "0";
        credit = "0";
        reg_time = String.valueOf(new Date().getTime());
        active_code = Utils.getActiveCode();
        type = "1";
        status = "0";
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIntegral() {
        return integral;
    }

    public void setIntegral(String integral) {
        this.integral = integral;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getReg_time() {
        return reg_time;
    }

    public void setReg_time(String reg_time) {
        this.reg_time = reg_time;
    }

    public String getLast_login_time() {
        return last_login_time;
    }

    public void setLast_login_time(String last_login_time) {
        this.last_login_time = last_login_time;
    }

    public String getActive_code() {
        return active_code;
    }

    public void setActive_code(String active_code) {
        this.active_code = active_code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
