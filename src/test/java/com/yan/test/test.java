package com.yan.test;

import com.yan.dao.UserMapper;
import com.yan.domain.User;
import com.yan.service.impl.UserServiceImpl;
import com.yan.util.Utils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Package ：com.yan.test
 * Description：
 * date： 2019/2/12 下午8:56
 * author： Li KaiYan
 */

public class test {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserServiceImpl userService;

    @Test
    public void demo(){
        System.out.println(Utils.isEmail("@qq.com"));
        System.out.println(Utils.isValidMessageAudio("4297F44B13955235245B2497399D7A93"));
    }
}
