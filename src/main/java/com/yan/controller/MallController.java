package com.yan.controller;

import com.yan.constant.loginRequire;
import com.yan.domain.*;
import com.yan.exception.MallException;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

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
    public ModelAndView homePage() {
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
    public List<Map<String, String>> getComments(HttpSession session){
        String commodity_id = (String)session.getAttribute("viewItem");
        if(commodity_id != null){
            Toy toy = new Toy();
            toy.setCommodity_id(commodity_id);
            List<Map<String, String>> comments = mallService.getComments(toy);
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
    public ModelAndView doBuy(@Param("commodity_id") String commodity_id, HttpSession session){
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
    public Status submitOrder(HttpSession session, @Param("buyer_phone") String phone, @Param("province") String province, @Param("city") String city, @Param("district") String district, @Param("address") String address, @Param("transction") String transction){
        String buyer_id = ((User)session.getAttribute("user")).getUid();
        String commodity_id = (String)session.getAttribute("buyItem");
        Order order = new Order();
        order.setBuyer_id(buyer_id);
        order.setCommodity_id(commodity_id);
        order.setBuyer_phone(phone);
        order.setProvince(province);
        order.setCity(city);
        order.setDistrict(district);
        order.setAddress(address);
        order.setMeans_of_transction(transction);
        order.setStatus("0");
        boolean isSuccess = mallService.addOrder(order);
        if(isSuccess){
            return new Status(StatusMsg.ORDER_SUCCESS);
        }else{
            return new Status(StatusMsg.ORDER_FAILED);
        }
    }

    @loginRequire
    @RequestMapping(value = "/myOrders", method = RequestMethod.GET)
    public List<Map<String, String>> myOrders(HttpSession session){
        User user = (User)session.getAttribute("user");
        List<Map<String, String>> list = mallService.selectOrderByUser(user);
        return list;

    }

    @loginRequire
    @RequestMapping(value = "/myToys", method = RequestMethod.GET)
    public List<Map<String, String>> myToys(HttpSession session){
        User user = (User)session.getAttribute("user");
        List<Map<String, String>> list = mallService.selectToyByUser(user);
        return list;

    }

    @loginRequire
    @RequestMapping(value = "/releaseToy", method = RequestMethod.GET)
    public ModelAndView releaseToy(){
        return new ModelAndView("releaseToy");
    }

    @loginRequire
    @RequestMapping(value = "/doRelease", method = RequestMethod.POST)
    public Status doRelease(HttpSession session, @Param("title") String title, @Param("province") String province, @Param("city") String city, @Param("price") String price, @Param("means_of_transction") String means_of_transction, @Param("description") String description, @Param("pic") String[] pic){
        Status status = new Status();
        User user = (User)session.getAttribute("user");
        Toy toy = new Toy();
        toy.setTitle(title);
        toy.setUid(user.getUid());
        toy.setProvince(province);
        toy.setCity(city);
        toy.setPrice(new BigDecimal(price));
        toy.setMeans_of_transction(means_of_transction);
        toy.setDescription(description);
        toy.setStatus("0");
        if(mallService.addToy(toy, pic)){
            status.setStatus(StatusMsg.ADD_SUCCESS);
        }else{
            status.setStatus(StatusMsg.ADD_FAILED);
        }
        return status;
    }

    @loginRequire
    @RequestMapping(value = "/cancelOrder", method = RequestMethod.POST)
    public Status cancelOrder(@Param("order_id") String order_id, HttpSession session){
        Status status = new Status();
        Order order = new Order();
        User user = (User)session.getAttribute("user");
        order.setOrder_id(order_id);
        if(mallService.cancelOrder(order, user)){
            status.setStatus(StatusMsg.CHANGE_SUCCESS);
        }else{
            status.setStatus(StatusMsg.CHANGE_FAILED);
        }
        return status;

    }

    @loginRequire
    @RequestMapping(value = "/confirmOrder", method = RequestMethod.POST)
    public Status confirmOrder(@Param("order_id") String order_id, HttpSession session){
        Status status = new Status();
        Order order = new Order();
        User user = (User)session.getAttribute("user");
        order.setOrder_id(order_id);
        if(mallService.confirmOrder(order, user)){
            status.setStatus(StatusMsg.CHANGE_SUCCESS);
        }else{
            status.setStatus(StatusMsg.CHANGE_FAILED);
        }
        return status;
    }

    @loginRequire
    @RequestMapping(value = "/delToy", method = RequestMethod.POST)
    public Status delToy(@Param("commodity_id") String commodity_id, HttpSession session){
        Status status = new Status();
        Toy toy = new Toy();
        toy.setCommodity_id(commodity_id);
        User user = (User)session.getAttribute("user");
        if(mallService.delToy(toy, user)){
            status.setStatus(StatusMsg.DEL_SUCCESS);
        }else{
            status.setStatus(StatusMsg.DEL_FAILED);
        }
        return status;
    }

    @loginRequire
    @RequestMapping(value = "/uploadPic", method = RequestMethod.POST)
    public Status uploadPic(@RequestParam(value = "pic") MultipartFile multipartFile) throws IOException {
        Status status = new Status();
        String filename = mallService.UploadPic(multipartFile);
        if(filename != null){
            status.setStatus(filename);
        }else{
            status.setStatus("error");
        }
        return status;
    }

    @loginRequire
    @RequestMapping(value = "/addComment", method = RequestMethod.POST)
    public Status addComment(@RequestParam(value = "content") String content, @RequestParam(value = "commodity_id") String commodity_id, @RequestParam(value = "reply_id", required = false) String reply_id, HttpSession session){
        Status status = new Status();
        User user = (User)session.getAttribute("user");
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setTime(String.valueOf(new Date().getTime()));
        comment.setCommodity_id(commodity_id);
        if(reply_id == null || "".equals(reply_id)){
            comment.setType("0");
        }else{
            comment.setType("1");
            comment.setReply_id(reply_id);
        }
        if(mallService.addComment(comment, user)){
            status.setStatus(StatusMsg.ADD_SUCCESS);
        }else{
            status.setStatus(StatusMsg.ADD_FAILED);
        }
        return status;

    }

}
