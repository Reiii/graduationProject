package com.yan.service.impl;

import com.yan.dao.ActivityMapper;
import com.yan.domain.City_activity;
import com.yan.domain.User;
import com.yan.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Package ：com.yan.service.impl
 * Description：
 * date： 2019/1/22 上午9:27
 * author： Li KaiYan
 */

public class ActivityServiceImpl implements ActivityService {
    @Autowired
    private ActivityMapper activityMapper;
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    @Override
    public Boolean addCity_activity(City_activity city_activity, User user) {
        city_activity.setUid(user.getUid());
        city_activity.setTime(String.valueOf(new Date().getTime()));
        activityMapper.addActivity(city_activity);
        return true;
    }

    @Override
    public Boolean delCity_activity(City_activity city_activity, User user) {
        City_activity activity_in_db = activityMapper.selectActivityById(city_activity);
        if(activity_in_db.getUid().equals(user.getUid())){
            activityMapper.delActivity(activity_in_db);
            return true;
        }
        return false;
    }

    @Override
    public Boolean updateCity_activity(City_activity city_activity, User user) {
        City_activity activity_in_db = activityMapper.selectActivityById(city_activity);
        if(activity_in_db.getUid().equals(user.getUid())){
            activity_in_db.setTitle(city_activity.getTitle());
            activity_in_db.setStart_time(city_activity.getStart_time());
            activity_in_db.setEnd_time(city_activity.getEnd_time());
            activity_in_db.setProvince(city_activity.getProvince());
            activity_in_db.setCity(city_activity.getCity());
            activity_in_db.setAddress(city_activity.getAddress());
            activity_in_db.setDetail(city_activity.getDetail());
            activity_in_db.setCost(city_activity.getCost());
            activity_in_db.setParticipants_num(city_activity.getParticipants_num());
            activity_in_db.setStatus(city_activity.getStatus());
            activityMapper.updateActivity(activity_in_db);
            return true;
        }
        return false;
    }

    @Override
    public City_activity getCity_activityById(String id) {
        City_activity city_activity = new City_activity();
        city_activity.setActivity_id(id);
        return activityMapper.selectActivityById(city_activity);
    }

    @Override
    public City_activity[] getCity_activitiesByTitle(String title) {
        City_activity city_activity = new City_activity();
        city_activity.setTitle(title);
        return activityMapper.selectActivityByTitle(city_activity);
    }

    @Override
    public City_activity[] getCity_activitiesByProvince(String province) {
        City_activity city_activity = new City_activity();
        city_activity.setProvince(province);
        return activityMapper.selectActivityByProvince(city_activity);
    }

    @Override
    public City_activity[] getCity_activitiesByCity(String city) {
        City_activity city_activity = new City_activity();
        city_activity.setCity(city);
        return activityMapper.selectActivityByCity(city_activity);
    }

    @Override
    public City_activity[] getCity_activitiesByCost(String cost) {
        City_activity city_activity = new City_activity();
        city_activity.setCost(cost);
        return activityMapper.selectActivityByCost(city_activity);
    }

    @Override
    public City_activity[] getCity_activitiesByStatus(String status) {
        City_activity city_activity = new City_activity();
        city_activity.setStatus(status);
        return activityMapper.selectActivityByStatus(city_activity);
    }

    @Override
    public Boolean addParticipants(City_activity city_activity, User user) {
        activityMapper.addParticipant(city_activity, user);
        return true;
    }

    @Override
    public Boolean delParticipants(City_activity city_activity, User user) {
        activityMapper.delParticipant(city_activity, user);
        return true;
    }

    @Override
    public City_activity[] getCity_activitiesByUser(User user) {
        return activityMapper.selectActivityByUser(user);
    }

    @Override
    public User[] getUsersByCity_activity(City_activity city_activity) {
        return activityMapper.selectUserByActivity(city_activity);
    }
}
