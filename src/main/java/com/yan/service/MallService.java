package com.yan.service;

import com.yan.domain.Comment;
import com.yan.domain.Order;
import com.yan.domain.Toy;
import com.yan.domain.User;

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
    Toy[] getAllToys();

    /**
     * 搜索关键字获得玩具
     * @param keyword
     * @return
     */
    Toy[] getToysByKeyword(String keyword);

    /**
     *  根据类别获得玩具
     * @param type
     * @return
     */
    Toy[] getToysByType(String type);

    /**
     * 根据省份获得玩具
     * @param province
     * @return
     */
    Toy[] getToysByProvince(String province);

    /**
     * 根据城市获得玩具
     * @param city
     * @return
     */
    Toy[] getToysByCity(String city);

    /**
     * 根据用户获得玩具
     * @param user
     * @return
     */
    Toy[] getToysByUser(User user);

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
    Comment[] getComment(Toy toy);

    /**
     * 添加收藏夹
     * @param toy
     * @param user
     * @return
     */
    boolean addFavorite(Toy toy, User user);

    /**
     * 删除收藏夹
     * @param toy
     * @param user
     * @return
     */
    boolean delFavorite(Toy toy, User user);

    /**
     * 添加订单
     * @param order
     * @param user
     * @return
     */
    boolean addOrder(Order order, User user);

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



}
