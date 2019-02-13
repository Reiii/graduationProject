package com.yan.service.impl;

import com.yan.dao.MallMapper;
import com.yan.domain.*;
import com.yan.exception.MallException;
import com.yan.service.MallService;
import com.yan.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
    public Page getAllToys(String page) throws MallException{
        int total = mallMapper.countAllToy();
        int total_page = total / 20 * 20 == total ? total / 20 : total / 20 + 1;
        if(Integer.parseInt(page) > total_page){
            throw new MallException(MallException.PAGE_OVER_LIMIT);
        }
        Toy[] toys = mallMapper.selectAllToy(String.valueOf((Integer.parseInt(page) - 1) * 20));
        Page<Toy> toyPage = new Page<>();
        toyPage.setCurrentPage(Integer.parseInt(page));
        toyPage.setStartPage(1);
        toyPage.setEndPage(total_page);
        toyPage.setData(Arrays.asList(toys));
        return toyPage;
    }

    @Override
    public Page<Toy> getToysByKeyword(String keyword, String page) throws MallException{
        int total = mallMapper.countToyByKeyword(keyword);
        int total_page = total / 20 * 20 == total ? total / 20 : total / 20 + 1;
        if(Integer.parseInt(page) > total_page){
            throw new MallException(MallException.PAGE_OVER_LIMIT);
        }
        Toy[] toys = mallMapper.selectToyByTitle(keyword, String.valueOf((Integer.parseInt(page) - 1) * 20));
        Page<Toy> toyPage = new Page<>();
        toyPage.setCurrentPage(Integer.parseInt(page));
        toyPage.setStartPage(1);
        toyPage.setEndPage(total_page);
        toyPage.setData(Arrays.asList(toys));
        return toyPage;
    }

    @Override
    public Page<Toy> getToysByType(String type, String page) throws MallException {
        int total = mallMapper.countToyByType(type);
        int total_page = total / 20 * 20 == total ? total / 20 : total / 20 + 1;
        if(Integer.parseInt(page) > total_page){
            throw new MallException(MallException.PAGE_OVER_LIMIT);
        }
        Toy[] toys = mallMapper.selectToyByType(type, String.valueOf((Integer.parseInt(page) - 1) * 20));
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
        Toy[] toys = mallMapper.selectToyByProvince(province, String.valueOf((Integer.parseInt(page) - 1) * 20));
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
        Toy[] toys = mallMapper.selectToyByCity(city, String.valueOf((Integer.parseInt(page) - 1) * 20));
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
        Toy[] toys = mallMapper.selectToyByUser(user, String.valueOf((Integer.parseInt(page) - 1) * 20));
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
