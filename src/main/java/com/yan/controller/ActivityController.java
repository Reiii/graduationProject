package com.yan.controller;

import com.yan.domain.City_activity;
import com.yan.exception.ActivityException;
import com.yan.service.impl.ActivityServiceImpl;
import com.yan.util.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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
    public Page<City_activity> getAllActivities(@Param("page") String page) throws ActivityException {
        Page<City_activity> activity = activityService.getAllCity_Activity(page);
        return activity;
    }
    
}
