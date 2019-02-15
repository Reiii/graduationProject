package com.yan.controller;

import com.yan.domain.Picture;
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

@RestController
@RequestMapping(value = "/mall")
public class MallController {
    @Autowired
    private MallServiceImpl mallService;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView homePage() throws MallException {
        ModelAndView mav = new ModelAndView("mall");
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

    @RequestMapping(value = "/item", method = RequestMethod.GET)
    public ModelAndView ToyDetail(@Param("commodity_id") String commodity_id, HttpSession session){
        session.setAttribute("viewItem", commodity_id);
        ModelAndView mav = new ModelAndView("item");
        return mav;
    }

    @RequestMapping(value = "/getSeller", method = RequestMethod.GET)
    public User getSeller(HttpSession session){
        String commodity_id = (String)session.getAttribute("viewItem");
        if(commodity_id != null){
            Toy toy = new Toy();
            toy.setCommodity_id(commodity_id);
            User seller = mallService.getUserByToy(toy);
            return seller;
        }
        return null;
    }

    @RequestMapping(value = "/getToyInfo", method = RequestMethod.GET)
    public Toy getToyInfo(HttpSession session){
        String commodity_id = (String)session.getAttribute("viewItem");
        if(commodity_id != null){
            Toy toy = new Toy();
            toy.setCommodity_id(commodity_id);
            Toy toy_in_db = mallService.toyInfo(toy);
            return toy_in_db;
        }
        return null;
    }

    @RequestMapping(value = "/getComment", method = RequestMethod.GET)
    public

    @RequestMapping(value = "/cover", method = RequestMethod.GET)
    public Picture ToyCover(@Param("commodity_id") String commodity_id){
        Toy toy = new Toy();
        toy.setCommodity_id(commodity_id);
        Picture cover = mallService.getCover(toy);
        return cover;
    }


}
