package com.yan.controller;

import com.yan.domain.Toy;
import com.yan.domain.User;
import com.yan.exception.MallException;
import com.yan.service.MallService;
import com.yan.service.impl.MallServiceImpl;
import com.yan.util.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * Package ：com.yan.controller
 * Description：
 * date： 2019/1/20 上午11:15
 * author： Li KaiYan
 */

@RequestMapping(value = "/mall")
@RestController
public class MallController {
    @Autowired
    private MallServiceImpl mallService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView homePage(HttpSession session) throws MallException {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("mall");
        return mav;
    }

    @RequestMapping(value = "/getItems", method = RequestMethod.GET)
    public Page getItems(@Param("keyword") String keyword, @RequestParam(value = "page", defaultValue = "1") String page ) throws MallException{
        Page itemPage;
        if(keyword == null){
            itemPage = mallService.getAllToys(page);
            return itemPage;
        }else{
            itemPage = mallService.getToysByKeyword(keyword, page);
        }
        return itemPage;
    }


}
