package com.yan.controller;

import com.yan.constant.loginRequire;
import com.yan.domain.*;
import com.yan.exception.MallException;
import com.yan.service.MallService;
import com.yan.service.impl.MallServiceImpl;
import com.yan.service.impl.UserServiceImpl;
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
    @Autowired
    private UserServiceImpl userService;

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
    public Comments getComments(HttpSession session){
        String commodity_id = (String)session.getAttribute("viewItem");
        if(commodity_id != null){
            Toy toy = new Toy();
            toy.setCommodity_id(commodity_id);
            Comments comments = mallService.getComments(toy);
            return comments;
        }
        return null;
    }

    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
    public User getUserInfo(@RequestParam("uid") String uid){
        User user = new User();
        user.setUid(uid);
        User user_in_db = userService.userInfo(user);
        return user_in_db;
    }

    @RequestMapping(value = "/getToyPic", method = RequestMethod.GET)
    public Pictures getToyPic(HttpSession session){
        String commodity_id = (String)session.getAttribute("viewItem");
        if(commodity_id != null){
            Toy toy = new Toy();
            toy.setCommodity_id(commodity_id);
            Pictures pics = mallService.getPictures(toy);
            return pics;
        }
        return null;
    }

    @RequestMapping(value = "/cover", method = RequestMethod.GET)
    public Picture ToyCover(@Param("commodity_id") String commodity_id){
        Toy toy = new Toy();
        toy.setCommodity_id(commodity_id);
        Picture cover = mallService.getCover(toy);
        return cover;
    }

    @loginRequire
    @RequestMapping(value = "/doBuy", method = RequestMethod.GET)
    public ModelAndView doBuy(@RequestParam("commodity_id") String commodity_id, HttpSession session){
        session.setAttribute("buyItem", commodity_id);
        ModelAndView mav = new ModelAndView("confirmOrder");
        return mav;
    }

    @loginRequire
    @RequestMapping(value = "/getBuyItem", method = RequestMethod.GET)
    public Toy getBuyItem(HttpSession session){
        String commodity_id = (String)session.getAttribute("buyItem");
        if(commodity_id != null){
            Toy toy = new Toy();
            toy.setCommodity_id(commodity_id);
            Toy toy_in_db = mallService.toyInfo(toy);
            return toy_in_db;
        }
        return null;
    }

    @loginRequire
    @RequestMapping(value = "/submitOrder", method = RequestMethod.POST)
    public Status submitOrder(HttpSession session, @RequestParam("buyer_phone") String phone, @RequestParam("province") String province, @RequestParam("city") String city, @RequestParam("district") String district, @RequestParam("address") String address){
        String buyer_id = ((User)session.getAttribute("user")).getUid();
        String commodity_id = (String)session.getAttribute("buyItem");
        Order order = new Order();
        order.setBuyer_id(buyer_id);
        order.setCommdity_id(commodity_id);
        order.setBuyer_phone(phone);
        order.setProvince(province);
        order.setCity(city);
        order.setDistrict(district);
        order.setAddress(address);
        order.setStatus("0");
        boolean isSuccess = mallService.addOrder(order);
        if(isSuccess){
            return new Status(StatusMsg.ORDER_SUCCESS);
        }else{
            return new Status(StatusMsg.ORDER_FAILED);
        }
    }

}
