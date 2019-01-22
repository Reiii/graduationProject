package com.yan.dao;

import com.yan.domain.City_activity;
import com.yan.domain.User;

/**
 * Package ：com.yan.dao
 * Description：同城活动dao接口
 * date： 2019/1/7 下午5:22
 * author： Li KaiYan
 */

public interface ActivityMapper {

    /**
     * 增加同城活动
     * @param city_activity
     * @return
     */
    String addActivity(City_activity city_activity);

    /**
     * 删除同城活动
     * @param city_activity
     * @return
     */
    String delActivity(City_activity city_activity);

    /**
     * 更新同城活动
     * @param city_activity
     * @return
     */
    String updateActivity(City_activity city_activity);

    /**
     * 根据Id选择同城活动
     * @param city_activity
     * @return
     */
    City_activity selectActivityById(City_activity city_activity);

    /**
     * 根据标题选择同城活动
     * @param city_activity
     * @return
     */
    City_activity[] selectActivityByTitle(City_activity city_activity);

    /**
     * 根据时间选择同城活动
     * @param city_activity
     * @return
     */
    City_activity[] selectActivityByTime(City_activity city_activity);

    /**
     * 根据省份选择同城活动
     * @param city_activity
     * @return
     */
    City_activity[] selectActivityByProvince(City_activity city_activity);

    /**
     * 根据城市选择同城活动
     * @param city_activity
     * @return
     */
    City_activity[] selectActivityByCity(City_activity city_activity);

    /**
     * 根据费用选择同城活动
     * @param city_activity
     * @return
     */
    City_activity[] selectActivityByCost(City_activity city_activity);

    /**
     * 根据活动状态选择同城活动
     * @param city_activity
     * @return
     */
    City_activity[] selectActivityByStatus(City_activity city_activity);

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
