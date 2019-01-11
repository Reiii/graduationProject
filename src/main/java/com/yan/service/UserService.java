package com.yan.service;

import com.yan.domain.User;
import com.yan.util.StatusMsg;

/**
 * Package ：com.yan.service
 * Description：用户模块 service层 接口类
 * date： 2019/1/8 上午10:45
 * author： Li KaiYan
 */

public interface UserService {

    /**
     * 检查邮件是否已经注册
     * @param email
     * @return
     */
    boolean check_email(String email);

    /**
     * 注册用户
     * @param user
     * @return
     */
    boolean register(User user);

    /**
     * 激活用户
     * @param user
     * @return
     */
    boolean active(User user);

    /**
     * 用户登录
     * @param user
     * @return
     */
    User login(User user);

    /**
     * 修改密码
     * @param user
     * @return
     */
    boolean change_password(User user);

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    User update(User user);

    /**
     * 查看用户信息
     * @param user
     * @return
     */
    User userInfo(User user);

}
