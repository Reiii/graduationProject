package com.yan.service.impl;

import com.yan.dao.ActivityMapper;
import com.yan.domain.City_activity;
import com.yan.domain.User;
import com.yan.exception.ActivityException;
import com.yan.service.ActivityService;
import com.yan.util.Page;
import com.yan.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Package ：com.yan.service.impl
 * Description：
 * date： 2019/1/22 上午9:27
 * author： Li KaiYan
 */
@Service
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
    public Page<City_activity> getAllCity_Activity(String page) throws ActivityException{
        City_activity city_activity = new City_activity();
        city_activity.setStart_time(String.valueOf(new Date().getTime()));
        int total = activityMapper.countAllActivity(city_activity);
        int total_page = total / 20 * 20 == total ? total / 20 : total / 20 + 1;
        if(Integer.parseInt(page) > total_page){
            throw new ActivityException(ActivityException.PAGE_OVER_LIMIT);
        }
        City_activity[] activities = activityMapper.selectAllActivity(city_activity, (Integer.parseInt(page) - 1) * 20);
        Page<City_activity> activityPage = new Page<>();
        activityPage.setCurrentPage(Integer.parseInt(page));
        activityPage.setEndPage(total_page);
        activityPage.setData(Arrays.asList(activities));
        activityPage.setStartPage(1);
        return activityPage;
    }

    @Override
    public Page<City_activity> getCity_activitiesByTitle(String title, String page) throws ActivityException {
        City_activity city_activity = new City_activity();
        city_activity.setStart_time(String.valueOf(new Date().getTime()));
        city_activity.setTitle(title);
        int total = activityMapper.countActivityByKeyword(city_activity);
        int total_page = total / 20 * 20 == total ? total / 20 : total / 20 + 1;
        if(Integer.parseInt(page) > total_page){
            throw new ActivityException(ActivityException.PAGE_OVER_LIMIT);
        }
        City_activity[] activities = activityMapper.selectActivityByTitle(city_activity, (Integer.parseInt(page) - 1) * 20);
        Page<City_activity> activityPage = new Page<>();
        activityPage.setCurrentPage(Integer.parseInt(page));
        activityPage.setEndPage(total_page);
        activityPage.setData(Arrays.asList(activities));
        activityPage.setStartPage(1);
        return activityPage;
    }

    @Override
    public Page<City_activity> getCity_activitiesByProvince(String province, String page) throws ActivityException {
        City_activity city_activity = new City_activity();
        city_activity.setStart_time(String.valueOf(new Date().getTime()));
        city_activity.setProvince(province);
        int total = activityMapper.countActivityByProvince(city_activity);
        int total_page = total / 20 * 20 == total ? total / 20 : total / 20 + 1;
        if(Integer.parseInt(page) > total_page){
            throw new ActivityException(ActivityException.PAGE_OVER_LIMIT);
        }
        City_activity[] activities = activityMapper.selectActivityByProvince(city_activity, (Integer.parseInt(page) - 1) * 20);
        Page<City_activity> activityPage = new Page<>();
        activityPage.setCurrentPage(Integer.parseInt(page));
        activityPage.setEndPage(total_page);
        activityPage.setData(Arrays.asList(activities));
        activityPage.setStartPage(1);
        return activityPage;
    }

    @Override
    public Page<City_activity> getCity_activitiesByCity(String city, String page) throws ActivityException {
        City_activity city_activity = new City_activity();
        city_activity.setStart_time(String.valueOf(new Date().getTime()));
        city_activity.setCity(city);
        int total = activityMapper.countActivityByCity(city_activity);
        int total_page = total / 20 * 20 == total ? total / 20 : total / 20 + 1;
        if(Integer.parseInt(page) > total_page){
            throw new ActivityException(ActivityException.PAGE_OVER_LIMIT);
        }
        City_activity[] activities = activityMapper.selectActivityByCity(city_activity, (Integer.parseInt(page) - 1) * 20);
        Page<City_activity> activityPage = new Page<>();
        activityPage.setCurrentPage(Integer.parseInt(page));
        activityPage.setEndPage(total_page);
        activityPage.setData(Arrays.asList(activities));
        activityPage.setStartPage(1);
        return activityPage;
    }

    @Override
    public Page<City_activity> getCity_activitiesByCost(String cost, String page) throws ActivityException {
        City_activity city_activity = new City_activity();
        city_activity.setStart_time(String.valueOf(new Date().getTime()));
        city_activity.setCost(cost);
        int total = activityMapper.countActivityByCost(city_activity);
        int total_page = total / 20 * 20 == total ? total / 20 : total / 20 + 1;
        if(Integer.parseInt(page) > total_page){
            throw new ActivityException(ActivityException.PAGE_OVER_LIMIT);
        }
        City_activity[] activities = activityMapper.selectActivityByCost(city_activity, (Integer.parseInt(page) - 1) * 20);
        Page<City_activity> activityPage = new Page<>();
        activityPage.setCurrentPage(Integer.parseInt(page));
        activityPage.setEndPage(total_page);
        activityPage.setData(Arrays.asList(activities));
        activityPage.setStartPage(1);
        return activityPage;
    }

    @Override
    public Page<City_activity> getCity_activitiesByStatus(String status, String page) throws ActivityException {
        City_activity city_activity = new City_activity();
        city_activity.setStart_time(String.valueOf(new Date().getTime()));
        city_activity.setStatus(status);
        int total = activityMapper.countActivityByStatus(city_activity);
        int total_page = total / 20 * 20 == total ? total / 20 : total / 20 + 1;
        if(Integer.parseInt(page) > total_page){
            throw new ActivityException(ActivityException.PAGE_OVER_LIMIT);
        }
        City_activity[] activities = activityMapper.selectActivityByStatus(city_activity, (Integer.parseInt(page) - 1) * 20);
        Page<City_activity> activityPage = new Page<>();
        activityPage.setCurrentPage(Integer.parseInt(page));
        activityPage.setEndPage(total_page);
        activityPage.setData(Arrays.asList(activities));
        activityPage.setStartPage(1);
        return activityPage;
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

    @Override
    public String UploadCover(MultipartFile file) throws IOException {
        String name = "avtivity" + new Date().getTime() + ".jpg";
        if(Utils.uploadFile(name, file.getInputStream())){
            return name;
        }
        return null;
    }
}
