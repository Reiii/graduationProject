package com.yan.service;

import com.yan.domain.*;
import com.yan.exception.MallException;
import com.yan.util.Page;

/**
 * Package ：com.yan.service
 * Description：
 * date： 2019/1/8 下午12:59
 * author： Li KaiYan
 */

public interface MallService {

    /**
     * 添加二手玩具
     * @param toy
     * @param user
     * @return
     */
    boolean addToy(Toy toy, User user);

    /**
     * 更新玩具信息
     * @param toy
     * @param user
     * @return
     */
    Toy updateToy(Toy toy, User user);

    /**
     * 删除玩具
     * @param toy
     * @param user
     * @return
     */
    boolean delToy(Toy toy, User user);

    /**
     * 获得所有玩具
     * @return
     */
    Page<Toy> getAllToys(String page) throws MallException;

    /**
     * 搜索关键字获得玩具
     * @param keyword
     * @return
     */
    Page<Toy> getToysByKeyword(String keyword, String page) throws MallException;

    /**
     *  根据类别获得玩具
     * @param type
     * @return
     */
    Page<Toy> getToysByType(String type, String page) throws MallException;

    /**
     * 根据省份获得玩具
     * @param province
     * @return
     */
    Page<Toy> getToysByProvince(String province, String page) throws MallException;

    /**
     * 根据城市获得玩具
     * @param city
     * @return
     */
    Page<Toy> getToysByCity(String city, String page) throws MallException;

    /**
     * 根据用户获得玩具
     * @param user
     * @return
     */
    Page<Toy> getToysByUser(User user, String page) throws MallException;

    /**
     * 获取玩具信息
     * @param toy
     * @return
     */
    Toy toyInfo(Toy toy);

    /**
     * 增加评论
     * @param comment
     * @param user
     * @return
     */
    boolean addComment(Comment comment, User user);

    /**
     * 删除评论
     * @param comment
     * @param user
     * @return
     */
    boolean delComment(Comment comment, User user);

    /**
     * 获取商品留言
     * @param toy
     * @return
     */
    Comments getComments(Toy toy);

    /**
     * 添加收藏夹
     * @param toy
     * @param user
     * @return
     */
    boolean addFavorite(Toy toy, User user);

    /**
     * 删除收藏夹
     * @param favorite
     * @param user
     * @return
     */
    boolean delFavorite(Favorite favorite, User user);

    /**
     * 添加订单
     * @param order
     * @param user
     * @return
     */
    boolean addOrder(Order order);

    /**
     * 删除订单
     * @param order
     * @param user
     * @return
     */
    boolean delOrder(Order order, User user);

    /**
     * 更新订单
     * @param order
     * @param user
     * @return
     */
    boolean updateOrder(Order order, User user);

    /**
     * 查看用户订单
     * @param user
     * @return
     */
    Order[] selectOrder(User user);

    /**
     * 查看订单信息
     * @param order
     * @return
     */
    Order orderInfo(Order order, User user);

    /**
     * 获取商品封面
     * @param toy
     * @return
     */
    Picture getCover(Toy toy);

    /**
     * 获取商品全部图片
     * @param toy
     * @return
     */
    Pictures getPictures(Toy toy);

    /**
     * 根据玩具选择用户
     * @param toy
     * @return
     */
    User getUserByToy(Toy toy);


}
