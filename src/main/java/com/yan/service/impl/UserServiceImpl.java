package com.yan.service.impl;

import com.yan.dao.UserMapper;
import com.yan.domain.User;
import com.yan.service.UserService;
import com.yan.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Package ：com.yan.service.impl
 * Description：
 * date： 2019/1/17 下午11:11
 * author： Li KaiYan
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean check_email(String email) {
        User user = new User();
        user.setEmail(email);
        User user_in_db = userMapper.selectUserByEmail(user);
        if(user_in_db != null){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public boolean register(User user) {
        if(Utils.isEmail(user.getEmail()) && Utils.isValidMessageAudio(user.getPassword())){
            userMapper.addUser(user);
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
        if("0".equals(user_in_db.getStatus()) && user_in_db.getActive_code().equals(user.getActive_code())){
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
        User user_in_db = userMapper.selectUserByEmail(user);
        if(user_in_db != null && user_in_db.getPassword().equals(user.getPassword())){
            return user_in_db;
        }else{
            return null;
        }
    }

    @Override
    public boolean change_password(User user) {
        User user_in_db = userMapper.selectById(user);
        Utils.sendActive_Code(user_in_db, 1);
        return true;
    }

    @Override
    public boolean forget_password(User user) {
        User user_in_db = userMapper.selectUserByEmail(user);
        if(user_in_db != null){
            user_in_db.setActive_code(Utils.getActiveCode());
            userMapper.update_user(user_in_db);
            Utils.sendActive_Code(user_in_db, 2);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean verify_code(User user) {
        User user_in_db = userMapper.selectById(user);
        if(user.getActive_code().equals(user_in_db.getActive_code())){
            user_in_db.setActive_code("");
            userMapper.update_user(user_in_db);
            return true;
        }
        return false;
    }

    @Override
    public User update(User user) {
        User user_in_db = userMapper.selectById(user);
        user_in_db.setPassword(user.getPassword());
        userMapper.update_user(user_in_db);
        return user_in_db;
    }

    @Override
    public User userInfo(User user) {
        User user_in_db = userMapper.selectById(user);
        return user_in_db;
    }
}
