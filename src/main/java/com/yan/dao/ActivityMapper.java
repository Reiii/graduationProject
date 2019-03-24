package com.yan.dao;

import com.yan.domain.City_activity;
import com.yan.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Package ：com.yan.dao
 * Description：同城活动dao接口
 * date： 2019/1/7 下午5:22
 * author： Li KaiYan
 */
@Mapper
public interface ActivityMapper {

    /**
     * 增加同城活动
     * @param city_activity
     * @return
     */
    int addActivity(City_activity city_activity);

    /**
     * 删除同城活动
     * @param city_activity
     * @return
     */
    int delActivity(City_activity city_activity);

    /**
     * 更新同城活动
     * @param city_activity
     * @return
     */
    int updateActivity(City_activity city_activity);

    /**
     * 根据Id选择同城活动
     * @param city_activity
     * @return
     */
    City_activity selectActivityById(City_activity city_activity);

    /**
     * 所有同城活动
     * @param start
     * @return
     */
    City_activity[] selectAllActivity(@Param("city_activity") City_activity city_activity, @Param("start") int start);

    /**
     * 根据标题选择同城活动
     * @param city_activity
     * @return
     */
    City_activity[] selectActivityByTitle(@Param("city_activity") City_activity city_activity, @Param("start") int start);

    /**
     * 根据时间选择同城活动
     * @param city_activity
     * @return
     */
    City_activity[] selectActivityByTime(@Param("city_activity") City_activity city_activity, @Param("start") int start);

    /**
     * 根据省份选择同城活动
     * @param city_activity
     * @return
     */
    City_activity[] selectActivityByProvince(@Param("city_activity") City_activity city_activity, @Param("start") int start);

    /**
     * 根据城市选择同城活动
     * @param city_activity
     * @return
     */
    City_activity[] selectActivityByCity(@Param("city_activity") City_activity city_activity, @Param("start") int start);

    /**
     * 根据费用选择同城活动
     * @param city_activity
     * @return
     */
    City_activity[] selectActivityByCost(@Param("city_activity") City_activity city_activity, @Param("start") int start);

    /**
     * 根据活动状态选择同城活动
     * @param city_activity
     * @return
     */
    City_activity[] selectActivityByStatus(@Param("city_activity") City_activity city_activity, @Param("start") int start);

    /**
     * 获得同城活动的数量
     * @param city_activity
     * @return
     */
    int countAllActivity(City_activity city_activity);

    /**
     * 根据关键字获得活动数量
     * @param city_activity
     * @return
     */
    int countActivityByKeyword(City_activity city_activity);

    /**
     * 根据省份获得活动数量
     * @param city_activity
     * @return
     */
    int countActivityByProvince(City_activity city_activity);

    /**
     * 根据城市获得活动数量
     * @param city_activity
     * @return
     */
    int countActivityByCity(City_activity city_activity);

    /**
     * 根据费用获得活动数量
     * @param city_activity
     * @return
     */
    int countActivityByCost(City_activity city_activity);

    /**
     * 根据活动状态获得活动数量
     * @param city_activity
     * @return
     */
    int countActivityByStatus(City_activity city_activity);

    /**
     * 添加参与者
     * @param city_activity
     * @param user
     * @return
     */
    boolean addParticipant(City_activity city_activity, User user);

    /**
     * 删除参与者
     * @param city_activity
     * @param user
     * @return
     */
    boolean delParticipant(City_activity city_activity, User user);

    /**
     * 根据用户选择同城活动
     * @param user
     * @return
     */
    City_activity[] selectActivityByUser(User user);

    /**
     * 根据同城活动选择用户
     * @param city_activity
     * @return
     */
    User[] selectUserByActivity(City_activity city_activity);


}
