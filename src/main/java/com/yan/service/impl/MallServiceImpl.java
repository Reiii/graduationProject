package com.yan.service.impl;

import com.yan.dao.MallMapper;
import com.yan.dao.UserMapper;
import com.yan.domain.*;
import com.yan.exception.MallException;
import com.yan.service.MallService;
import com.yan.util.Page;
import com.yan.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Package ：com.yan.service.impl
 * Description：
 * date： 2019/1/20 上午11:16
 * author： Li KaiYan
 */
@Service
public class MallServiceImpl implements MallService{

    @Autowired
    private MallMapper mallMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean addToy(Toy toy, String[] pic) {
        mallMapper.addToy(toy);
        for (int i = 0; i < pic.length; i++) {
            Picture picture = new Picture();
            picture.setCommodity_id(toy.getCommodity_id());
            picture.setNumber(String.valueOf(i + 1));
            picture.setUrl("http://104.248.178.168/images/" + pic[i]);
            mallMapper.addPic(picture);
        }
        return true;
    }

    @Override
    public Toy updateToy(Toy toy, User user) {
        Toy toy_in_db = mallMapper.selectToyById(toy);
        if(toy_in_db.getUid().equals(user.getUid())){
            mallMapper.updateToy(toy);
            return toy;
        }else{
            return null;
        }
    }

