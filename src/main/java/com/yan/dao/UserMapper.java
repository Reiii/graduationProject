package com.yan.dao;

import com.yan.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Package ：com.yan.dao
 * Description：用户 dao 接口类
 * date： 2019/1/7 下午1:38
 * author： Li KaiYan
 */
@Mapper
public interface UserMapper {

    /**
     * 添加用户
     * @param user
     * @return
     */
    int addUser(User user);

    /**
     * 根据id选择用户
     * @param user
     * @return
     */
    User selectById(User user);

    /**
     * 根据email选择用户
     * @param user
     * @return
     */
    User selectUserByEmail(User user);

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    int update_user(User user);

}
