package com.yan.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.yan.constant.loginRequire;
import com.yan.domain.City_activity;
import com.yan.domain.User;
import com.yan.exception.ActivityException;
import com.yan.service.impl.ActivityServiceImpl;
import com.yan.util.Page;
import com.yan.util.Status;
import com.yan.util.StatusMsg;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Package ：com.yan.controller
 * Description：
 * date： 2019/2/22 下午4:47
 * author： Li KaiYan
 */
@RestController
@RequestMapping(value = "/activity")
public class ActivityController {
    @Autowired
    private ActivityServiceImpl activityService;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView activityHome(){
        return new ModelAndView("activityHome");
    }

    @RequestMapping(value = "/getAllActivities", method = RequestMethod.GET)
    public Page<City_activity> getAllActivities(@RequestParam(value = "page", defaultValue = "1") String page) throws ActivityException {
        Page<City_activity> activity = activityService.getAllCity_Activity(page);
        return activity;
    }

    @RequestMapping(value = "/getActivities", method = RequestMethod.GET)
    public Page<City_activity> getActivities(@Param("keyword") String keyword, @Param("province") String province, @Param("city") String city, @RequestParam(value = "page", defaultValue = "1") String page) throws ActivityException{
        Page<City_activity> activity;
        if(keyword != null){
            activity = activityService.getCity_activitiesByTitle(keyword, page);
        }else if(city != null){
            activity = activityService.getCity_activitiesByCity(city, page);
        }else if(province != null){
            activity = activityService.getCity_activitiesByProvince(province, page);
        }else{
            activity = activityService.getAllCity_Activity(page);
        }
        return activity;
    }

    @RequestMapping(value = "/getActivity", method = RequestMethod.GET)
    public ModelAndView getActivity(@Param("activity_id") String activity_id, HttpSession session){
        session.setAttribute("activity_id", activity_id);
        return new ModelAndView("activityDetail");
    }

    @RequestMapping(value = "/getActivityDetail", method = RequestMethod.GET)
    public City_activity getActivityDetail(HttpSession session){
        String activity_id = (String)session.getAttribute("activity_id");
        City_activity city_activity = activityService.getCity_activityById(activity_id);
        return city_activity;
    }

    @loginRequire
    @RequestMapping(value = "/myActivity", method = RequestMethod.GET)
    public City_activity[] myActivity(HttpSession session){
        User user = (User)session.getAttribute("user");
        City_activity[] activities = activityService.getCity_activitiesByUser(user);
        return activities;
    }

    @loginRequire
    @RequestMapping(value = "/releaseActivity", method = RequestMethod.GET)
    public ModelAndView releaseActivity(){
        return new ModelAndView("releaseActivity");
    }

    @loginRequire
    @RequestMapping(value = "/doRelease", method = RequestMethod.POST)
    public Status doRelease(@Param("title") String title, @Param("start_time") String start_time, @Param("end_time") String end_time, @Param("province") String province, @Param("city") String city, @Param("address") String address, @Param("detail") String detail, @Param("cost") String cost, HttpSession session){
        Status status = new Status();
        User user = (User)session.getAttribute("user");
        City_activity activity = new City_activity();
        activity.setTitle(title);
        activity.setStart_time(start_time);
        activity.setEnd_time(end_time);
        activity.setProvince(province);
        activity.setCity(city);
        activity.setAddress(address);
        activity.setDetail(detail);
        activity.setCost(cost);
        activity.setTime(String.valueOf(new Date().getTime()));
        activity.setParticipants_num("0");
        activity.setStatus("0");
        if(activityService.addCity_activity(activity, user)){
            status.setStatus(StatusMsg.ADD_TOY_SUCCESS);
        }else{
            status.setStatus(StatusMsg.ADD_TOY_FAILED);
        }
        return status;
    }


}
