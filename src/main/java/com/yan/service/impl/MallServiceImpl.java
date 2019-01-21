package com.yan.service.impl;

import com.yan.dao.MallMapper;
import com.yan.domain.*;
import com.yan.service.MallService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Package ：com.yan.service.impl
 * Description：
 * date： 2019/1/20 上午11:16
 * author： Li KaiYan
 */

public class MallServiceImpl implements MallService {

    @Autowired
    MallMapper mallMapper;

    @Override
    public boolean addToy(Toy toy, User user) {
        toy.setUid(user.getUid());
        mallMapper.addToy(toy);
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
    public Toy[] getAllToys() {
        return mallMapper.selectAllToy();
    }

    @Override
    public Toy[] getToysByKeyword(String keyword) {
        return mallMapper.selectToyByTitle(keyword);
    }

    @Override
    public Toy[] getToysByType(String type) {
        return mallMapper.selectToyByType(type);
    }

    @Override
    public Toy[] getToysByProvince(String province) {
        return mallMapper.selectToyByProvince(province);
    }

    @Override
    public Toy[] getToysByCity(String city) {
        return mallMapper.selectToyByCity(city);
    }

    @Override
    public Toy[] getToysByUser(User user) {
        return mallMapper.selectToyByUser(user);
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
    public Comment[] getComment(Toy toy) {
        return mallMapper.selectCommentByToy(toy);
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
    public synchronized boolean addOrder(Order order, User user) {
        Toy toy = new Toy();
        toy.setCommdity_id(order.getCommdity_id());
        Toy toy_in_db = mallMapper.selectToyById(toy);
        if(toy_in_db.getStatus().equals("1") || toy_in_db.getStatus().equals("2")){
            return false;
        }else{
            order.setSeller_id(toy_in_db.getUid());
            order.setOrder_time(String.valueOf(new Date().getTime()));
            mallMapper.addOrder(order);
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
    public Order[] selectOrder(User user) {
        return mallMapper.selectOrderByUser(user);
    }

    @Override
    public Order orderInfo(Order order, User user) {
        Order order_in_db = mallMapper.selectOrderById(order);
        if(order_in_db.getBuyer_id().equals(user.getUid())){
            return order_in_db;
        }
        return null;
    }
}
