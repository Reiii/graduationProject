package com.yan.service;

import com.yan.domain.City_activity;
import com.yan.domain.User;

/**
 * Package ：com.yan.service
 * Description：
 * date： 2019/1/11 下午2:00
 * author： Li KaiYan
 */

public interface ActivityService {

    /**
     * 添加同城活动
     * @param city_activity
     * @return
     */
    Boolean addCity_activity(City_activity city_activity, User user);

    /**
     * 删除同城活动
     * @param city_activity
     * @param user
     * @return
     */
    Boolean delCity_activity(City_activity city_activity, User user);

    /**
     * 更新同城活动
     * @param city_activity
     * @param user
     * @return
     */
    Boolean updateCity_activity(City_activity city_activity, User user);

    /**
     * 根据Id获取同城活动
     * @param id
     * @return
     */
    City_activity getCity_activityById(String id);

    /**
     * 根据标题获取同城活动
     * @param title
     * @return
     */
    City_activity[] getCity_activitiesByTitle(String title);

    /**
     * 根据省份获取同城活动
     * @param province
     * @return
     */
    City_activity[] getCity_activitiesByProvince(String province);

    /**
     * 根据城市获取同城活动
     * @param city
     * @return
     */
    City_activity[] getCity_activitiesByCity(String city);

    /**
     * 根据费用获取同城活动
     * @param cost
     * @return
     */
    City_activity[] getCity_activitiesByCost(String cost);

    /**
     * 根据状态获取同城活动
     * @param status
     * @return
     */
    City_activity[] getCity_activitiesByStatus(String status);

    /**
     * 添加参与者
     * @param city_activity
     * @param user
     * @return
     */
    Boolean addParticipants(City_activity city_activity, User user);

    /**
     * 删除参与者
     * @param city_activity
     * @param user
     * @return
     */
    Boolean delParticipants(City_activity city_activity, User user);

    /**
     * 获取用户参与的同城活动
     * @param user
     * @return
     */
    City_activity[] getCity_activitiesByUser(User user);

    /**
     * 获取同城活动的参与者
     * @param city_activity
     * @return
     */
    User[] getUsersByCity_activity(City_activity city_activity);

}