    @Override
    public boolean delToy(Toy toy, User user) {
        Toy toy_in_db = mallMapper.selectToyById(toy);
        if(toy_in_db.getUid().equals(user.getUid())){
            mallMapper.delToy(toy);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Page getAllToys(String page) throws MallException{
        int total = mallMapper.countAllToy();
        int total_page = total / 20 * 20 == total ? total / 20 : total / 20 + 1;
        if(Integer.parseInt(page) > total_page){
            throw new MallException(MallException.PAGE_OVER_LIMIT);
        }
        Page<Map<String, Object>> toyPage = new Page<>();
        List<Map<String, Object>> list = new ArrayList<>();
        Toy[] toys = mallMapper.selectAllToy((Integer.parseInt(page) - 1) * 20);
        for(Toy toy : toys){
            User user = new User();
            user.setUid(toy.getUid());
            User user_in_db = userMapper.selectById(user);
            Picture pic = mallMapper.selectPictureByCommodity_id(toy);
            Map<String, Object> map = new HashMap<>();
            map.put("toy", toy);
            map.put("user", user_in_db);
            map.put("pic", pic);
            list.add(map);
        }
        toyPage.setCurrentPage(Integer.parseInt(page));
        toyPage.setStartPage(1);
        toyPage.setEndPage(total_page);
        toyPage.setData(list);
        return toyPage;
    }

    @Override
    public Page getToysByKeyword(String keyword, String page) throws MallException{
        int total = mallMapper.countToyByKeyword(keyword);
        int total_page = total / 20 * 20 == total ? total / 20 : total / 20 + 1;
        if(Integer.parseInt(page) > total_page && total_page != 0){
            throw new MallException(MallException.PAGE_OVER_LIMIT);
        }
        Page<Map<String, Object>> toyPage = new Page<>();
        List<Map<String, Object>> list = new ArrayList<>();
        Toy[] toys = mallMapper.selectToyByTitle_page(keyword, (Integer.parseInt(page) - 1) * 20);
        for(Toy toy : toys){
            User user = new User();
            user.setUid(toy.getUid());
            User user_in_db = userMapper.selectById(user);
            Picture pic = mallMapper.selectPictureByCommodity_id(toy);
            Map<String, Object> map = new HashMap<>();
            map.put("toy", toy);
            map.put("user", user_in_db);
            map.put("pic", pic);
            list.add(map);
        }
        toyPage.setCurrentPage(Integer.parseInt(page));
        toyPage.setStartPage(1);
        toyPage.setEndPage(total_page);
        toyPage.setData(list);
        return toyPage;
    }

    @Override
    public Page<Toy> getToysByType(String type, String page) throws MallException {
        int total = mallMapper.countToyByType(type);
        int total_page = total / 20 * 20 == total ? total / 20 : total / 20 + 1;
        if(Integer.parseInt(page) > total_page){
            throw new MallException(MallException.PAGE_OVER_LIMIT);
        }
        Toy[] toys = mallMapper.selectToyByType_page(type, String.valueOf((Integer.parseInt(page) - 1) * 20));
        Page<Toy> toyPage = new Page<>();
        toyPage.setCurrentPage(Integer.parseInt(page));
        toyPage.setStartPage(1);
        toyPage.setEndPage(total_page);
        toyPage.setData(Arrays.asList(toys));
        return toyPage;
    }

    @Override
    public Page<Toy> getToysByProvince(String province, String page) throws MallException {
        int total = mallMapper.countToyByProvince(province);
        int total_page = total / 20 * 20 == total ? total / 20 : total / 20 + 1;
        if(Integer.parseInt(page) > total_page){
            throw new MallException(MallException.PAGE_OVER_LIMIT);
        }
        Toy[] toys = mallMapper.selectToyByProvince_page(province, String.valueOf((Integer.parseInt(page) - 1) * 20));
        Page<Toy> toyPage = new Page<>();
        toyPage.setCurrentPage(Integer.parseInt(page));
        toyPage.setStartPage(1);
        toyPage.setEndPage(total_page);
        toyPage.setData(Arrays.asList(toys));
        return toyPage;
    }

    @Override
    public Page<Toy> getToysByCity(String city, String page) throws MallException {
        int total = mallMapper.countToyByCity(city);
        int total_page = total / 20 * 20 == total ? total / 20 : total / 20 + 1;
        if(Integer.parseInt(page) > total_page){
            throw new MallException(MallException.PAGE_OVER_LIMIT);
        }
        Toy[] toys = mallMapper.selectToyByCity_page(city, String.valueOf((Integer.parseInt(page) - 1) * 20));
        Page<Toy> toyPage = new Page<>();
        toyPage.setCurrentPage(Integer.parseInt(page));
        toyPage.setStartPage(1);
        toyPage.setEndPage(total_page);
        toyPage.setData(Arrays.asList(toys));
        return toyPage;
    }

    @Override
    public Page<Toy> getToysByUser(User user, String page) throws MallException{
        int total = mallMapper.countToyByUser(user);
        int total_page = total / 20 * 20 == total ? total / 20 : total / 20 + 1;
        if(Integer.parseInt(page) > total_page){
            throw new MallException(MallException.PAGE_OVER_LIMIT);
        }
        Toy[] toys = mallMapper.selectToyByUser_page(user, String.valueOf((Integer.parseInt(page) - 1) * 20));
        Page<Toy> toyPage = new Page<>();
        toyPage.setCurrentPage(Integer.parseInt(page));
        toyPage.setStartPage(1);
        toyPage.setEndPage(total_page);
        toyPage.setData(Arrays.asList(toys));
        return toyPage;
    }

    @Override
    public Toy toyInfo(Toy toy) {
        return mallMapper.selectToyById(toy);
    }

    @Override
    public boolean addComment(Comment comment, User user) {
        comment.setUid(user.getUid());
        mallMapper.addComment(comment);
        return true;
    }

    @Override
    public boolean delComment(Comment comment, User user) {
        if(comment.getUid().equals(user.getUid())){
            mallMapper.delComment(comment);
            return true;
        }
        return false;
    }

    @Override
    public List<Map<String, String>> getComments(Toy toy) {
        List<Map<String, String>> list = new ArrayList<>();
        Comment[] comments = mallMapper.selectCommentByToy(toy);
        for (Comment comment : comments) {
            Map<String, String> map = new HashMap<>();
            User user = new User();
            user.setUid(comment.getUid());
            map.put("uid", comment.getUid());
            map.put("username", userMapper.selectById(user).getUsername());
            map.put("content", comment.getContent());
            map.put("time", comment.getTime());
            map.put("type", comment.getType());
            if(comment.getType().equals("1")){
                User reply_user = new User();
                reply_user.setUid(comment.getReply_id());
                map.put("reply_username", userMapper.selectById(reply_user).getUsername());
            }
            list.add(map);
        }
        return list;
    }

    @Override
    public boolean addFavorite(Toy toy, User user) {
        Favorite favorite = new Favorite();
        favorite.setUser(user);
        favorite.setToy(toy);
        mallMapper.addFavorite(favorite);
        return true;
    }

    @Override
    public boolean delFavorite(Favorite favorite, User user) {
        Favorite favorite_in_db = mallMapper.selectFavoriteById(favorite);
        if(favorite_in_db.getUser().getUid().equals(user.getUid())){
            mallMapper.delFavorite(favorite_in_db);
            return true;
        }
        return false;
    }

    @Override
    public synchronized boolean addOrder(Order order) {
        Toy toy = new Toy();
        toy.setCommodity_id(order.getCommodity_id());
        Toy toy_in_db = mallMapper.selectToyById(toy);
        if(toy_in_db.getStatus().equals("1") || toy_in_db.getStatus().equals("2")){
            return false;
        }else{
            order.setSeller_id(toy_in_db.getUid());
            order.setPrice(toy_in_db.getPrice());
            order.setOrder_time(String.valueOf(new Date().getTime()));
            mallMapper.addOrder(order);
            toy_in_db.setStatus("1");
            mallMapper.updateToy(toy_in_db);
            return true;
        }
    }

    @Override
    public boolean delOrder(Order order, User user) {
        Order order_in_db = mallMapper.selectOrderById(order);
        if(order_in_db.getBuyer_id().equals(user.getUid())){
            order_in_db.setStatus("2");
            mallMapper.updateOrder(order_in_db);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateOrder(Order order, User user) {
        Order order_in_db = mallMapper.selectOrderById(order);
        if(order_in_db.getBuyer_id().equals(user.getUid())){
            order_in_db.setPrice(order.getPrice());
            order_in_db.setBuyer_phone(order.getBuyer_phone());
            order_in_db.setProvince(order.getProvince());
            order_in_db.setCity(order.getCity());
            order_in_db.setAddress(order.getAddress());
            mallMapper.updateOrder(order_in_db);
            return true;
        }
        return false;
    }

    @Override
    public List selectOrderByUser(User user) {
        List<Map<String, String>> list = new ArrayList<>();
        Order[] orders = mallMapper.selectOrderByUser(user);
        for(Order o : orders){
            Toy toy = new Toy();
            toy.setCommodity_id(o.getCommodity_id());
            Toy toy_in_db = mallMapper.selectToyById(toy);
            Map<String, String> map = new HashMap<>();
            map.put("order_id", o.getOrder_id());
            map.put("order_time", o.getOrder_time());
            map.put("title", toy_in_db.getTitle());
            map.put("price", String.valueOf(toy_in_db.getPrice()));
            map.put("means_of_transction", "0".equals(o.getMeans_of_transction()) ? "线上交易" : "线下交易");
            map.put("status", "0".equals(o.getStatus()) ? "正在进行" : ("1".equals(o.getStatus()) ? "已完成" : "已取消"));
            list.add(map);
        }
        return list;
    }

    @Override
    public List selectToyByUser(User user) {
        List<Map<String, String>> list = new ArrayList<>();
        Toy[] toys = mallMapper.selectToyByUser(user);
        for(Toy t : toys){
            Map<String, String> map = new HashMap<>();
            map.put("commodity_id", t.getCommodity_id());
            map.put("title", t.getTitle());
            map.put("means_of_transction", "0".equals(t.getMeans_of_transction()) ? "线上交易" : ("1".equals(t.getMeans_of_transction()) ? "线下交易" : "线上线下均可"));
            map.put("price", String.valueOf(t.getPrice()));
            map.put("status", "0".equals(t.getStatus()) ? "未售出" : ("1".equals(t.getStatus()) ? "正在进行" : "已售出"));
            list.add(map);
        }
        return list;
    }

    @Override
    public Order orderInfo(Order order, User user) {
        Order order_in_db = mallMapper.selectOrderById(order);
        if(order_in_db.getBuyer_id().equals(user.getUid())){
            return order_in_db;
        }
        return null;
    }

    @Override
    public Picture getCover(Toy toy){
        Picture pic = mallMapper.selectPictureByCommodity_id(toy);
        return pic;
    }

    @Override
    public Pictures getPictures(Toy toy) {
        Pictures pictures = new Pictures();
        Picture[] pics  = mallMapper.selectPicturesByCommodity_id(toy);
        pictures.setPictures(Arrays.asList(pics));
        return pictures;
    }

    @Override
    public User getUserByToy(Toy toy) {
        User seller = mallMapper.selectUserByToy(toy);
        return seller;
    }

    @Override
    public boolean cancelOrder(Order order, User user) {
        Order order_in_db = mallMapper.selectOrderById(order);
        if(order_in_db != null && order_in_db.getStatus().equals("0")){
            if(order_in_db.getBuyer_id().equals(user.getUid())){
                order_in_db.setStatus("2");
                mallMapper.updateOrder(order_in_db);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean confirmOrder(Order order, User user) {
        Order order_in_db = mallMapper.selectOrderById(order);
        if(order_in_db != null && order_in_db.getStatus().equals("0")){
            if(order_in_db.getBuyer_id().equals(user.getUid())){
                order_in_db.setStatus("1");
                mallMapper.updateOrder(order_in_db);
                return true;
            }
        }
        return false;
    }

    @Override
    public String UploadPic(MultipartFile file) throws IOException {
        String filename = String.valueOf(new Date().getTime()) + String.valueOf(new Random().nextInt(1000)) + ".jpg";
        InputStream input = file.getInputStream();
        if(Utils.uploadFile(filename, input)){
            return filename;
        }else{
            return null;
        }
    }
}
