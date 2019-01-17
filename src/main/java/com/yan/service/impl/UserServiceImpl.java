package com.yan.service.impl;

import com.yan.dao.UserMapper;
import com.yan.domain.User;
import com.yan.service.UserService;
import com.yan.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Package ：com.yan.service.impl
 * Description：
 * date： 2019/1/17 下午11:11
 * author： Li KaiYan
 */

public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean check_email(String email) {
        User user = new User();
        user.setEmail(email);
        if(userMapper.selectUserByEmail(user) != null){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public boolean register(User user) {
        if(Utils.isEmail(user.getEmail()) && Utils.isValidMessageAudio(user.getPassword())){
            String uid = userMapper.addUser(user);
            user.setUid(uid);
            user = userMapper.selectById(user);
            user.setActive_code(Utils.getActiveCode());
            userMapper.update_user(user);
            Utils.sendActive_Code(user, 0);
            return true;
        }else{
            return false;
        }

    }

    @Override
    public boolean active(User user) {
        User user_in_db = userMapper.selectById(user);
        if("0".equals(user_in_db) && user_in_db.getActive_code().equals(user.getActive_code())){
            user_in_db.setActive_code("");
            user_in_db.setStatus("1");
            userMapper.update_user(user_in_db);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public User login(User user) {
        User user_in_db = userMapper.selectById(user);
        if(user_in_db != null && user_in_db.getPassword().equals(user.getPassword())){
            return user_in_db;
        }else{
            return null;
        }
    }

    @Override
    public boolean change_password(User user) {
        User user_in_db = userMapper.selectById(user);
        Utils.sendActive_Code(user_in_db, );

    }

    @Override
    public boolean forget_password(User user) {
        return false;
    }

    @Override
    public boolean verify_code(User user) {
        return false;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public User userInfo(User user) {
        return null;
    }
}
